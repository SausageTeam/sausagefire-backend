package com.sausage.app.service.hr.dashboard.impl;

import com.sausage.app.constant.enums.ApplicationWorkFlow.ApplicationWorkFlowOPTStatusEnums;
import com.sausage.app.dao.ApplicationWorkFlow.ApplicationWorkFlowDAO;
import com.sausage.app.dao.Employee.EmployeeDAO;
import com.sausage.app.dao.PersonalDocument.PersonalDocumentDAO;
import com.sausage.app.domain.hr.dashboard.Dashboard;
import com.sausage.app.domain.hr.dashboard.Trouble;
import com.sausage.app.entity.ApplicationWorkFlow;
import com.sausage.app.entity.Employee;
import com.sausage.app.entity.Person;
import com.sausage.app.entity.PersonalDocument;
import com.sausage.app.service.common.mail.EmailService;
import com.sausage.app.service.hr.dashboard.HRDashboardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import static com.sausage.app.constant.Constant.VISA_NOTIFICATION_BODY;
import static com.sausage.app.constant.Constant.VISA_NOTIFICATION_SUBJECT;
import static com.sausage.app.constant.enums.ApplicationWorkFlow.ApplicationWorkFlowNotifyEnums.NOT_NOTIFIED;
import static com.sausage.app.constant.enums.ApplicationWorkFlow.ApplicationWorkFlowOPTStatusEnums.OPT_RECEIPT;
import static com.sausage.app.constant.enums.ApplicationWorkFlow.ApplicationWorkFlowTypeEnums.OPT;
import static com.sausage.app.constant.enums.ApplicationWorkFlow.ApplicationWorkFlowUploadEnums.REQUIRE;

@Service
public class HRDashboardServiceImpl implements HRDashboardService {

    private EmployeeDAO employeeDAO;

    private ApplicationWorkFlowDAO applicationWorkFlowDAO;

    private PersonalDocumentDAO personalDocumentDAO;

    private EmailService emailService;

    @Autowired
    public void setEmployeeDAO(EmployeeDAO employeeDAO) {
        this.employeeDAO = employeeDAO;
    }

    @Autowired
    public void setApplicationWorkFlowDAO(ApplicationWorkFlowDAO applicationWorkFlowDAO) {
        this.applicationWorkFlowDAO = applicationWorkFlowDAO;
    }

    @Autowired
    public void setPersonalDocumentDAO(PersonalDocumentDAO personalDocumentDAO) {
        this.personalDocumentDAO = personalDocumentDAO;
    }

    @Autowired
    public void setEmailService(EmailService emailService) {
        this.emailService = emailService;
    }

    private List<Trouble> buildWaitingTroubleList() {
        List<Trouble> waitingList = new ArrayList<>();
        List<ApplicationWorkFlow> applicationWorkFlowList = applicationWorkFlowDAO.getAllWaitingApplicationWorkFlow();
        for (ApplicationWorkFlow applicationWorkFlow : applicationWorkFlowList) {
            Employee employee = applicationWorkFlow.getEmployee();
            Person person = employee.getPerson();
            PersonalDocument personalDocument = personalDocumentDAO.getLatestDocumentByEmployee(employee);
            Trouble trouble = Trouble.builder()
                    .employeeId(employee.getId())
                    .firstName(person.getFirstName())
                    .middleName(person.getMiddleName())
                    .lastName(person.getLastName())
                    .fileName(personalDocument.getTitle())
                    .build();
            waitingList.add(trouble);
        }
        return waitingList;
    }

    private List<Trouble> buildNotifyTroubleList() {
        List<Trouble> notifyingList = new ArrayList<>();
        List<Employee> employeeList = employeeDAO.getAllEmployee();
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String formatDateTime = now.format(format);

        for (Employee employee : employeeList) {
            Person person = employee.getPerson();
            String visaEndDate = employee.getVisaEndDate();
            if (visaEndDate == null || visaEndDate.length() == 0)
                continue;

            ApplicationWorkFlow applicationWorkFlow = applicationWorkFlowDAO.getApplicationWorkFlowByEmployee(employee);
            if (applicationWorkFlow == null) {
                applicationWorkFlow = ApplicationWorkFlow.builder()
                        .employee(employee)
                        .status(OPT_RECEIPT.getValue())
                        .type(OPT.getStr())
                        .upload(REQUIRE.getValue())
                        .notify(NOT_NOTIFIED.getValue())
                        .createdDateTime(formatDateTime)
                        .modificationDateTime(formatDateTime)
                        .build();
                applicationWorkFlowDAO.setApplicationWorkFlow(applicationWorkFlow);
            }
            LocalDate dateNow = LocalDate.now();
            DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            LocalDate localDate = LocalDate.parse(visaEndDate, dateFormat);
            Period period = Period.between(dateNow, localDate);
            int diff = period.getDays();
            if (diff < 100) {
                String workAuthorization = ApplicationWorkFlowOPTStatusEnums.values()[applicationWorkFlow.getStatus()].getStr();
                Trouble trouble = Trouble.builder()
                        .employeeId(employee.getId())
                        .firstName(person.getFirstName())
                        .middleName(person.getMiddleName())
                        .lastName(person.getLastName())
                        .workAuthorization(workAuthorization)
                        .visaEndDate(visaEndDate)
                        .dayLeft(diff)
                        .build();
                notifyingList.add(trouble);
            }

        }
        return notifyingList;
    }

    @Override
    @Transactional
    public Dashboard getHRDashboard() {
        try {
            return Dashboard.builder()
                    .waitingList(buildWaitingTroubleList())
                    .notifyList(buildNotifyTroubleList())
                    .build();
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    @Transactional
    public void postHRDashboard(int employeeId) {
        Employee employee = employeeDAO.getEmployeeById(employeeId);
        ApplicationWorkFlow applicationWorkFlow = applicationWorkFlowDAO.getApplicationWorkFlowByEmployee(employee);

        String firstName = employee.getPerson().getFirstName();
        String workAuthorization = ApplicationWorkFlowOPTStatusEnums.values()[applicationWorkFlow.getStatus()].getStr();

        String to = employee.getPerson().getEmail();
        String body = String.format(VISA_NOTIFICATION_BODY, firstName, workAuthorization);
        emailService.sendMail(to, VISA_NOTIFICATION_SUBJECT, body);
    }

}

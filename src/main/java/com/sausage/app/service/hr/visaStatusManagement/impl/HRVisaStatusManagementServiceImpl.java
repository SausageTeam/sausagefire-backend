package com.sausage.app.service.hr.visaStatusManagement.impl;

import com.sausage.app.dao.ApplicationWorkFlow.ApplicationWorkFlowDAO;
import com.sausage.app.constant.enums.ApplicationWorkFlow.ApplicationWorkFlowOPTStatusEnums;
import com.sausage.app.dao.Employee.EmployeeDAO;
import com.sausage.app.dao.PersonalDocument.PersonalDocumentDAO;
import com.sausage.app.dao.VisaStatus.VisaStatusDAO;
import com.sausage.app.domain.hr.visaStatusManagement.VisaStatusManagement;
import com.sausage.app.domain.hr.visaStatusManagement.VisaStatusRecord;
import com.sausage.app.entity.ApplicationWorkFlow;
import com.sausage.app.entity.Employee;
import com.sausage.app.entity.Person;
import com.sausage.app.entity.PersonalDocument;
import com.sausage.app.fileIO.FileOutput;
import com.sausage.app.service.hr.visaStatusManagement.HRVisaStatusManagementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.File;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import static com.sausage.app.constant.Constant.VISA_NOTIFICATION;
import static com.sausage.app.constant.enums.ApplicationWorkFlow.ApplicationWorkFlowNotifyEnums.NOT_NOTIFIED;
import static com.sausage.app.constant.enums.ApplicationWorkFlow.ApplicationWorkFlowOPTStatusEnums.OPT_RECEIPT;
import static com.sausage.app.constant.enums.ApplicationWorkFlow.ApplicationWorkFlowTypeEnums.OPT;
import static com.sausage.app.constant.enums.ApplicationWorkFlow.ApplicationWorkFlowUploadEnums.REQUIRE;

@Service
public class HRVisaStatusManagementServiceImpl implements HRVisaStatusManagementService {

    private EmployeeDAO employeeDAO;

    private VisaStatusDAO visaStatusDAO;

    private ApplicationWorkFlowDAO applicationWorkFlowDAO;

    private PersonalDocumentDAO personalDocumentDAO;

    @Autowired
    public void setEmployeeDAO(EmployeeDAO employeeDAO) {
        this.employeeDAO = employeeDAO;
    }

    @Autowired
    public void setVisaStatusDAO(VisaStatusDAO visaStatusDAO) {
        this.visaStatusDAO = visaStatusDAO;
    }

    @Autowired
    public void setApplicationWorkFlowDAO(ApplicationWorkFlowDAO applicationWorkFlowDAO) {
        this.applicationWorkFlowDAO = applicationWorkFlowDAO;
    }

    @Autowired
    public void setPersonalDocumentDAO(PersonalDocumentDAO personalDocumentDAO) {
        this.personalDocumentDAO = personalDocumentDAO;
    }

    @Override
    @Transactional
    public VisaStatusManagement getVisaStatusManagement() {
        try {
            List<VisaStatusRecord> visaStatusRecordList = new ArrayList<>();
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
                LocalDate localDate = LocalDate.parse(visaEndDate, format);
                Period period = Period.between(dateNow, localDate);
                int diff = period.getDays();

                List<PersonalDocument> personalDocumentList = personalDocumentDAO.getPersonalDocumentByEmployee(employee);
                List<String> documentTitleList = new ArrayList<>();
                List<File> documentReceivedList = new ArrayList<>();
                for (PersonalDocument personalDocument : personalDocumentList) {
                    documentTitleList.add(personalDocument.getTitle());
                    documentReceivedList.add(FileOutput.getFile(personalDocument.getPath()));
                }

                String workAuthorization = ApplicationWorkFlowOPTStatusEnums.values()[applicationWorkFlow.getStatus()].getStr();
                String visaType = visaStatusDAO.getVisaStatusById(employee.getVisaStatusId()).getVisaType();

                VisaStatusRecord visaStatusRecord = VisaStatusRecord.builder()
                        .firstName(person.getFirstName())
                        .middleName(person.getMiddleName())
                        .lastName(person.getLastName())
                        .workAuthorization(workAuthorization)
                        .dayLeft(diff)
                        .visaStatus(visaType)
                        .visaStartDate(employee.getVisaStartDate())
                        .visaEndDate(employee.getVisaEndDate())
                        .documentTitleList(documentTitleList)
                        .documentReceivedList(documentReceivedList)
                        .nextStep(ApplicationWorkFlowOPTStatusEnums.values()[applicationWorkFlow.getStatus() + 1].getStr())
                        .build();
                visaStatusRecordList.add(visaStatusRecord);
            }

            return VisaStatusManagement.builder()
                    .visaStatusRecordList(visaStatusRecordList)
                    .build();
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    @Transactional
    public void setVisaStatusManagement(int employeeId) {
        Employee employee = employeeDAO.getEmployeeById(employeeId);
        ApplicationWorkFlow applicationWorkFlow = applicationWorkFlowDAO.getApplicationWorkFlowByEmployee(employee);

        String firstName = employee.getPerson().getFirstName();
        String workAuthorization = ApplicationWorkFlowOPTStatusEnums.values()[applicationWorkFlow.getStatus()].getStr();

        String to = employee.getPerson().getEmail();
        String text = String.format(VISA_NOTIFICATION, firstName, workAuthorization);
    }

}

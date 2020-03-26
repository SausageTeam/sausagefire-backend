package com.sausage.app.service.hr.visaStatusManagement.impl;

import com.sausage.app.dao.applicationWorkFlow.ApplicationWorkFlowDAO;
import com.sausage.app.dao.applicationWorkFlow.enums.ApplicationWorkFlowStatusEnums;
import com.sausage.app.dao.personalDocument.PersonalDocumentDAO;
import com.sausage.app.dao.visaStatus.VisaStatusDAO;
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
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import static com.sausage.app.constant.Constant.VISA_NOTIFICATION;
import static com.sausage.app.dao.applicationWorkFlow.enums.ApplicationWorkFlowStatusEnums.OPT_RECEIPT;

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
        List<VisaStatusRecord> visaStatusRecordList = new ArrayList<>();
        List<Employee> employeeList = employeeDAO.getAllEmployee();
        DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyy-MM-dd");

        for (Employee employee : employeeList) {
            Person person = employee.getPerson();

            String visaEndDate = employee.getVisaEndDate();

            if(visaEndDate == null || visaEndDate.length() == 0)
                continue;

            LocalDate now = LocalDate.now();
            ApplicationWorkFlow applicationWorkFlow = applicationWorkFlowDAO.getApplicationWorkFlowByEmployee(employee);
            int diff = 0;
            if (applicationWorkFlow == null) {
                applicationWorkFlow = ApplicationWorkFlow.builder()
                        .createdDate(now.format(format))
                        .modificationDate(now.format(format))
                        .status(OPT_RECEIPT.getValue())
                        .type("OPT")
                        .build();
                applicationWorkFlowDAO.setApplicationWorkFlow(applicationWorkFlow);
            }
            LocalDate localDate = LocalDate.parse(visaEndDate , format);
            Period period = Period.between(now, localDate);
            diff = period.getDays();

            List<PersonalDocument> personalDocumentList = personalDocumentDAO.getPersonalDocumentByEmployee(employee);
            List<String> documentTitleList = new ArrayList<>();
            List<File> documentReceivedList = new ArrayList<>();
            for (PersonalDocument personalDocument : personalDocumentList) {
                documentTitleList.add(personalDocument.getTitle());
                documentReceivedList.add(FileOutput.getFile(personalDocument.getPath()));
            }

            String workAuthorization = ApplicationWorkFlowStatusEnums.values()[applicationWorkFlow.getStatus()].getStr();
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
                    .nextStep(ApplicationWorkFlowStatusEnums.values()[applicationWorkFlow.getStatus() + 1].getStr())
                    .build();
            visaStatusRecordList.add(visaStatusRecord);
        }

        return VisaStatusManagement.builder()
                .visaStatusRecordList(visaStatusRecordList)
                .build();
    }

    @Override
    @Transactional
    public void setVisaStatusManagement(int employeeId) {
        Employee employee = employeeDAO.getEmployeeById(employeeId);
        ApplicationWorkFlow applicationWorkFlow = applicationWorkFlowDAO.getApplicationWorkFlowByEmployee(employee);

        String firstName = employee.getPerson().getFirstName();
        String workAuthorization = ApplicationWorkFlowStatusEnums.values()[applicationWorkFlow.getStatus()].getStr();

        String to = employee.getPerson().getEmail();
        String text = String.format(VISA_NOTIFICATION, firstName, workAuthorization);
    }

}

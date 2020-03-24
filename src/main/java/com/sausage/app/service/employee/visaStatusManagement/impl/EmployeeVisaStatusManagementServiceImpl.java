package com.sausage.app.service.employee.visaStatusManagement.impl;

import com.sausage.app.constant.Constant;
import com.sausage.app.dao.ApplicationWorkFlow.ApplicationWorkFlowDAO;
import com.sausage.app.dao.ApplicationWorkFlow.enums.ApplicationWorkFlowStatusEnums;
import com.sausage.app.dao.DigitalDocument.DigitalDocumentDAO;
import com.sausage.app.dao.Employee.EmployeeDAO;
import com.sausage.app.dao.Person.PersonDAO;
import com.sausage.app.dao.User.UserDAO;
import com.sausage.app.dao.VisaStatus.VisaStatusDAO;
import com.sausage.app.domain.employee.visaStatusManagement.VisaStatusManagement;
import com.sausage.app.entity.*;
import com.sausage.app.fileIO.FileInput;
import com.sausage.app.fileIO.FileOutput;
import com.sausage.app.service.employee.visaStatusManagement.EmployeeVisaStatusManagementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.File;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static com.sausage.app.dao.ApplicationWorkFlow.enums.ApplicationWorkFlowStatusEnums.OPT_EAD;
import static com.sausage.app.dao.VisaStatus.enums.VisaStatusEnums.F1;

@Service
public class EmployeeVisaStatusManagementServiceImpl implements EmployeeVisaStatusManagementService {

    private UserDAO userDAO;

    private PersonDAO personDAO;

    private EmployeeDAO employeeDAO;

    private VisaStatusDAO visaStatusDAO;

    private ApplicationWorkFlowDAO applicationWorkFlowDAO;

    private DigitalDocumentDAO digitalDocumentDAO;

    @Autowired
    public void setUserDAO(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    @Autowired
    public void setPersonDAO(PersonDAO personDAO) {
        this.personDAO = personDAO;
    }

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
    public void setDigitalDocumentDAO(DigitalDocumentDAO digitalDocumentDAO) {
        this.digitalDocumentDAO = digitalDocumentDAO;
    }

    private Employee getEmployeeByUserId(int userId) {
        User user = userDAO.getUserById(userId);
        Person person = personDAO.getPersonById(user.getPersonId());
        return employeeDAO.getEmployeeByPerson(person);
    }

    @Override
    @Transactional
    public VisaStatusManagement getVisaStatusManagement(int userId) {
        VisaStatusManagement visaStatusManagement = new VisaStatusManagement();

        Employee employee = getEmployeeByUserId(userId);
        ApplicationWorkFlow applicationWorkFlow = applicationWorkFlowDAO.getApplicationWorkFlowByEmployee(employee);
        VisaStatus visaStatus = visaStatusDAO.getVisaStatusById(employee.getVisaStatusId());

        String visaEndDate = employee.getVisaEndDate();
        DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        LocalDateTime localDateTime = LocalDateTime.parse(visaEndDate , format);
        LocalDateTime now = LocalDateTime.now();
        Duration duration = Duration.between(now, localDateTime);
        long diff = duration.toDays();

        boolean ifF1 = (visaStatus.getId() == F1.getValue());
        boolean ifEAD = (applicationWorkFlow.getStatus() == OPT_EAD.getValue());
        boolean ifExpired = (diff < 100);
        if (ifF1 && ifEAD && ifExpired){
            DigitalDocument i983_empty = digitalDocumentDAO.getDigitalDocumentByType("I983_empty");
            File i983_empty_file = FileOutput.getFile(i983_empty.getTemplateLocation());
            DigitalDocument i983_sample = digitalDocumentDAO.getDigitalDocumentByType("I983_sample");
            File i983_sample_file = FileOutput.getFile(i983_sample.getTemplateLocation());
            visaStatusManagement.setIfNeedDownload(true);
            visaStatusManagement.setStatus(applicationWorkFlow.getStatus());
            visaStatusManagement.setComments(applicationWorkFlow.getComments());
            visaStatusManagement.setEmptyForm(i983_empty_file);
            visaStatusManagement.setSampleForm(i983_sample_file);
        }else{
            visaStatusManagement.setIfNeedDownload(false);
            visaStatusManagement.setStatus(applicationWorkFlow.getStatus());
            visaStatusManagement.setComments(applicationWorkFlow.getComments());
            visaStatusManagement.setEmptyForm(null);
            visaStatusManagement.setSampleForm(null);
        }
        return visaStatusManagement;
    }

    @Override
    @Transactional
    public void setVisaStatusManagement(int userId, File file) {
        Employee employee = getEmployeeByUserId(userId);
        ApplicationWorkFlow applicationWorkFlow = applicationWorkFlowDAO.getApplicationWorkFlowByEmployee(employee);
        String filePath = String.format(Constant.DEFAULT_FILE_PATH, employee.getId(), ApplicationWorkFlowStatusEnums.values()[applicationWorkFlow.getStatus()].getStr() + ".pdf");
        FileInput.setFile(filePath, file);
    }
}

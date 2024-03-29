package com.sausage.app.service.employee.visaStatusManagement.impl;

import com.sausage.app.constant.Constant;
import com.sausage.app.constant.enums.ApplicationWorkFlow.ApplicationWorkFlowOPTStatusEnums;
import com.sausage.app.dao.ApplicationWorkFlow.ApplicationWorkFlowDAO;
import com.sausage.app.dao.DigitalDocument.DigitalDocumentDAO;
import com.sausage.app.dao.Employee.EmployeeDAO;
import com.sausage.app.dao.User.UserDAO;
import com.sausage.app.dao.VisaStatus.VisaStatusDAO;
import com.sausage.app.domain.employee.visaStatusManagement.VisaStatusManagement;
import com.sausage.app.entity.*;
import com.sausage.app.fileIO.FileInput;
import com.sausage.app.fileIO.URIHandler;
import com.sausage.app.service.employee.visaStatusManagement.EmployeeVisaStatusManagementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.File;
import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;

import static com.sausage.app.constant.enums.ApplicationWorkFlow.ApplicationWorkFlowOPTStatusEnums.OPT_EAD;
import static com.sausage.app.constant.enums.VisaStatus.VisaStatusVisaTypeEnums.F1;

@Service
public class EmployeeVisaStatusManagementServiceImpl implements EmployeeVisaStatusManagementService {

    private UserDAO userDAO;

    private EmployeeDAO employeeDAO;

    private VisaStatusDAO visaStatusDAO;

    private ApplicationWorkFlowDAO applicationWorkFlowDAO;

    private DigitalDocumentDAO digitalDocumentDAO;

    private URIHandler uriHandler;

    @Autowired
    public void setUserDAO(UserDAO userDAO) {
        this.userDAO = userDAO;
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

    @Autowired
    public void setUriHandler(URIHandler uriHandler) {
        this.uriHandler = uriHandler;
    }

    @Override
    @Transactional
    public VisaStatusManagement getVisaStatusManagement(int userId) {
        try {
            VisaStatusManagement visaStatusManagement = new VisaStatusManagement();

            User user = userDAO.getUserById(userId);
            Person person = user.getPerson();
            Employee employee = employeeDAO.getEmployeeByPerson(person);
            ApplicationWorkFlow applicationWorkFlow = applicationWorkFlowDAO.getApplicationWorkFlowByEmployee(employee);
            VisaStatus visaStatus = visaStatusDAO.getVisaStatusById(employee.getVisaStatusId());

            String visaEndDate = employee.getVisaEndDate();
            DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            LocalDate localDate = LocalDate.parse(visaEndDate, format);
            LocalDate now = LocalDate.now();
            Period period = Period.between(now, localDate);
            long diff = period.getDays();

            boolean ifF1 = (visaStatus.getId() == F1.getValue());
            boolean ifEAD = (applicationWorkFlow.getStatus() == OPT_EAD.getValue());
            boolean ifExpired = (diff < 100);
            if (ifF1 && ifEAD && ifExpired) {
                String uri_empty = uriHandler.getUri("document", "I983_empty.pdf");
                String uri_sample = uriHandler.getUri("document", "I983_sample.pdf");
                visaStatusManagement.setIfNeedDownload(true);
                visaStatusManagement.setStatus(applicationWorkFlow.getStatus());
                visaStatusManagement.setComments(applicationWorkFlow.getComments());
                visaStatusManagement.setEmptyForm(uri_empty);
                visaStatusManagement.setSampleForm(uri_sample);
            } else {
                visaStatusManagement.setIfNeedDownload(false);
                visaStatusManagement.setStatus(applicationWorkFlow.getStatus());
                visaStatusManagement.setComments(applicationWorkFlow.getComments());
                visaStatusManagement.setEmptyForm(null);
                visaStatusManagement.setSampleForm(null);
            }
            return visaStatusManagement;
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    @Transactional
    public void setVisaStatusManagement(int userId, File file) {
        User user = userDAO.getUserById(userId);
        Person person = user.getPerson();
        Employee employee = employeeDAO.getEmployeeByPerson(person);
        ApplicationWorkFlow applicationWorkFlow = applicationWorkFlowDAO.getApplicationWorkFlowByEmployee(employee);
        String filePath = String.format(Constant.DEFAULT_FILE_PATH, employee.getId(), ApplicationWorkFlowOPTStatusEnums.values()[applicationWorkFlow.getStatus()].getStr() + ".pdf");
        FileInput.setFile(filePath, file);
    }

}

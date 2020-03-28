package com.sausage.app.service.employee.onboarding.impl;

import com.sausage.app.dao.Employee.EmployeeDAO;
import com.sausage.app.dao.Person.PersonDAO;
import com.sausage.app.dao.VisaStatus.VisaStatusDAO;
import com.sausage.app.dao.User.UserDAO;
import com.sausage.app.domain.employee.onboarding.onboardingVisa.OnboardingVisa;
import com.sausage.app.entity.Employee;
import com.sausage.app.entity.Person;
import com.sausage.app.entity.User;
import com.sausage.app.entity.VisaStatus;
import com.sausage.app.service.employee.onboarding.EmployeeOnboardingVisaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Service
public class EmployeeOnboardingVisaServiceImpl implements EmployeeOnboardingVisaService {

    private UserDAO userDAO;

    private PersonDAO personDAO;

    private EmployeeDAO employeeDAO;

    private VisaStatusDAO visaStatusDAO;

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

    @Override
    @Transactional
    public OnboardingVisa getOnboardingVisa(int userId) {
        OnboardingVisa onboardingVisa = new OnboardingVisa();
        User user = userDAO.getUserById(userId);
        Person person = user.getPerson();
        Employee employee = employeeDAO.getEmployeeByPerson(person);
        int visaStatusId = employee.getVisaStatusId();
        VisaStatus visaStatus = visaStatusDAO.getVisaStatusById(visaStatusId);
        if (visaStatus != null) {
            onboardingVisa.setVisaType(visaStatus.getVisaType());
            onboardingVisa.setVisaStartDate(employee.getVisaStartDate());
            onboardingVisa.setVisaEndDate(employee.getVisaEndDate());
        }
        return onboardingVisa;
    }

    @Override
    @Transactional
    public void setOnboardingVisa(int userId, OnboardingVisa onboardingVisa) {
        User user = userDAO.getUserById(userId);
        Person person = user.getPerson();
        Employee employee = employeeDAO.getEmployeeByPerson(person);
        String visaType = onboardingVisa.getVisaType();
        String visaStartDate = onboardingVisa.getVisaStartDate();
        String visaEndDate = onboardingVisa.getVisaEndDate();

        VisaStatus visaStatus = visaStatusDAO.setOtherVisaStatus(visaType);
        if (visaStatus == null){
            LocalDateTime now = LocalDateTime.now();
            DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
            String formatDateTime = now.format(format);
            visaStatus = VisaStatus.builder()
                    .visaType(visaType)
                    .modificationDate(formatDateTime)
                    .createUser(userId)
                    .build();
            visaStatus = visaStatusDAO.setVisaStatus(visaStatus);
        }

        employee.setVisaStatusId(visaStatus.getId());
        employee.setVisaStartDate(visaStartDate);
        employee.setVisaEndDate(visaEndDate);
        employeeDAO.setEmployee(employee);
    }

}

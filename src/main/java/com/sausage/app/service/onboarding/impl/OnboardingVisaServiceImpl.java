package com.sausage.app.service.onboarding.impl;

import com.sausage.app.dao.Employee.EmployeeDAO;
import com.sausage.app.dao.Person.PersonDAO;
import com.sausage.app.dao.user.UserDAO;
import com.sausage.app.dao.VisaStatus.VisaStatusDAO;
import com.sausage.app.domain.onboarding.onboardingVisa.OnboardingVisa;
import com.sausage.app.entity.Employee;
import com.sausage.app.entity.Person;
import com.sausage.app.entity.User;
import com.sausage.app.entity.VisaStatus;
import com.sausage.app.service.onboarding.OnboardingVisaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Service
public class OnboardingVisaServiceImpl implements OnboardingVisaService {

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

    private Employee getEmployeeByUserId(int userId) {
        User user = userDAO.getUserById(userId);
        Person person = personDAO.getPersonById(user.getPersonId());
        return employeeDAO.getEmployeeByPerson(person);
    }

    @Override
    @Transactional
    public OnboardingVisa getOnboardingVisa(int userId) {
        OnboardingVisa onboardingVisa = new OnboardingVisa();

        Employee employee = getEmployeeByUserId(userId);
        onboardingVisa.setVisaStartDate(employee.getVisaStartDate());
        onboardingVisa.setVisaEndDate(employee.getVisaEndDate());

        VisaStatus visaStatus = employee.getVisaStatus();
        onboardingVisa.setVisaType(visaStatus.getVisaType());

        return onboardingVisa;
    }

    @Override
    @Transactional
    public void setOnboardingVisa(int userId, OnboardingVisa onboardingVisa) {
        Employee employee = getEmployeeByUserId(userId);
        String visaType = onboardingVisa.getVisaType();
        String visaStartDate = onboardingVisa.getVisaStartDate();
        String visaEndDate = onboardingVisa.getVisaEndDate();

        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        String formatDateTime = now.format(format);
        VisaStatus visaStatus = VisaStatus.builder()
                .visaType(visaType)
                .activeFlag(1)
                .modificationDate(formatDateTime)
                .createUser(0)
                .build();
        visaStatus = visaStatusDAO.setVisaStatus(visaStatus);

        employee.setVisaStatus(visaStatus);
        employee.setVisaStartDate(visaStartDate);
        employee.setVisaEndDate(visaEndDate);
        employeeDAO.setEmployee(employee);
    }


}
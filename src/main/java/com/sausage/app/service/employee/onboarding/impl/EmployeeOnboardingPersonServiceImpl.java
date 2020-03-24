package com.sausage.app.service.employee.onboarding.impl;

import com.sausage.app.dao.ApplicationWorkFlow.ApplicationWorkFlowDAO;
import com.sausage.app.dao.Employee.EmployeeDAO;
import com.sausage.app.dao.Person.PersonDAO;
import com.sausage.app.dao.User.UserDAO;
import com.sausage.app.domain.employee.onboarding.onboardingPerson.OnboardingPerson;
import com.sausage.app.entity.ApplicationWorkFlow;
import com.sausage.app.entity.Employee;
import com.sausage.app.entity.Person;
import com.sausage.app.entity.User;
import com.sausage.app.service.employee.onboarding.EmployeeOnboardingPersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static com.sausage.app.dao.ApplicationWorkFlow.enums.ApplicationWorkFlowStatusEnums.ONBOARDING;
import static com.sausage.app.dao.ApplicationWorkFlow.enums.ApplicationWorkFlowNotifyEnums.NOT_NOTIFIED;
import static com.sausage.app.dao.ApplicationWorkFlow.enums.ApplicationWorkFlowUploadEnums.REQUIRE;
import static com.sausage.app.dao.ApplicationWorkFlow.enums.ApplicationWorkFlowTypeEnums.ONBOARDING_TYPE;

@Service
public class EmployeeOnboardingPersonServiceImpl implements EmployeeOnboardingPersonService {

    private UserDAO userDAO;

    private PersonDAO personDAO;

    private EmployeeDAO employeeDAO;

    private ApplicationWorkFlowDAO applicationWorkFlowDAO;

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
    public void setApplicationWorkFlowDAO(ApplicationWorkFlowDAO applicationWorkFlowDAO) {
        this.applicationWorkFlowDAO = applicationWorkFlowDAO;
    }

    private Person getPersonByUserId(int userId) {
        User user = userDAO.getUserById(userId);
        int personId = user.getPersonId();
        return personDAO.getPersonById(personId);
    }

    @Override
    @Transactional
    public OnboardingPerson getOnboardingPerson(int userId) {
        Person person = getPersonByUserId(userId);
        if (person != null) {
            Employee employee = employeeDAO.getEmployeeByPerson(person);
            LocalDateTime now = LocalDateTime.now();
            DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
            String formatDateTime = now.format(format);
            ApplicationWorkFlow applicationWorkFlow = applicationWorkFlowDAO.getApplicationWorkFlowByEmployee(employee);
            if (applicationWorkFlow == null) {
                applicationWorkFlow = ApplicationWorkFlow.builder()
                        .employee(employee)
                        .createdDate(formatDateTime)
                        .modificationDate(formatDateTime)
                        .status(ONBOARDING.getValue())
                        .comments(null)
                        .type(ONBOARDING_TYPE.getStr())
                        .upload(REQUIRE.getValue())
                        .notify(NOT_NOTIFIED.getValue())
                        .build();
                applicationWorkFlowDAO.setApplicationWorkFlow(applicationWorkFlow);
            }

            return OnboardingPerson.builder()
                    .firstName(person.getFirstName())
                    .middleName(person.getMiddleName())
                    .lastName(person.getLastName())
                    .email(person.getEmail())
                    .cellPhone(person.getCellphone())
                    .alternatePhone(person.getAlternatePhone())
                    .gender(person.getGender())
                    .ssn(person.getSSN())
                    .dob(person.getDOB())
                    .build();
        } else {
            return null;
        }
    }

    @Override
    @Transactional
    public void setOnboardingPerson(int userId, OnboardingPerson onboardingPerson) {
        Person person = getPersonByUserId(userId);
        person.setFirstName(onboardingPerson.getFirstName());
        person.setMiddleName(onboardingPerson.getMiddleName());
        person.setLastName(onboardingPerson.getLastName());
        person.setEmail(onboardingPerson.getEmail());
        person.setCellphone(onboardingPerson.getCellPhone());
        person.setAlternatePhone(onboardingPerson.getAlternatePhone());
        person.setGender(onboardingPerson.getGender());
        person.setSSN(onboardingPerson.getSsn());
        person.setDOB(onboardingPerson.getDob());
        personDAO.setPerson(person);
    }

}

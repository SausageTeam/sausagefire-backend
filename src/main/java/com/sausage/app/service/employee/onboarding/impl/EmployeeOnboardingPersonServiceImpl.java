package com.sausage.app.service.employee.onboarding.impl;

import com.sausage.app.dao.ApplicationWorkFlow.ApplicationWorkFlowDAO;
import com.sausage.app.dao.Person.PersonDAO;
import com.sausage.app.dao.User.UserDAO;
import com.sausage.app.domain.employee.onboarding.onboardingPerson.OnboardingPerson;
import com.sausage.app.entity.Person;
import com.sausage.app.entity.User;
import com.sausage.app.service.employee.onboarding.EmployeeOnboardingPersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class EmployeeOnboardingPersonServiceImpl implements EmployeeOnboardingPersonService {

    private UserDAO userDAO;

    private PersonDAO personDAO;

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
    public void postOnboardingPerson(int userId, OnboardingPerson onboardingPerson) {
        Person person = Person.builder()
                .firstName(onboardingPerson.getFirstName())
                .middleName(onboardingPerson.getMiddleName())
                .lastName(onboardingPerson.getLastName())
                .email(onboardingPerson.getEmail())
                .cellphone(onboardingPerson.getCellPhone())
                .alternatePhone(onboardingPerson.getAlternatePhone())
                .gender(onboardingPerson.getGender())
                .SSN(onboardingPerson.getSsn())
                .DOB(onboardingPerson.getDob())
                .build();
        personDAO.setPerson(person);
    }

}

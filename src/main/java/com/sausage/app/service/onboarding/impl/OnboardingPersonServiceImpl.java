package com.sausage.app.service.onboarding.impl;

import com.sausage.app.dao.Person.PersonDAO;
import com.sausage.app.dao.user.UserDAO;
import com.sausage.app.domain.onboarding.onboardingPerson.OnboardingPerson;
import com.sausage.app.domain.onboarding.onboardingPerson.OnboardingPersonRequest;
import com.sausage.app.entity.Person;
import com.sausage.app.entity.User;
import com.sausage.app.service.onboarding.OnboardingPersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class OnboardingPersonServiceImpl implements OnboardingPersonService {

    private UserDAO userDAO;

    private PersonDAO personDAO;

    @Autowired
    public void setUserDAO(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    @Autowired
    public void setPersonDAO(PersonDAO personDAO) {
        this.personDAO = personDAO;
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
            OnboardingPerson onboardingPerson = new OnboardingPerson();
            onboardingPerson.setEmail(person.getEmail());
            return onboardingPerson;
        } else {
            return null;
        }
    }

    @Override
    @Transactional
    public void postOnboardingPerson(int userId, OnboardingPersonRequest onboardingPersonRequest) {
        Person person = getPersonByUserId(userId);
        person.setFirstName(onboardingPersonRequest.getFirstName());
        person.setLastName(onboardingPersonRequest.getLastName());
        person.setMiddleName(onboardingPersonRequest.getMiddleName());
        person.setEmail(onboardingPersonRequest.getEmail());
        person.setCellphone(onboardingPersonRequest.getCellPhone());
        person.setAlternatePhone(onboardingPersonRequest.getCellPhone());
        person.setGender(onboardingPersonRequest.getGender());
        person.setSSN(onboardingPersonRequest.getSSN());
        person.setDOB(onboardingPersonRequest.getDOB());
        personDAO.updatePerson(person);
    }


}

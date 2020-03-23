package com.sausage.app.service.profile.impl;

import com.sausage.app.dao.Employee.EmployeeDAO;
import com.sausage.app.dao.Person.PersonDAO;
import com.sausage.app.dao.User.UserDAO;
import com.sausage.app.domain.profile.profileContact.ProfileContact;
import com.sausage.app.entity.Person;
import com.sausage.app.entity.User;
import com.sausage.app.service.profile.ProfileContactService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProfileContactServiceImpl implements ProfileContactService {

    private UserDAO userDAO;

    private PersonDAO personDAO;

    private EmployeeDAO employeeDAO;

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

    private Person getPersonByUserId(int userId) {
        User user = userDAO.getUserById(userId);
        return personDAO.getPersonById(user.getPersonId());
    }

    @Override
    public ProfileContact getProfileContact(int userId) {
        Person person = getPersonByUserId(userId);
        return ProfileContact.builder()
                .personalEmail(person.getEmail())
                .cellPhone(person.getCellphone())
                .alternatePhone(person.getAlternatePhone())
                .build();
    }

    @Override
    public void setProfileContact(int userId, ProfileContact profileContact) {
        Person person = getPersonByUserId(userId);
        person.setEmail(profileContact.getPersonalEmail());
        person.setCellphone(profileContact.getCellPhone());
        person.setAlternatePhone(profileContact.getAlternatePhone());
        personDAO.setPerson(person);
    }
}

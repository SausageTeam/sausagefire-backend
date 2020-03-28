package com.sausage.app.service.employee.profile.impl;

import com.sausage.app.dao.Person.PersonDAO;
import com.sausage.app.dao.User.UserDAO;
import com.sausage.app.domain.employee.profile.profileContact.ProfileContact;
import com.sausage.app.entity.Person;
import com.sausage.app.entity.User;
import com.sausage.app.service.employee.profile.EmployeeProfileContactService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class EmployeeProfileContactServiceImpl implements EmployeeProfileContactService {

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

    @Override
    @Transactional
    public ProfileContact getProfileContact(int userId) {
        User user = userDAO.getUserById(userId);
        Person person = user.getPerson();
        return ProfileContact.builder()
                .personalEmail(person.getEmail())
                .cellPhone(person.getCellphone())
                .alternatePhone(person.getAlternatePhone())
                .build();
    }

    @Override
    @Transactional
    public void setProfileContact(int userId, ProfileContact profileContact) {
        User user = userDAO.getUserById(userId);
        Person person = user.getPerson();
        person.setEmail(profileContact.getPersonalEmail());
        person.setCellphone(profileContact.getCellPhone());
        person.setAlternatePhone(profileContact.getAlternatePhone());
        personDAO.setPerson(person);
    }
}

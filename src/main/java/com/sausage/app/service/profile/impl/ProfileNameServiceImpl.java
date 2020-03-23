package com.sausage.app.service.profile.impl;

import com.sausage.app.constant.Constant;
import com.sausage.app.dao.Employee.EmployeeDAO;
import com.sausage.app.dao.Person.PersonDAO;
import com.sausage.app.dao.User.UserDAO;
import com.sausage.app.domain.profile.profileName.ProfileName;
import com.sausage.app.entity.Employee;
import com.sausage.app.entity.Person;
import com.sausage.app.entity.User;
import com.sausage.app.fileIO.fileInput;
import com.sausage.app.fileIO.fileOutput;
import com.sausage.app.service.profile.ProfileNameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.File;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Service
public class ProfileNameServiceImpl implements ProfileNameService {

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
    @Transactional
    public ProfileName getProfileName(int userId) {
        Person person = getPersonByUserId(userId);
        Employee employee = employeeDAO.getEmployeeByPerson(person);
        String firstName = person.getFirstName();
        String middleName = person.getMiddleName();
        String lastName = person.getLastName();
        String preferredName = person.getPreferredName();

        String avatarPath = String.format(Constant.EMPLOYEE_AVATAR_PATH, employee.getId());
        File avatar = fileOutput.getAvatar(avatarPath);

        String dob = person.getDOB();
        DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate localDate = LocalDate.parse(dob , format);
        int dob_year = localDate.getYear();
        LocalDateTime now = LocalDateTime.now();
        int now_year = now.getYear();
        int age = now_year - dob_year;

        String gender = person.getGender();
        String ssn = person.getSSN();

        return ProfileName.builder()
                .firstName(firstName)
                .middleName(middleName)
                .lastName(lastName)
                .preferredName(preferredName)
                .avatar(avatar)
                .dob(dob)
                .age(age)
                .gender(gender)
                .ssn(ssn)
                .build();
    }

    @Override
    @Transactional
    public void setProfileName(int userId, ProfileName profileName) {
        Person person = getPersonByUserId(userId);
        Employee employee = employeeDAO.getEmployeeByPerson(person);

        String firstName = profileName.getFirstName();
        String middleName = profileName.getMiddleName();
        String lastName = profileName.getLastName();
        String preferredName = profileName.getPreferredName();
        File avatar = profileName.getAvatar();
        String dob = profileName.getDob();
        int age = profileName.getAge();
        String gender = profileName.getGender();
        String ssn = profileName.getSsn();

        person.setFirstName(firstName);
        person.setMiddleName(middleName);
        person.setLastName(lastName);
        person.setPreferredName(preferredName);
        person.setDOB(dob);
        person.setGender(gender);
        person.setSSN(ssn);
        personDAO.setPerson(person);

        String avatarPath = String.format(Constant.EMPLOYEE_AVATAR_PATH, employee.getId());
        fileInput.setAvatar(avatarPath, avatar);
    }

}

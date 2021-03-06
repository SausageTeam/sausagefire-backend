package com.sausage.app.service.employee.profile.impl;

import com.sausage.app.dao.Employee.EmployeeDAO;
import com.sausage.app.dao.Person.PersonDAO;
import com.sausage.app.dao.User.UserDAO;
import com.sausage.app.domain.employee.profile.profileName.ProfileName;
import com.sausage.app.entity.Employee;
import com.sausage.app.entity.Person;
import com.sausage.app.entity.User;
import com.sausage.app.fileIO.URIHandler;
import com.sausage.app.service.employee.profile.EmployeeProfileNameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Service
public class EmployeeProfileNameServiceImpl implements EmployeeProfileNameService {

    private UserDAO userDAO;

    private PersonDAO personDAO;

    private EmployeeDAO employeeDAO;

    private URIHandler uriHandler;

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
    public void setUriHandler(URIHandler uriHandler) {
        this.uriHandler = uriHandler;
    }

    @Override
    @Transactional
    public ProfileName getProfileName(int userId) {
        try {
            User user = userDAO.getUserById(userId);
            Person person = user.getPerson();
            Employee employee = employeeDAO.getEmployeeByPerson(person);
            String firstName = person.getFirstName();
            String middleName = person.getMiddleName();
            String lastName = person.getLastName();
            String preferredName = person.getPreferredName();

            String uri = uriHandler.getUri(String.valueOf(employee.getId()), "avatar.jpg");

            String dob = person.getDOB();
            DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            LocalDate localDate = LocalDate.parse(dob, format);
            int dob_year = localDate.getYear();
            LocalDate now = LocalDate.now();
            int now_year = now.getYear();
            int age = now_year - dob_year;

            String gender = person.getGender();
            String ssn = person.getSSN();

            return ProfileName.builder()
                    .firstName(firstName)
                    .middleName(middleName)
                    .lastName(lastName)
                    .preferredName(preferredName)
                    .avatarUri(uri)
                    .dob(dob)
                    .age(age)
                    .gender(gender)
                    .ssn(ssn)
                    .build();
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    @Transactional
    public void setProfileName(int userId, ProfileName profileName) {
        User user = userDAO.getUserById(userId);
        Person person = user.getPerson();

        String firstName = profileName.getFirstName();
        String middleName = profileName.getMiddleName();
        String lastName = profileName.getLastName();
        String preferredName = profileName.getPreferredName();
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
    }

}

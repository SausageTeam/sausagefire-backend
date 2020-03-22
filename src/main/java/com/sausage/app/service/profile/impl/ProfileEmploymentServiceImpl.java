package com.sausage.app.service.profile.impl;

import com.sausage.app.dao.Employee.EmployeeDAO;
import com.sausage.app.dao.Person.PersonDAO;
import com.sausage.app.dao.VisaStatus.VisaStatusDAO;
import com.sausage.app.dao.user.UserDAO;
import com.sausage.app.domain.profile.profileEmployment.ProfileEmployment;
import com.sausage.app.entity.Employee;
import com.sausage.app.entity.Person;
import com.sausage.app.entity.User;
import com.sausage.app.entity.VisaStatus;
import com.sausage.app.service.profile.ProfileEmploymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProfileEmploymentServiceImpl implements ProfileEmploymentService {

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
    public ProfileEmployment getProfileEmployment(int userId) {
        Employee employee = getEmployeeByUserId(userId);
        return ProfileEmployment.builder()
                .title(employee.getTitle())
                .startDate(employee.getStartDate())
                .endDate(employee.getEndDate())
                .visaType(employee.getVisaStatus().getVisaType())
                .visaStartDate(employee.getVisaStartDate())
                .visaEndDate(employee.getVisaEndDate())
                .build();
    }

    @Override
    public void setProfileEmployment(int userId, ProfileEmployment profileEmployment) {
        Employee employee = getEmployeeByUserId(userId);

        VisaStatus visaStatus = employee.getVisaStatus();
        visaStatus.setVisaType(profileEmployment.getVisaType());
        visaStatus = visaStatusDAO.setVisaStatus(visaStatus);

        employee.setTitle(profileEmployment.getTitle());
        employee.setStartDate(profileEmployment.getStartDate());
        employee.setEndDate(profileEmployment.getEndDate());
        employee.setVisaStatus(visaStatus);
        employee.setVisaStartDate(profileEmployment.getVisaStartDate());
        employee.setVisaEndDate(profileEmployment.getVisaEndDate());
        employeeDAO.setEmployee(employee);
    }
}

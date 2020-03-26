package com.sausage.app.service.employee.profile.impl;

import com.sausage.app.dao.Person.PersonDAO;
import com.sausage.app.dao.visaStatus.VisaStatusDAO;
import com.sausage.app.dao.user.UserDAO;
import com.sausage.app.domain.employee.profile.profileEmployment.ProfileEmployment;
import com.sausage.app.entity.Employee;
import com.sausage.app.entity.Person;
import com.sausage.app.entity.User;
import com.sausage.app.entity.VisaStatus;
import com.sausage.app.service.employee.profile.EmployeeProfileEmploymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Service
public class EmployeeProfileEmploymentServiceImpl implements EmployeeProfileEmploymentService {

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
    public ProfileEmployment getProfileEmployment(int userId) {
        Employee employee = getEmployeeByUserId(userId);
        VisaStatus visaStatus = visaStatusDAO.getVisaStatusById(employee.getVisaStatusId());

        return ProfileEmployment.builder()
                .title(employee.getTitle())
                .startDate(employee.getStartDate())
                .endDate(employee.getEndDate())
                .visaType(visaStatus.getVisaType())
                .visaStartDate(employee.getVisaStartDate())
                .visaEndDate(employee.getVisaEndDate())
                .build();
    }

    @Override
    @Transactional
    public void setProfileEmployment(int userId, ProfileEmployment profileEmployment) {
        Employee employee = getEmployeeByUserId(userId);

        String visaType = profileEmployment.getVisaType();
        VisaStatus visaStatus = visaStatusDAO.setOtherVisaStatus(visaType);
        if (visaStatus == null) {
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

        employee.setTitle(profileEmployment.getTitle());
        employee.setStartDate(profileEmployment.getStartDate());
        employee.setEndDate(profileEmployment.getEndDate());
        employee.setVisaStatusId(visaStatus.getId());
        employee.setVisaStartDate(profileEmployment.getVisaStartDate());
        employee.setVisaEndDate(profileEmployment.getVisaEndDate());
        employeeDAO.setEmployee(employee);
    }
}

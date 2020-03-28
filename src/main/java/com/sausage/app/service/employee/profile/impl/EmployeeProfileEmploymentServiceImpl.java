package com.sausage.app.service.employee.profile.impl;

import com.sausage.app.dao.Employee.EmployeeDAO;
import com.sausage.app.dao.User.UserDAO;
import com.sausage.app.dao.VisaStatus.VisaStatusDAO;
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

import static com.sausage.app.constant.Constant.ACTIVE_FLAG;

@Service
public class EmployeeProfileEmploymentServiceImpl implements EmployeeProfileEmploymentService {

    private UserDAO userDAO;

    private EmployeeDAO employeeDAO;

    private VisaStatusDAO visaStatusDAO;

    @Autowired
    public void setUserDAO(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    @Autowired
    public void setEmployeeDAO(EmployeeDAO employeeDAO) {
        this.employeeDAO = employeeDAO;
    }

    @Autowired
    public void setVisaStatusDAO(VisaStatusDAO visaStatusDAO) {
        this.visaStatusDAO = visaStatusDAO;
    }

    @Override
    @Transactional
    public ProfileEmployment getProfileEmployment(int userId) {
        User user = userDAO.getUserById(userId);
        Person person = user.getPerson();
        Employee employee = employeeDAO.getEmployeeByPerson(person);
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
        User user = userDAO.getUserById(userId);
        Person person = user.getPerson();
        Employee employee = employeeDAO.getEmployeeByPerson(person);

        String visaType = profileEmployment.getVisaType();
        VisaStatus visaStatus = visaStatusDAO.setOtherVisaStatus(visaType);
        if (visaStatus == null) {
            LocalDateTime now = LocalDateTime.now();
            DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
            String formatDateTime = now.format(format);
            visaStatus = VisaStatus.builder()
                    .visaType(visaType)
                    .activeFlag(ACTIVE_FLAG)
                    .createdDateTime(formatDateTime)
                    .modificationDateTime(formatDateTime)
                    .createdUser(userId)
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

package com.sausage.app.service.employee.onboarding.impl;

import com.sausage.app.constant.Constant;
import com.sausage.app.dao.Person.PersonDAO;
import com.sausage.app.dao.Employee.EmployeeDAO;
import com.sausage.app.dao.User.UserDAO;
import com.sausage.app.domain.employee.onboarding.onboardingAvatar.OnboardingAvatar;
import com.sausage.app.entity.Employee;
import com.sausage.app.entity.Person;
import com.sausage.app.entity.User;
import com.sausage.app.fileIO.FileOutput;
import com.sausage.app.fileIO.URIConvert;
import com.sausage.app.service.employee.onboarding.EmployeeOnboardingAvatarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.File;
import java.net.URI;

@Service
public class EmployeeOnboardingAvatarServiceImpl implements EmployeeOnboardingAvatarService {

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

    private Employee getEmployeeByUserId(int userId) {
        User user = userDAO.getUserById(userId);
        Person person = personDAO.getPersonById(user.getPersonId());
        return employeeDAO.getEmployeeByPerson(person);
    }

    @Override
    @Transactional
    public OnboardingAvatar getOnboardingAvatar(int userId) {
        Employee employee = getEmployeeByUserId(userId);
        String avatarPath = String.format(Constant.DEFAULT_FILE_PATH, employee.getId(), "avatar.jpg");
        FileOutput.getAvatar(avatarPath);
        String uri = URIConvert.getUri(employee.getId(), "avatar.jpg");
        return OnboardingAvatar.builder()
                .avatarUri(uri)
                .build();
    }

    @Override
    @Transactional
    public void setOnboardingAvatar(int userId, OnboardingAvatar onboardingAvatar) {
//        File avatar = onboardingAvatar.getAvatar();
//        Employee employee = getEmployeeByUserId(userId);
//        String avatarPath = String.format(Constant.EMPLOYEE_AVATAR_PATH, employee.getId());
//        FileInput.setAvatar(avatarPath, avatar);
//        employee.setAvatar(avatarPath);
//        employeeDAO.setEmployee(employee);
    }


}

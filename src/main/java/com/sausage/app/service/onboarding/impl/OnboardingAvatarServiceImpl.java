package com.sausage.app.service.onboarding.impl;

import com.sausage.app.constant.Constant;
import com.sausage.app.dao.Person.PersonDAO;
import com.sausage.app.dao.Employee.EmployeeDAO;
import com.sausage.app.dao.user.UserDAO;
import com.sausage.app.domain.onboarding.onboardingAvatar.OnboardingAvatar;
import com.sausage.app.entity.Employee;
import com.sausage.app.entity.Person;
import com.sausage.app.entity.User;
import com.sausage.app.fileIO.fileInput;
import com.sausage.app.fileIO.fileOutput;
import com.sausage.app.service.onboarding.OnboardingAvatarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.File;

@Service
public class OnboardingAvatarServiceImpl implements OnboardingAvatarService {

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
    public OnboardingAvatar getOnboardingAvatar(int userId) {
        OnboardingAvatar onboardingAvatar = new OnboardingAvatar();

        Employee employee = getEmployeeByUserId(userId);
        String avatarPath = String.format(Constant.EMPLOYEE_AVATAR_PATH, employee.getId());
        File avatar = fileOutput.getAvatar(avatarPath);
        onboardingAvatar.setAvatar(avatar);

        return onboardingAvatar;
    }

    @Override
    @Transactional
    public void setOnboardingAvatar(int userId, OnboardingAvatar onboardingAvatar) {
        File avatar = onboardingAvatar.getAvatar();
        Employee employee = getEmployeeByUserId(userId);
        String avatarPath = String.format(Constant.EMPLOYEE_AVATAR_PATH, employee.getId());
        fileInput.setAvatar(avatarPath, avatar);
        employee.setAvatar(avatarPath);
        employeeDAO.setEmployee(employee);
    }


}

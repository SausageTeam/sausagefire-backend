package com.sausage.app.service.onboarding.impl;

import com.sausage.app.dao.Person.PersonDAO;
import com.sausage.app.dao.employee.EmployeeDAO;
import com.sausage.app.dao.user.UserDAO;
import com.sausage.app.domain.onboarding.onboardingAvatar.OnboardingAvatar;
import com.sausage.app.entity.Employee;
import com.sausage.app.service.onboarding.OnboardingAvatarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

    @Override
    public OnboardingAvatar getOnboardingAvatar(int userId) {
        return null;
    }

}

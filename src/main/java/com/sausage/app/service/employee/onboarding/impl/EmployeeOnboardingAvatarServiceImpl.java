package com.sausage.app.service.employee.onboarding.impl;

import com.sausage.app.constant.Constant;
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
import org.springframework.web.multipart.MultipartFile;

@Service
public class EmployeeOnboardingAvatarServiceImpl implements EmployeeOnboardingAvatarService {

    private UserDAO userDAO;

    private EmployeeDAO employeeDAO;

    private URIConvert uriConvert;

    @Autowired
    public void setUserDAO(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    @Autowired
    public void setEmployeeDAO(EmployeeDAO employeeDAO) {
        this.employeeDAO = employeeDAO;
    }

    @Autowired
    public void setUriConvert(URIConvert uriConvert) {
        this.uriConvert = uriConvert;
    }

    @Override
    @Transactional
    public OnboardingAvatar getOnboardingAvatar(int userId) {
        try {
            User user = userDAO.getUserById(userId);
            Person person = user.getPerson();
            Employee employee = employeeDAO.getEmployeeByPerson(person);
            String avatarPath = String.format(Constant.DEFAULT_FILE_PATH, employee.getId(), "avatar.jpg");
            FileOutput.getAvatar(avatarPath);
            String uri = uriConvert.getUri(String.valueOf(employee.getId()), "avatar.jpg");
            return OnboardingAvatar.builder()
                    .avatarUri(uri)
                    .build();
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    @Transactional
    public void setOnboardingAvatar(int userId, MultipartFile file) {
        User user = userDAO.getUserById(userId);
        Person person = user.getPerson();
        Employee employee = employeeDAO.getEmployeeByPerson(person);
        uriConvert.storeFile(employee.getId(), file);
    }

}

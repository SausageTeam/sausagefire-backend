package com.sausage.app.service.employee.onboarding.impl;

import com.sausage.app.dao.Employee.EmployeeDAO;
import com.sausage.app.dao.Person.PersonDAO;
import com.sausage.app.dao.PersonalDocument.PersonalDocumentDAO;
import com.sausage.app.dao.User.UserDAO;
import com.sausage.app.domain.employee.onboarding.onboardingDriving.OnboardingDriving;
import com.sausage.app.entity.Employee;
import com.sausage.app.entity.Person;
import com.sausage.app.entity.PersonalDocument;
import com.sausage.app.entity.User;
import com.sausage.app.fileIO.FileInput;
import com.sausage.app.fileIO.FileOutput;
import com.sausage.app.service.employee.onboarding.EmployeeOnboardingDrivingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.File;

import static com.sausage.app.constant.Constant.*;

@Service
public class EmployeeOnboardingDrivingServiceImpl implements EmployeeOnboardingDrivingService {

    private UserDAO userDAO;

    private PersonDAO personDAO;

    private EmployeeDAO employeeDAO;

    private PersonalDocumentDAO personalDocumentDAO;

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
    public void setPersonalDocumentDAO(PersonalDocumentDAO personalDocumentDAO) {
        this.personalDocumentDAO = personalDocumentDAO;
    }

    private Employee getEmployeeByUserId(int userId) {
        User user = userDAO.getUserById(userId);
        Person person = personDAO.getPersonById(user.getPersonId());
        return employeeDAO.getEmployeeByPerson(person);
    }

    @Override
    @Transactional
    public OnboardingDriving getOnboardingDriving(int userId) {
        Employee employee = getEmployeeByUserId(userId);
        String driverLicense = employee.getDriverLicense();
        String driverLicenseExpirationDate = employee.getDriverLicenseExpirationDate();
        String car = employee.getCar();
        String[] arr_car = car.split("_");

        return OnboardingDriving.builder()
                .driverLicense(driverLicense)
                .driverLicenseExpirationDate(driverLicenseExpirationDate)
                .driverLicenseDoc(null)
                .maker(arr_car[0])
                .model(arr_car[1])
                .color(arr_car[2])
                .build();
    }

    @Override
    @Transactional
    public void setOnboardingDriving(int userId, OnboardingDriving onboardingDriving) {
        String car = String.format("%s_%s_%s", onboardingDriving.getMaker(), onboardingDriving.getModel(), onboardingDriving.getColor());
        Employee employee = getEmployeeByUserId(userId);
        employee.setDriverLicense(onboardingDriving.getDriverLicense());
        employee.setDriverLicenseExpirationDate(onboardingDriving.getDriverLicenseExpirationDate());
        employee.setCar(car);
        employeeDAO.setEmployee(employee);
    }

}

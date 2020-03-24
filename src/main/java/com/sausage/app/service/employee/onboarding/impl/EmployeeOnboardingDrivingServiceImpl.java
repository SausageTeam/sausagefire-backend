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
import com.sausage.app.fileIO.fileInput;
import com.sausage.app.fileIO.fileOutput;
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

        PersonalDocument personalDocument = personalDocumentDAO.getPersonalDrivingDocumentByEmployee(employee);
        String driverLicenseDocPath = personalDocument.getPath();
        File driverLicenseDoc = fileOutput.getFile(driverLicenseDocPath);

        return OnboardingDriving.builder()
                .driverLicense(driverLicense)
                .driverLicenseExpirationDate(driverLicenseExpirationDate)
                .driverLicenseDoc(driverLicenseDoc)
                .car(car)
                .build();
    }

    @Override
    @Transactional
    public void setOnboardingDriving(int userId, OnboardingDriving onboardingDriving) {
        Employee employee = getEmployeeByUserId(userId);
        employee.setDriverLicense(onboardingDriving.getDriverLicense());
        employee.setDriverLicenseExpirationDate(onboardingDriving.getDriverLicenseExpirationDate());
        employee.setCar(onboardingDriving.getCar());
        employeeDAO.setEmployee(employee);

        String driverLicenseDocPath = String.format(FILE_PATH, employee.getId(), EMPLOYEE_DRIVER_LICENSE_TITLE);
        fileInput.setFile(driverLicenseDocPath, onboardingDriving.getDriverLicenseDoc());
        PersonalDocument personalDocument = personalDocumentDAO.getPersonalDrivingDocumentByEmployee(employee);
        personalDocument.setPath(driverLicenseDocPath);
        personalDocumentDAO.setPersonalDocument(personalDocument);
    }

}

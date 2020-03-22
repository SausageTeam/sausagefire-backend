package com.sausage.app.service.onboarding.impl;

import com.sausage.app.dao.Employee.EmployeeDAO;
import com.sausage.app.dao.Person.PersonDAO;
import com.sausage.app.dao.PersonalDocument.PersonalDocumentDAO;
import com.sausage.app.dao.user.UserDAO;
import com.sausage.app.domain.onboarding.onboardingDriving.OnboardingDriving;
import com.sausage.app.entity.Employee;
import com.sausage.app.entity.Person;
import com.sausage.app.entity.PersonalDocument;
import com.sausage.app.entity.User;
import com.sausage.app.fileIO.fileInput;
import com.sausage.app.fileIO.fileOutput;
import com.sausage.app.service.onboarding.OnboardingDrivingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.File;

import static com.sausage.app.constant.Constant.*;

@Service
public class OnboardingDrivingServiceImpl implements OnboardingDrivingService {

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

        PersonalDocument personalDocument = personalDocumentDAO.getPersonalDocumentByEmployeeId(employee.getId());
        String driverLicenseDocPath = personalDocument.getPath();
        File driverLicenseDoc = fileOutput.getFile(driverLicenseDocPath);

        return OnboardingDriving.builder()
                .driverLicense(driverLicense)
                .driverLicenseExpirationDate(driverLicenseExpirationDate)
                .driverLicenseDoc(driverLicenseDoc)
                .build();
    }

    @Override
    @Transactional
    public void setOnboardingDriving(int userId, OnboardingDriving onboardingDriving) {
        Employee employee = getEmployeeByUserId(userId);
        employee.setDriverLicense(onboardingDriving.getDriverLicense());
        employee.setDriverLicenseExpirationDate(onboardingDriving.getDriverLicenseExpirationDate());
        employeeDAO.setEmployee(employee);

        String driverLicenseDocPath = String.format(EMPLOYEE_DRIVER_LICENSE_PATH, employee.getId());
        fileInput.setFile(driverLicenseDocPath, onboardingDriving.getDriverLicenseDoc());
        PersonalDocument personalDocument = personalDocumentDAO.getPersonalDocumentByEmployeeId(employee.getId());
        personalDocument.setPath(driverLicenseDocPath);
        personalDocumentDAO.setPersonalDocument(personalDocument);
    }

}

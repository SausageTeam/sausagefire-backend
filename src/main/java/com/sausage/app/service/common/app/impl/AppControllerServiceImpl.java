package com.sausage.app.service.common.app.impl;

import com.sausage.app.dao.Employee.EmployeeDAO;
import com.sausage.app.dao.Person.PersonDAO;
import com.sausage.app.dao.User.UserDAO;
import com.sausage.app.domain.common.app.AppDomain;
import com.sausage.app.entity.Employee;
import com.sausage.app.entity.Person;
import com.sausage.app.entity.User;
import com.sausage.app.fileIO.URIConvert;
import com.sausage.app.service.common.app.AppControllerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AppControllerServiceImpl implements AppControllerService {

    private UserDAO userDAO;

    private PersonDAO personDAO;

    private EmployeeDAO employeeDAO;

    private URIConvert uriConvert;

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

    private Employee getEmployeeByUserId(int userId){
        User user = userDAO.getUserById(userId);
        Person person = personDAO.getPersonById(user.getPersonId());
        return employeeDAO.getEmployeeByPerson(person);
    }

    @Autowired
    public void setUriConvert(URIConvert uriConvert) {
        this.uriConvert = uriConvert;
    }

    @Override
    public AppDomain getAppDomain(int userId) {
        Employee employee = getEmployeeByUserId(userId);
        String uri = uriConvert.getUri(employee.getId(), "avatar.jpg");
        return AppDomain.builder()
                .avatarUri(uri)
                .build();
    }

}

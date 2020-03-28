package com.sausage.app.service.common.auth.impl;

import com.sausage.app.constant.enums.ApplicationWorkFlow.ApplicationWorkFlowTypeEnums;
import com.sausage.app.dao.ApplicationWorkFlow.ApplicationWorkFlowDAO;
import com.sausage.app.dao.Employee.EmployeeDAO;
import com.sausage.app.dao.Person.PersonDAO;
import com.sausage.app.dao.User.UserDAO;
import com.sausage.app.dao.UserRole.UserRoleDAO;
import com.sausage.app.domain.common.auth.Auth;
import com.sausage.app.entity.*;
import com.sausage.app.service.common.auth.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
public class AuthServiceImpl implements AuthService {

    UserDAO userDAO;

    PersonDAO personDAO;

    EmployeeDAO employeeDAO;

    ApplicationWorkFlowDAO applicationWorkFlowDAO;

    UserRoleDAO userRoleDAO;

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
    public void setApplicationWorkFlowDAO(ApplicationWorkFlowDAO applicationWorkFlowDAO) {
        this.applicationWorkFlowDAO = applicationWorkFlowDAO;
    }

    @Autowired
    public void setUserRoleDAO(UserRoleDAO userRoleDAO) {
        this.userRoleDAO = userRoleDAO;
    }

    @Override
    @Transactional
    public Auth getAuth(int userId) {
        try {
            User user = userDAO.getUserById(userId);
            Person person = user.getPerson();
            Employee employee = employeeDAO.getEmployeeByPerson(person);
            ApplicationWorkFlow applicationWorkFlow = applicationWorkFlowDAO.getApplicationWorkFlowByEmployee(employee);
            String type = applicationWorkFlow.getType();
            int applicationWorkFlowType = ApplicationWorkFlowTypeEnums.valueOf(type).getValue();
            UserRole userRole = userRoleDAO.getUserRoleByUserId(userId);
            return Auth.builder()
                    .applicationWorkFlowType(applicationWorkFlowType)
                    .roleId(userRole.getRoleId())
                    .build();
        } catch (NullPointerException e) {
            return null;
        }
    }

}

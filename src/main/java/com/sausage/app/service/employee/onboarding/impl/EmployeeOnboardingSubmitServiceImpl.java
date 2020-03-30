package com.sausage.app.service.employee.onboarding.impl;

import com.sausage.app.dao.ApplicationWorkFlow.ApplicationWorkFlowDAO;
import com.sausage.app.dao.Employee.EmployeeDAO;
import com.sausage.app.dao.User.UserDAO;
import com.sausage.app.entity.ApplicationWorkFlow;
import com.sausage.app.entity.Employee;
import com.sausage.app.entity.Person;
import com.sausage.app.entity.User;
import com.sausage.app.service.employee.onboarding.EmployeeOnboardingSubmitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static com.sausage.app.constant.enums.ApplicationWorkFlow.ApplicationWorkFlowOnboardingStatusEnums.WAITING;

@Service
public class EmployeeOnboardingSubmitServiceImpl implements EmployeeOnboardingSubmitService {

    private UserDAO userDAO;

    private EmployeeDAO employeeDAO;

    ApplicationWorkFlowDAO applicationWorkFlowDAO;

    @Autowired
    public void setUserDAO(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    @Autowired
    public void setEmployeeDAO(EmployeeDAO employeeDAO) {
        this.employeeDAO = employeeDAO;
    }

    @Autowired
    public void setApplicationWorkFlowDAO(ApplicationWorkFlowDAO applicationWorkFlowDAO) {
        this.applicationWorkFlowDAO = applicationWorkFlowDAO;
    }

    @Override
    @Transactional
    public void setEmployeeOnboardingSubmit(int userId) {
        User user = userDAO.getUserById(userId);
        Person person = user.getPerson();
        Employee employee = employeeDAO.getEmployeeByPerson(person);
        ApplicationWorkFlow applicationWorkFlow = applicationWorkFlowDAO.getApplicationWorkFlowByEmployee(employee);
        applicationWorkFlow.setStatus(WAITING.getValue());
        applicationWorkFlowDAO.setApplicationWorkFlow(applicationWorkFlow);
    }

}

package com.sausage.app.dao.employee.impl;

import com.sausage.app.dao.AbstractHibernateDAO;
import com.sausage.app.dao.employee.EmployeeDAO;
import com.sausage.app.entity.Employee;
import org.springframework.stereotype.Repository;

@Repository
public class EmployeeDAOImpl extends AbstractHibernateDAO<Employee> implements EmployeeDAO {
    public EmployeeDAOImpl() { setClazz(Employee.class);}
}

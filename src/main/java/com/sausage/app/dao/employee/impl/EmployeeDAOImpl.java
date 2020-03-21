package com.sausage.app.dao.employee.impl;

import com.sausage.app.dao.AbstractHibernateDAO;
import com.sausage.app.dao.employee.EmployeeDAO;
import com.sausage.app.entity.Employee;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class EmployeeDAOImpl extends AbstractHibernateDAO<Employee> implements EmployeeDAO {
    private static final String GET_EMPLOYEE = "FROM Employee WHERE personID = :personID";
    private static final String GET_EMPLOYEE_FROM_THE_HOUSE = "FROM Employee WHERE houseID = :houseID";

    public EmployeeDAOImpl() { setClazz(Employee.class);}

    @Override
    public Employee getEmployee(int personID) {
        Session session = getCurrentSession();
        Query query = session.createQuery(GET_EMPLOYEE);
        query.setParameter("personID", personID);
        List<Employee> employeeList = query.getResultList();
        return employeeList.size() > 0 ? employeeList.get(0) : null;
    }

    @Override
    public List<Employee> getEmployeesFromTheHouse(int houseID) {
        Session session = getCurrentSession();
        Query query = session.createQuery(GET_EMPLOYEE_FROM_THE_HOUSE);
        query.setParameter("houseID", houseID);
        List<Employee> employeeList = query.getResultList();
        return employeeList.size() > 0 ? employeeList : null;
    }

}

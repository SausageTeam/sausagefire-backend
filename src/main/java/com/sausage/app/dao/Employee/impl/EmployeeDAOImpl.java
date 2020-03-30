package com.sausage.app.dao.Employee.impl;

import com.sausage.app.dao.AbstractHibernateDAO;
import com.sausage.app.dao.Employee.EmployeeDAO;
import com.sausage.app.entity.Employee;
import com.sausage.app.entity.House;
import com.sausage.app.entity.Person;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import javax.persistence.Query;
import java.util.List;


@Repository
public class EmployeeDAOImpl extends AbstractHibernateDAO<Employee> implements EmployeeDAO {

    private static final String GET_ALL_EMPLOYEE = "FROM Employee";

    private static final String GET_EMPLOYEE = "FROM Employee WHERE person = :person";

    private static final String GET_EMPLOYEE_By_HOUSE = "FROM Employee WHERE house = :house";

    public EmployeeDAOImpl() { setClazz(Employee.class);}

    @Override
    public List<Employee> getAllEmployee() {
        Session session = getCurrentSession();
        Query query = session.createQuery(GET_ALL_EMPLOYEE);
        @SuppressWarnings("unchecked")
        List<Employee> employeeList = (List<Employee>) query.getResultList();
        return employeeList;
    }

    @Override
    public Employee getEmployeeById(int id) {
        return findById(id);
    }

    @Override
    public Employee getEmployeeByPerson(Person person) {
        Session session = getCurrentSession();
        Query query = session.createQuery(GET_EMPLOYEE);
        query.setParameter("person", person);
        @SuppressWarnings("unchecked")
        List<Employee> employees = (List<Employee>) query.getResultList();
        return (employees.size() > 0) ? employees.get(0) : null;
    }

    @Override
    public void setEmployee(Employee employee) {
        Session session = getCurrentSession();
        session.merge(employee);
    }

    @Override
    public List<Employee> getEmployeesByHouse(House house) {
        Session session = getCurrentSession();
        Query query = session.createQuery(GET_EMPLOYEE_By_HOUSE);
        query.setParameter("house", house);

        @SuppressWarnings("unchecked")
        List<Employee> employeeList = query.getResultList();
        return employeeList;
    }

}

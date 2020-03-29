package com.sausage.app.dao.Employee;

import com.sausage.app.entity.Employee;
import com.sausage.app.entity.Person;

import java.util.List;

public interface EmployeeDAO {

    List<Employee> getAllEmployee();

    Employee getEmployeeById(int id);

    Employee getEmployeeByPerson(Person person);

    void setEmployee(Employee employee);

    List<Employee> getEmployeesFromTheHouse(int houseID);
}

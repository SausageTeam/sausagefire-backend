package com.sausage.app.dao.employee;

import com.sausage.app.entity.Employee;

import java.util.List;

public interface EmployeeDAO {
    Employee getEmployee(int personID);
    List<Employee> getEmployeesFromTheHouse(int houseID);
}

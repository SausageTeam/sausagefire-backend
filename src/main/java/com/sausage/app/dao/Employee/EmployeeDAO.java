package com.sausage.app.dao.Employee;

import com.sausage.app.entity.Employee;
import com.sausage.app.entity.Person;

public interface EmployeeDAO {

    Employee getEmployeeByPerson(Person person);
}

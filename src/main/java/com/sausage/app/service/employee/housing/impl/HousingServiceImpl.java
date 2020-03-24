package com.sausage.app.service.employee.housing.impl;

import com.sausage.app.dao.Person.PersonDAO;
import com.sausage.app.dao.employee.EmployeeDAO;
import com.sausage.app.dao.house.HouseDAO;
import com.sausage.app.dao.user.UserDAO;
import com.sausage.app.domain.housing.housingInfo.HousingInfo;
import com.sausage.app.domain.housing.housingInfo.Resident;
import com.sausage.app.entity.Employee;
import com.sausage.app.entity.House;
import com.sausage.app.entity.Person;
import com.sausage.app.entity.User;
import com.sausage.app.service.employee.housing.HousingService;
import com.sausage.app.service.employee.housing.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
public class HousingServiceImpl implements HousingService {
    private UserDAO userDAO;
    private EmployeeDAO employeeDAO;
    private HouseDAO houseDAO;
    private PersonDAO personDAO;
    private UserService userService;

    @Autowired
    public void setUserDAO(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    @Autowired
    public void setEmployeeDAO(EmployeeDAO employeeDAO) {
        this.employeeDAO = employeeDAO;
    }

    @Autowired
    public void setHouseDAO(HouseDAO houseDAO) {
        this.houseDAO = houseDAO;
    }

    @Autowired
    public void setPersonDAO(PersonDAO personDAO) {
        this.personDAO = personDAO;
    }

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @Override
    @Transactional
    public HousingInfo getHousingInfo(int userID){
        User user = userDAO.getUser(userID);
        int personID = userService.getPersonID(user);
        Employee employee = employeeDAO.getEmployee(personID);
        int houseID = employee.getHouseID();
        House house = houseDAO.getHouse(houseID);
        String address = house.getAddress();

        Resident resident;
        Person person;
        List<Resident> residentList = new ArrayList<>();
        List<Employee> employeeList = (List<Employee>) employeeDAO.getEmployeesFromTheHouse(houseID);
        for(Employee e: employeeList) {
            personID = e.getPersonID();
            person = personDAO.getPerson(personID);
            resident = new Resident(person.getFirstName(), person.getMiddleName(), person.getLastName(), person.getCellphone());
            residentList.add(resident);
        }

        HousingInfo housingInfo = new HousingInfo();
        housingInfo.setAddress(address);
        housingInfo.setResidentList(residentList);
        return housingInfo;
    }
}
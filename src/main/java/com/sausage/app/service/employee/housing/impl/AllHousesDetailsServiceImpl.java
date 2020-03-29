package com.sausage.app.service.employee.housing.impl;

import com.sausage.app.dao.Contact.ContactDAO;
import com.sausage.app.dao.Employee.EmployeeDAO;
import com.sausage.app.dao.House.HouseDAO;
import com.sausage.app.dao.Person.PersonDAO;
import com.sausage.app.domain.housing.housesDetails.AllHousesDetails;
import com.sausage.app.domain.housing.housesDetails.EmployeeInfo;
import com.sausage.app.entity.Contact;
import com.sausage.app.entity.Employee;
import com.sausage.app.entity.House;
import com.sausage.app.entity.Person;
import com.sausage.app.service.employee.housing.AllHousesDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
public class AllHousesDetailsServiceImpl implements AllHousesDetailsService {
    private HouseDAO houseDAO;
    private ContactDAO contactDAO;
    private PersonDAO personDAO;
    private EmployeeDAO employeeDAO;

    @Autowired
    public void setHouseDAO(HouseDAO houseDAO) {
        this.houseDAO = houseDAO;
    }

    @Autowired
    public void setContactDAO(ContactDAO contactDAO) {
        this.contactDAO = contactDAO;
    }

    @Autowired
    public void setPersonDAO(PersonDAO personDAO) {
        this.personDAO = personDAO;
    }

    @Autowired
    public void setEmployeeDAO(EmployeeDAO employeeDAO) {
        this.employeeDAO = employeeDAO;
    }

    @Override
    @Transactional
    public List<AllHousesDetails> getAllHousesDetailList() {
        List<House> houseList = houseDAO.getAllHouses();
        List<AllHousesDetails> allHousesDetailsList = new ArrayList<>();
        int id = 1;
        for(House house: houseList) {
            AllHousesDetails allHousesDetails = new AllHousesDetails();
            allHousesDetails.setId(id);
            allHousesDetails.setAddress(house.getAddress());
            allHousesDetails.setNumOfResidents(house.getNumberOfPerson());
            Person landlord = getLandlord(house);
            allHousesDetails.setEmail(landlord.getEmail());
            allHousesDetails.setLandlord(landlord.getFirstName() + landlord.getMiddleName() + landlord.getLastName());
            allHousesDetails.setPhoneNumber(landlord.getCellphone());
            allHousesDetailsList.add(allHousesDetails);
            id++;
        }
        return allHousesDetailsList;
    }

    private Person getLandlord(House house) {
        int contactID = house.getContactID();
        Contact contact = contactDAO.getContactById(contactID);
        return contact.getPerson();
    }

    @Override
    @Transactional
    public List<EmployeeInfo> getEmployeeInfoList() {
        List<Employee> employeeList = employeeDAO.getAllEmployee();
        List<EmployeeInfo> employeeInfoList = new ArrayList<>();
        for(Employee e: employeeList) {
            EmployeeInfo employeeInfo = new EmployeeInfo();
            System.out.println("First Name: " + e.getPerson().getFirstName());
            employeeInfo.setName(e.getPerson().getFirstName());
            employeeInfo.setCar(e.getCar());
            employeeInfo.setEmail(e.getPerson().getEmail());
            employeeInfo.setPhoneNumber(e.getPerson().getCellphone());
            employeeInfoList.add(employeeInfo);
        }
        return employeeInfoList;
    }
}

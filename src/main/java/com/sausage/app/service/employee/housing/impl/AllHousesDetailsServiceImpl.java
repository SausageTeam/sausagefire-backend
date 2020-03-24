package com.sausage.app.service.employee.housing.impl;

import com.sausage.app.dao.Person.PersonDAO;
import com.sausage.app.dao.contact.ContactDAO;
import com.sausage.app.dao.employee.EmployeeDAO;
import com.sausage.app.dao.house.HouseDAO;
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
        Contact contact = contactDAO.getContact(contactID);
        int personID = contact.getPersonID();
        return personDAO.getPerson(personID);
    }

    @Override
    @Transactional
    public List<EmployeeInfo> getEmployeeInfoList() {
        List<Employee> employeeList = employeeDAO.getAllEmployees();
        List<EmployeeInfo> employeeInfoList = new ArrayList<>();
        for(Employee e: employeeList) {
            EmployeeInfo employeeInfo = new EmployeeInfo();
            employeeInfo.setName(personDAO.getPerson(e.getPersonID()).getFirstName());
            employeeInfo.setCar(e.getCar());
            employeeInfo.setEmail(personDAO.getPerson(e.getPersonID()).getEmail());
            employeeInfo.setPhoneNumber(personDAO.getPerson(e.getPersonID()).getCellphone());
            employeeInfoList.add(employeeInfo);
        }
        return employeeInfoList;
    }
}
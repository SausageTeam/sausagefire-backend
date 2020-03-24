package com.sausage.app.service.employee.housing.impl;

import com.sausage.app.dao.Person.PersonDAO;
import com.sausage.app.dao.contact.ContactDAO;
import com.sausage.app.dao.house.HouseDAO;
import com.sausage.app.domain.housing.housesDetails.AllHousesDetails;
import com.sausage.app.entity.Contact;
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

    @Override
    @Transactional
    public List<AllHousesDetails> getAllHousesDetailList() {
        List<House> houseList = houseDAO.getAllHouses();
        List<AllHousesDetails> allHousesDetailsList = new ArrayList<>();
        for(House house: houseList) {
            AllHousesDetails allHousesDetails = new AllHousesDetails();
            allHousesDetails.setAddress(house.getAddress());
            allHousesDetails.setNumOfResidents(house.getNumberOfPerson());
            Person landlord = getLandlord(house);
            allHousesDetails.setEmail(landlord.getEmail());
            allHousesDetails.setLandlord(landlord.getFirstName() + landlord.getMiddleName() + landlord.getLastName());
            allHousesDetails.setPhoneNumber(landlord.getCellphone());
            allHousesDetailsList.add(allHousesDetails);
        }
        return allHousesDetailsList;
    }

    private Person getLandlord(House house) {
        int contactID = house.getContactID();
        Contact contact = contactDAO.getContact(contactID);
        int personID = contact.getPersonID();
        return personDAO.getPerson(personID);
    }
}

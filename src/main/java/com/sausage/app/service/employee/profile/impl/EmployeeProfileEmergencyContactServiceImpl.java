package com.sausage.app.service.employee.profile.impl;

import com.sausage.app.dao.Address.AddressDAO;
import com.sausage.app.dao.Contact.ContactDAO;
import com.sausage.app.dao.Employee.EmployeeDAO;
import com.sausage.app.dao.Person.PersonDAO;
import com.sausage.app.dao.User.UserDAO;
import com.sausage.app.domain.common.AddressDomain;
import com.sausage.app.domain.employee.profile.profileEmergencyContact.ProfileEmergencyContact;
import com.sausage.app.entity.*;
import com.sausage.app.service.employee.profile.EmployeeProfileEmergencyContactService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class EmployeeProfileEmergencyContactServiceImpl implements EmployeeProfileEmergencyContactService {

    private UserDAO userDAO;

    private PersonDAO personDAO;

    private EmployeeDAO employeeDAO;

    private ContactDAO contactDAO;

    private AddressDAO addressDAO;

    @Autowired
    public void setUserDAO(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    @Autowired
    public void setPersonDAO(PersonDAO personDAO) {
        this.personDAO = personDAO;
    }

    @Autowired
    public void setEmployeeDAO(EmployeeDAO employeeDAO) {
        this.employeeDAO = employeeDAO;
    }

    @Autowired
    public void setContactDAO(ContactDAO contactDAO) {
        this.contactDAO = contactDAO;
    }

    @Autowired
    public void setAddressDAO(AddressDAO addressDAO) {
        this.addressDAO = addressDAO;
    }

    private Person getContactPersonByUserId(int userId) {
        User user = userDAO.getUserById(userId);
        Person person = personDAO.getPersonById(user.getPersonId());
        Employee employee = employeeDAO.getEmployeeByPerson(person);
        Contact contact = contactDAO.getContactById(employee.getEmergencyId());
        return contact.getPerson();
    }

    @Override
    @Transactional
    public ProfileEmergencyContact getProfileEmergencyContact(int userId) {
        Person person = getContactPersonByUserId(userId);
        String firstName = person.getFirstName();
        String middleName = person.getMiddleName();
        String lastName = person.getLastName();
        String cellPhone = person.getCellphone();

        Address address = addressDAO.getAddressByPerson(person);
        AddressDomain addressDomain = AddressDomain.builder()
                .addressLineOne(address.getAddressLineOne())
                .addressLineTwo(address.getAddressLineTwo())
                .city(address.getCity())
                .zipCode(address.getZipCode())
                .stateName(address.getStateName())
                .stateAbbr(address.getStateAbbr())
                .build();

        return ProfileEmergencyContact.builder()
                .firstName(firstName)
                .middleName(middleName)
                .lastName(lastName)
                .cellPhone(cellPhone)
                .addressDomain(addressDomain)
                .build();
    }

    @Override
    @Transactional
    public void setProfileEmergencyContact(int userId, ProfileEmergencyContact profileEmergencyContact) {
        Person person = getContactPersonByUserId(userId);
        String firstName = profileEmergencyContact.getFirstName();
        String middleName = profileEmergencyContact.getMiddleName();
        String lastName = profileEmergencyContact.getLastName();
        String cellPhone = profileEmergencyContact.getCellPhone();
        AddressDomain addressDomain = profileEmergencyContact.getAddressDomain();

        person.setFirstName(firstName);
        person.setMiddleName(middleName);
        person.setLastName(lastName);
        person.setCellphone(cellPhone);
        person = personDAO.setPerson(person);

        Address address = addressDAO.getAddressByPerson(person);
        address.setAddressLineOne(addressDomain.getAddressLineOne());
        address.setAddressLineTwo(addressDomain.getAddressLineTwo());
        address.setCity(addressDomain.getCity());
        address.setZipCode(addressDomain.getZipCode());
        address.setStateName(addressDomain.getStateName());
        address.setStateAbbr(addressDomain.getStateAbbr());
        address.setPerson(person);
        addressDAO.setAddress(address);
    }
}
package com.sausage.app.service.employee.onboarding.impl;

import com.sausage.app.dao.address.AddressDAO;
import com.sausage.app.dao.contact.ContactDAO;
import com.sausage.app.dao.Person.PersonDAO;
import com.sausage.app.dao.user.UserDAO;
import com.sausage.app.domain.common.AddressDomain;
import com.sausage.app.domain.employee.onboarding.onboardingEmergency.OnboardingEmergency;
import com.sausage.app.entity.*;
import com.sausage.app.service.employee.onboarding.EmployeeOnboardingEmergencyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class EmployeeOnboardingEmergencyServiceImpl implements EmployeeOnboardingEmergencyService {

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

    private Employee getEmployeeByUserId(int userId) {
        User user = userDAO.getUserById(userId);
        Person person = personDAO.getPersonById(user.getPersonId());
        return employeeDAO.getEmployeeByPerson(person);
    }

    @Override
    @Transactional
    public OnboardingEmergency getOnboardingEmergency(int userId) {
        Employee employee = getEmployeeByUserId(userId);
        if (employee.getEmergencyId() == 0){
            return OnboardingEmergency.builder()
                    .addressDomain(AddressDomain.builder().build()).build();
        }
        Contact contact = contactDAO.getContactById(employee.getEmergencyId());
        Person contactPerson = contact.getPerson();
        Address address = addressDAO.getAddressByPerson(contactPerson);

        AddressDomain addressDomain = AddressDomain.builder()
                .addressLineOne(address.getAddressLineOne())
                .addressLineTwo(address.getAddressLineTwo())
                .city(address.getCity())
                .zipCode(address.getZipCode())
                .stateName(address.getStateName())
                .stateAbbr(address.getStateAbbr())
                .build();

        return OnboardingEmergency.builder()
                .firstName(contactPerson.getFirstName())
                .lastName(contactPerson.getLastName())
                .middleName(contactPerson.getMiddleName())
                .email(contactPerson.getEmail())
                .cellPhone(contactPerson.getCellphone())
                .addressDomain(addressDomain)
                .relationship(contact.getRelationship())
                .title(contact.getTitle())
                .build();
    }

    @Override
    @Transactional
    public void setOnboardingEmergency(int userId, OnboardingEmergency onboardingEmergency) {
        Employee employee = getEmployeeByUserId(userId);
        AddressDomain addressDomain = onboardingEmergency.getAddressDomain();
        Contact contact;
        Person person;
        Address address;
        if (employee.getEmergencyId() == 0) {
            person = Person.builder()
                    .firstName(onboardingEmergency.getFirstName())
                    .lastName(onboardingEmergency.getLastName())
                    .middleName(onboardingEmergency.getMiddleName())
                    .email(onboardingEmergency.getEmail())
                    .cellphone(onboardingEmergency.getCellPhone())
                    .build();
            person = personDAO.setPerson(person);


            address = Address.builder()
                    .addressLineOne(addressDomain.getAddressLineOne())
                    .addressLineTwo(addressDomain.getAddressLineTwo())
                    .city(addressDomain.getCity())
                    .zipCode(addressDomain.getZipCode())
                    .stateName(addressDomain.getStateName())
                    .stateAbbr(addressDomain.getStateAbbr())
                    .person(person)
                    .build();
            addressDAO.setAddress(address);


            contact = Contact.builder()
                    .person(person)
                    .relationship(onboardingEmergency.getRelationship())
                    .title(onboardingEmergency.getTitle())
                    .isEmergency(1)
                    .isEmergency(0)
                    .isLandlord(0).build();
            contact = contactDAO.setContact(contact);
        } else{
            contact = contactDAO.getContactById(employee.getEmergencyId());
            person = contact.getPerson();
            address = addressDAO.getAddressByPerson(person);

            person.setFirstName(onboardingEmergency.getFirstName());
            person.setMiddleName(onboardingEmergency.getMiddleName());
            person.setLastName(onboardingEmergency.getLastName());
            person.setEmail(onboardingEmergency.getEmail());
            person.setCellphone(onboardingEmergency.getCellPhone());
            person = personDAO.setPerson(person);

            address.setAddressLineOne(addressDomain.getAddressLineOne());
            address.setAddressLineTwo(addressDomain.getAddressLineTwo());
            address.setCity(addressDomain.getCity());
            address.setZipCode(addressDomain.getZipCode());
            address.setStateName(addressDomain.getStateName());
            address.setStateAbbr(addressDomain.getStateAbbr());
            address.setPerson(person);
            addressDAO.setAddress(address);

            contact.setPerson(person);
            contactDAO.setContact(contact);
        }

        employee.setEmergencyId(contact.getId());
        employeeDAO.setEmployee(employee);
    }
}
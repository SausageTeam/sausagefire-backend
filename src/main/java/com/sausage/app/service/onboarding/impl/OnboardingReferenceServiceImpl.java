package com.sausage.app.service.onboarding.impl;

import com.sausage.app.dao.Address.AddressDAO;
import com.sausage.app.dao.Employee.EmployeeDAO;
import com.sausage.app.dao.Person.PersonDAO;
import com.sausage.app.dao.Contact.ContactDAO;
import com.sausage.app.dao.user.UserDAO;
import com.sausage.app.domain.onboarding.onboardingReference.OnboardingReference;
import com.sausage.app.domain.common.AddressDomain;
import com.sausage.app.entity.*;
import com.sausage.app.service.onboarding.OnboardingReferenceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class OnboardingReferenceServiceImpl implements OnboardingReferenceService {

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
    public OnboardingReference getOnboardingReference(int userId) {
        Employee employee = getEmployeeByUserId(userId);
        Contact contact = contactDAO.getContactById(employee.getReferenceId());
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

        return OnboardingReference.builder()
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
    public void setOnboardingReference(int userId, OnboardingReference onboardingReference) {
        Employee employee = getEmployeeByUserId(userId);
        Person person = Person.builder()
                .firstName(onboardingReference.getFirstName())
                .lastName(onboardingReference.getLastName())
                .middleName(onboardingReference.getMiddleName())
                .email(onboardingReference.getEmail())
                .cellphone(onboardingReference.getCellPhone())
                .build();
        personDAO.updatePerson(person);

        AddressDomain addressDomain = onboardingReference.getAddressDomain();
        Address address = Address.builder()
                .addressLineOne(addressDomain.getAddressLineOne())
                .addressLineTwo(addressDomain.getAddressLineTwo())
                .city(addressDomain.getCity())
                .zipCode(addressDomain.getZipCode())
                .stateName(addressDomain.getStateName())
                .stateAbbr(addressDomain.getStateAbbr())
                .person(person)
                .build();
        addressDAO.setAddress(address);

        Contact contact = Contact.builder()
                .person(person)
                .relationship(onboardingReference.getRelationship())
                .title(onboardingReference.getTitle())
                .isReference(1)
                .isEmergency(0)
                .isLandlord(0).build();
        contact = contactDAO.setContact(contact);

        employee.setEmergencyId(contact.getId());
        employeeDAO.setEmployee(employee);
    }
}

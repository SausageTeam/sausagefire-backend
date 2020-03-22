package com.sausage.app.service.onboarding.impl;

import com.sausage.app.dao.Address.AddressDAO;
import com.sausage.app.dao.Contact.ContactDAO;
import com.sausage.app.dao.Employee.EmployeeDAO;
import com.sausage.app.dao.Person.PersonDAO;
import com.sausage.app.dao.user.UserDAO;
import com.sausage.app.domain.onboarding.onboardingEmergency.OnboardingEmergency;
import com.sausage.app.domain.onboarding.onboardingReference.OnboardingReference;
import com.sausage.app.domain.onboarding.onboardingReference.OnboardingReferenceAddress;
import com.sausage.app.entity.*;
import com.sausage.app.service.onboarding.OnboardingEmergencyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class OnboardingEmergencyServiceImpl implements OnboardingEmergencyService {
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
    public List<OnboardingEmergency> getOnboardingEmergency(int userId) {
        List<OnboardingEmergency> onboardingEmergencyList = new ArrayList<>();
        Employee employee = getEmployeeByUserId(userId);
        Contact contact = contactDAO.getContactById(employee.getReferenceId());
        Person contactPerson = contact.getPerson();
        Address address = addressDAO.getAddressByPerson(contactPerson);

        OnboardingReferenceAddress onboardingReferenceAddress = OnboardingReferenceAddress.builder()
                .addressLineOne(address.getAddressLineOne())
                .addressLineTwo(address.getAddressLineTwo())
                .city(address.getCity())
                .zipCode(address.getZipCode())
                .stateName(address.getStateName())
                .stateAbbr(address.getStateAbbr())
                .build();

        onboardingEmergencyList.add(OnboardingEmergency.builder()
                .firstName(contactPerson.getFirstName())
                .lastName(contactPerson.getLastName())
                .middleName(contactPerson.getMiddleName())
                .email(contactPerson.getEmail())
                .cellPhone(contactPerson.getCellphone())
                .onboardingReferenceAddress(onboardingReferenceAddress)
                .relationship(contact.getRelationship())
                .title(contact.getTitle())
                .build());

        return onboardingEmergencyList;
    }

    @Override
    @Transactional
    public void setOnboardingEmergency(int userId, OnboardingEmergency onboardingEmergency) {
        Employee employee = getEmployeeByUserId(userId);
        Person person = Person.builder()
                .firstName(onboardingEmergency.getFirstName())
                .lastName(onboardingEmergency.getLastName())
                .middleName(onboardingEmergency.getMiddleName())
                .email(onboardingEmergency.getEmail())
                .cellphone(onboardingEmergency.getCellPhone())
                .build();
        person = personDAO.updatePersonNoId(person);

        OnboardingReferenceAddress onboardingReferenceAddress = onboardingEmergency.getOnboardingReferenceAddress();
        Address address = Address.builder()
                .addressLineOne(onboardingReferenceAddress.getAddressLineOne())
                .addressLineTwo(onboardingReferenceAddress.getAddressLineTwo())
                .city(onboardingReferenceAddress.getCity())
                .zipCode(onboardingReferenceAddress.getZipCode())
                .stateName(onboardingReferenceAddress.getStateName())
                .stateAbbr(onboardingReferenceAddress.getStateAbbr())
                .person(person)
                .build();
        addressDAO.setAddressNoId(address);

        Contact contact = Contact.builder()
                .person(person)
                .relationship(onboardingEmergency.getRelationship())
                .title(onboardingEmergency.getTitle())
                .isReference(1)
                .isEmergency(0)
                .isLandlord(0).build();
        contact = contactDAO.setContact(contact);

        employee.setEmergencyId(contact.getId());
        employeeDAO.setEmployee(employee);
    }
}

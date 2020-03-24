package com.sausage.app.service.employee.profile.impl;

import com.sausage.app.dao.Address.AddressDAO;
import com.sausage.app.dao.Person.PersonDAO;
import com.sausage.app.dao.User.UserDAO;
import com.sausage.app.domain.employee.profile.profileAddress.ProfileAddress;
import com.sausage.app.entity.Address;
import com.sausage.app.entity.Person;
import com.sausage.app.entity.User;
import com.sausage.app.service.employee.profile.EmployeeProfileAddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class EmployeeProfileAddressServiceImpl implements EmployeeProfileAddressService {

    private UserDAO userDAO;

    private PersonDAO personDAO;

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
    public void setAddressDAO(AddressDAO addressDAO) {
        this.addressDAO = addressDAO;
    }

    private Address getAddressByUserId(int userId) {
        User user = userDAO.getUserById(userId);
        Person person = personDAO.getPersonById(user.getPersonId());
        return addressDAO.getAddressByPerson(person);
    }

    @Override
    @Transactional
    public ProfileAddress getProfileAddress(int userId) {
        Address address = getAddressByUserId(userId);
        return ProfileAddress.builder()
                .addressLineOne(address.getAddressLineOne())
                .addressLineTwo(address.getAddressLineTwo())
                .city(address.getCity())
                .zipCode(address.getZipCode())
                .stateName(address.getStateName())
                .stateAbbr(address.getStateAbbr())
                .build();
    }

    @Override
    @Transactional
    public void setProfileAddress(int userId, ProfileAddress profileAddress) {
        Address address = getAddressByUserId(userId);
        address.setAddressLineOne(profileAddress.getAddressLineOne());
        address.setAddressLineTwo(profileAddress.getAddressLineTwo());
        address.setCity(profileAddress.getCity());
        address.setZipCode(profileAddress.getZipCode());
        address.setStateName(profileAddress.getStateName());
        address.setStateAbbr(profileAddress.getStateAbbr());
        addressDAO.setAddress(address);
    }

}

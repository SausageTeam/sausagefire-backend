package com.sausage.app.service.employee.housing.impl;

import com.sausage.app.dao.Address.AddressDAO;
import com.sausage.app.dao.Contact.ContactDAO;
import com.sausage.app.dao.Employee.EmployeeDAO;
import com.sausage.app.dao.House.HouseDAO;
import com.sausage.app.dao.User.UserDAO;
import com.sausage.app.domain.common.AddressDomain;
import com.sausage.app.domain.employee.housing.houseDetail.HouseDetail;
import com.sausage.app.domain.employee.housing.houseDetail.Resident;
import com.sausage.app.entity.*;
import com.sausage.app.service.employee.housing.EmployeeHouseDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class EmployeeHouseDetailServiceImpl implements EmployeeHouseDetailService {

    private UserDAO userDAO;

    private EmployeeDAO employeeDAO;

    private ContactDAO contactDAO;

    private HouseDAO houseDAO;

    @Autowired
    public void setUserDAO(UserDAO userDAO) {
        this.userDAO = userDAO;
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
    public void setHouseDAO(HouseDAO houseDAO) {
        this.houseDAO = houseDAO;
    }

    @Override
    @Transactional
    public HouseDetail getHouseDetail(int userId) {
        try {
            User user = userDAO.getUserById(userId);
            Person person = user.getPerson();
            Employee employee = employeeDAO.getEmployeeByPerson(person);
            Contact contact = contactDAO.getContactByPerson(person);
            House house = houseDAO.getHouseByContact(contact);
            Address address = house.getAddress();
            AddressDomain addressDomain = AddressDomain.builder()
                    .addressLineOne(address.getAddressLineOne())
                    .addressLineTwo(address.getAddressLineTwo())
                    .city(address.getCity())
                    .zipCode(address.getZipCode())
                    .stateName(address.getStateName())
                    .stateAbbr(address.getStateAbbr())
                    .build();

            List<Resident> residentList = new ArrayList<>();
            List<Employee> employeeList = employeeDAO.getEmployeesByHouse(house);
            for (Employee e : employeeList) {
                Person p = e.getPerson();
                String name = p.getPreferredName();
                if (name == null || name.equals("")) {
                    name = p.getFirstName();
                }
                String phone = p.getCellphone();
                Resident resident = Resident.builder()
                        .name(name)
                        .phone(phone)
                        .build();
                residentList.add(resident);
            }
            return HouseDetail.builder()
                    .addressDomain(addressDomain)
                    .residentList(residentList)
                    .build();
        } catch (Exception e) {
            return null;
        }
    }

}

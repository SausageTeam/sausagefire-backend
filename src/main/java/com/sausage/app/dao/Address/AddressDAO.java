package com.sausage.app.dao.Address;

import com.sausage.app.entity.Address;
import com.sausage.app.entity.Person;

public interface AddressDAO {

    Address getAddressByPerson(Person person);

    void setAddress(Address address);

//    void setAddressNoId(Address address);

}

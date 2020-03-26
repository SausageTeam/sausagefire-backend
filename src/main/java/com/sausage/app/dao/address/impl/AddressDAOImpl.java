package com.sausage.app.dao.address.impl;

import com.sausage.app.dao.AbstractHibernateDAO;
import com.sausage.app.dao.address.AddressDAO;
import com.sausage.app.entity.Address;
import com.sausage.app.entity.Person;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import javax.persistence.Query;
import java.util.List;

@Repository
public class AddressDAOImpl extends AbstractHibernateDAO<Address> implements AddressDAO {

    private static final String GET_ADDRESS = "FROM Address WHERE person = :person";

//    private static final String GET_ADDRESS_MORE = "FROM Address WHERE addressLineOne = :addressLineOne AND zipCode = :zipCode AND person = :person";

    public AddressDAOImpl() {
        setClazz(Address.class);
    }

    @Override
    public Address getAddressByPerson(Person person) {
        Session session = getCurrentSession();
        Query query = session.createQuery(GET_ADDRESS);
        query.setParameter("person", person);

        @SuppressWarnings("unchecked")
        List<Address> addresses = (List<Address>) query.getResultList();
        return (addresses.size() > 0) ? addresses.get(0) : new Address();
    }

    @Override
    public void setAddress(Address address) {
        Session session = getCurrentSession();
        session.merge(address);
    }

//    @Override
//    public void setAddressNoId(Address address) {
//        Session session = getCurrentSession();
//        Query query = session.createQuery(GET_ADDRESS_MORE);
//        query.setParameter("addressLineOne", address.getAddressLineOne());
//        query.setParameter("zipCode", address.getZipCode());
//        query.setParameter("person", address.getPerson());
//
//        @SuppressWarnings("unchecked")
//        List<Address> addresses = (List<Address>) query.getResultList();
//        if (addresses.size() == 0) {
//            session.merge(address);
//        }
//    }

}

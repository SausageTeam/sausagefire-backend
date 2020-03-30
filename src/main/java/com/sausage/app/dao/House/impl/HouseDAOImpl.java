package com.sausage.app.dao.House.impl;

import com.sausage.app.dao.AbstractHibernateDAO;
import com.sausage.app.dao.House.HouseDAO;
import com.sausage.app.entity.Contact;
import com.sausage.app.entity.House;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import javax.persistence.Query;
import java.util.List;

@Repository
public class HouseDAOImpl extends AbstractHibernateDAO<House> implements HouseDAO {

    //    private final static String GET_ALL_HOUSES = "FROM House";
    private static final String GET_HOUSE_BY_CONTACT = "FROM House WHERE contact = :contact";

    public HouseDAOImpl() {
        setClazz(House.class);
    }

    @Override
    public House getHouseByContact(Contact contact) {
        Session session = getCurrentSession();
        Query query = session.createQuery(GET_HOUSE_BY_CONTACT);
        query.setParameter("contact", contact);

        @SuppressWarnings("unchecked")
        List<House> houseList = query.getResultList();
        return (houseList.size() > 0) ? houseList.get(0) : null;
    }

//    @Override
//    public House getHouse(int id) {
//        return findById(id);
//    }
//
//    @Override
//    public List<House> getAllHouses() {
//        Session session = getCurrentSession();
//        Query query = session.createQuery(GET_ALL_HOUSES);
//        List<House> houseList = query.getResultList();
//        return houseList;
//    }
}

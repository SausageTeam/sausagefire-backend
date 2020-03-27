package com.sausage.app.dao.House.impl;

import com.sausage.app.dao.AbstractHibernateDAO;
import com.sausage.app.dao.House.HouseDAO;
import com.sausage.app.entity.House;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class HouseDAOImpl extends AbstractHibernateDAO<House> implements HouseDAO {

    private final static String GET_ALL_HOUSES = "FROM House";

    public HouseDAOImpl() { setClazz(House.class);}

    @Override
    public House getHouse(int id) {
        return findById(id);
    }

    @Override
    public List<House> getAllHouses() {
        Session session = getCurrentSession();
        Query query = session.createQuery(GET_ALL_HOUSES);
        List<House> houseList = query.getResultList();
        return houseList;
    }
}

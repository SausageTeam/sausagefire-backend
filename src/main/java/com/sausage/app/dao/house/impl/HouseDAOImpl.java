package com.sausage.app.dao.house.impl;

import com.sausage.app.dao.AbstractHibernateDAO;
import com.sausage.app.dao.house.HouseDAO;
import com.sausage.app.entity.House;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import javax.persistence.Query;
import java.util.List;

@Repository
public class HouseDAOImpl extends AbstractHibernateDAO<House> implements HouseDAO {
    private static final String GET_ALL_HOUSES = "FROM House";

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

package com.sausage.app.dao.house.impl;

import com.sausage.app.dao.AbstractHibernateDAO;
import com.sausage.app.dao.house.HouseDAO;
import com.sausage.app.entity.House;
import org.springframework.stereotype.Repository;

@Repository
public class HouseDAOImpl extends AbstractHibernateDAO<House> implements HouseDAO {
    public HouseDAOImpl() { setClazz(House.class);}
}

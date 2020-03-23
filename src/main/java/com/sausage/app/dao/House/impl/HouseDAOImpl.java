package com.sausage.app.dao.House.impl;

import com.sausage.app.dao.AbstractHibernateDAO;
import com.sausage.app.dao.House.HouseDAO;
import com.sausage.app.entity.House;
import org.springframework.stereotype.Repository;

@Repository
public class HouseDAOImpl extends AbstractHibernateDAO<House> implements HouseDAO {
    public HouseDAOImpl() { setClazz(House.class);}
}

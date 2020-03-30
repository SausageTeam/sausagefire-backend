package com.sausage.app.dao.Facility.impl;

import com.sausage.app.dao.AbstractHibernateDAO;
import com.sausage.app.dao.Facility.FacilityDAO;
import com.sausage.app.entity.Facility;
import org.springframework.stereotype.Repository;

@Repository
public class FacilityDAOImpl extends AbstractHibernateDAO<Facility> implements FacilityDAO {
    public FacilityDAOImpl() { setClazz(Facility.class);}
}

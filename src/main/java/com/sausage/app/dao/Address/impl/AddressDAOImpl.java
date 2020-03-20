package com.sausage.app.dao.Address.impl;

import com.sausage.app.dao.AbstractHibernateDAO;
import com.sausage.app.dao.Address.AddressDAO;
import com.sausage.app.entity.Address;
import org.springframework.stereotype.Repository;

@Repository
public class AddressDAOImpl extends AbstractHibernateDAO<Address> implements AddressDAO {
    public AddressDAOImpl() { setClazz(Address.class); }
}

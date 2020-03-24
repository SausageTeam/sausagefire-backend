package com.sausage.app.dao.registrationToken.impl;

import com.sausage.app.dao.AbstractHibernateDAO;
import com.sausage.app.dao.registrationToken.RegistrationTokenDAO;
import com.sausage.app.entity.RegistrationToken;
import org.springframework.stereotype.Repository;

@Repository
public class RegistrationTokenImpl extends AbstractHibernateDAO<RegistrationToken> implements RegistrationTokenDAO {
    public RegistrationTokenImpl() { setClazz(RegistrationToken.class); }
}
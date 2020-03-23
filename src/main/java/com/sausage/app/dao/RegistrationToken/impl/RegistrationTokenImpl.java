package com.sausage.app.dao.RegistrationToken.impl;

import com.sausage.app.dao.AbstractHibernateDAO;
import com.sausage.app.dao.RegistrationToken.RegistrationTokenDAO;
import com.sausage.app.entity.RegistrationToken;
import org.springframework.stereotype.Repository;

@Repository
public class RegistrationTokenImpl extends AbstractHibernateDAO<RegistrationToken> implements RegistrationTokenDAO {
    public RegistrationTokenImpl() { setClazz(RegistrationToken.class); }
}

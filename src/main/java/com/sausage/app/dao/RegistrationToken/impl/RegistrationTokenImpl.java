package com.sausage.app.dao.RegistrationToken.impl;

import com.sausage.app.dao.AbstractHibernateDAO;
import com.sausage.app.dao.RegistrationToken.RegistrationTokenDAO;
import com.sausage.app.entity.RegistrationToken;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

@Repository
public class RegistrationTokenImpl extends AbstractHibernateDAO<RegistrationToken> implements RegistrationTokenDAO {

    public RegistrationTokenImpl() { setClazz(RegistrationToken.class); }

    @Override
    public void setRegistrationToken(RegistrationToken registrationToken) {
        Session session = getCurrentSession();
        session.merge(registrationToken);
    }

}

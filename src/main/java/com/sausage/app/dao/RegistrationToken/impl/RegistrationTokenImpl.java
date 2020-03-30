package com.sausage.app.dao.RegistrationToken.impl;

import com.sausage.app.dao.AbstractHibernateDAO;
import com.sausage.app.dao.RegistrationToken.RegistrationTokenDAO;
import com.sausage.app.entity.RegistrationToken;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import javax.persistence.Query;
import java.util.List;


@Repository
public class RegistrationTokenImpl extends AbstractHibernateDAO<RegistrationToken> implements RegistrationTokenDAO {

    private static final String GET_REGISTRATION_TOKEN_BY_EMAIL = "FROM RegistrationToken WHERE email = :email";

    public RegistrationTokenImpl() { setClazz(RegistrationToken.class); }

    @Override
    public RegistrationToken getRegistrationTokenByEmail(String email) {
        Session session = getCurrentSession();
        Query query = session.createQuery(GET_REGISTRATION_TOKEN_BY_EMAIL);
        query.setParameter("email", email);

        @SuppressWarnings("uncheck")
        List<RegistrationToken> registrationTokenList = query.getResultList();
        return (registrationTokenList.size() > 0) ? registrationTokenList.get(0) : null;
    }

    @Override
    public void setRegistrationToken(RegistrationToken registrationToken) {
        Session session = getCurrentSession();
        session.merge(registrationToken);
    }

}

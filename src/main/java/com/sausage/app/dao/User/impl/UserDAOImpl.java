package com.sausage.app.dao.User.impl;

import com.sausage.app.dao.AbstractHibernateDAO;
import com.sausage.app.dao.User.UserDAO;
import com.sausage.app.entity.User;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import javax.persistence.Query;
import java.util.List;

@Repository
public class UserDAOImpl extends AbstractHibernateDAO<User> implements UserDAO {

   private static final String GET_USERS_BY_EMAIL = "FROM User WHERE email = :email";

    public UserDAOImpl() {
        setClazz(User.class);
    }

    @Override
    public User getUserById(int id) {
        return findById(id);
    }

    @Override
    public User getUserByEmail(String email) {
        Session session = getCurrentSession();
        Query query = session.createQuery(GET_USERS_BY_EMAIL);
        query.setParameter("email", email);

        @SuppressWarnings("unchecked")
        List<User> userList = query.getResultList();
        return (userList.size() > 0) ? userList.get(0) : null;
    }

}

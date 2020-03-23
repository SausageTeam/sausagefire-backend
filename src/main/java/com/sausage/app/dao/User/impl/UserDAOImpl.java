package com.sausage.app.dao.User.impl;

import com.sausage.app.dao.AbstractHibernateDAO;
import com.sausage.app.dao.User.UserDAO;
import com.sausage.app.entity.User;
import org.springframework.stereotype.Repository;

@Repository
public class UserDAOImpl extends AbstractHibernateDAO<User> implements UserDAO {

//    private static final String GET_USERS = "FROM User WHERE id = :id";

    public UserDAOImpl() {
        setClazz(User.class);
    }

    @Override
    public User getUserById(int id) {
//        Session session = getCurrentSession();
//        Query query = session.createQuery(GET_USERS);
//        query.setParameter("id", id);
//
//        @SuppressWarnings("unchecked")
//        List<User> users = (List<User>) query.getResultList();
//        return (users.size() > 0) ? users.get(0) : null;
        return findById(id);
    }

}

package com.sausage.app.dao.user.impl;

import com.sausage.app.dao.AbstractHibernateDAO;
import com.sausage.app.dao.user.UserDAO;
import com.sausage.app.entity.User;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import javax.persistence.Query;
import java.util.List;

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

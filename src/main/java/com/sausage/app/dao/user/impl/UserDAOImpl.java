package com.sausage.app.dao.user.impl;

import com.sausage.app.dao.AbstractHibernateDAO;
import com.sausage.app.dao.user.UserDAO;
import com.sausage.app.entity.User;
import org.springframework.stereotype.Repository;

@Repository
public class UserDAOImpl extends AbstractHibernateDAO<User> implements UserDAO {

    public UserDAOImpl() { setClazz(User.class); }

}

package com.sausage.app.dao.userRole.impl;

import com.sausage.app.dao.AbstractHibernateDAO;
import com.sausage.app.dao.userRole.UserRoleDAO;
import com.sausage.app.entity.UserRole;
import org.springframework.stereotype.Repository;

@Repository
public class UserRoleDAOImpl extends AbstractHibernateDAO<UserRole> implements UserRoleDAO {
    public UserRoleDAOImpl() { setClazz(UserRole.class);}
}

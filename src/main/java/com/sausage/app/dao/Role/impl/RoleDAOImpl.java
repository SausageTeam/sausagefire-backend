package com.sausage.app.dao.Role.impl;

import com.sausage.app.dao.AbstractHibernateDAO;
import com.sausage.app.dao.Role.RoleDAO;
import com.sausage.app.entity.Role;
import org.springframework.stereotype.Repository;

@Repository
public class RoleDAOImpl extends AbstractHibernateDAO<Role> implements RoleDAO {
    public RoleDAOImpl() { setClazz(Role.class); }
}

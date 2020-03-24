package com.sausage.app.dao.Permission.impl;

import com.sausage.app.dao.AbstractHibernateDAO;
import com.sausage.app.dao.Permission.PermissionDAO;
import com.sausage.app.entity.Permission;
import org.springframework.stereotype.Repository;


@Repository
public class PermissionDAOImpl extends AbstractHibernateDAO<Permission> implements PermissionDAO {
    public PermissionDAOImpl() { setClazz(Permission.class); }
}

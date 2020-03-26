package com.sausage.app.dao.permission.impl;

import com.sausage.app.dao.AbstractHibernateDAO;
import com.sausage.app.dao.permission.PermissionDAO;
import com.sausage.app.entity.Permission;
import org.springframework.stereotype.Repository;


@Repository
public class PermissionDAOImpl extends AbstractHibernateDAO<Permission> implements PermissionDAO {
    public PermissionDAOImpl() { setClazz(Permission.class); }
}

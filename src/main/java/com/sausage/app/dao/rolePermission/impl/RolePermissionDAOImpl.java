package com.sausage.app.dao.rolePermission.impl;

import com.sausage.app.dao.AbstractHibernateDAO;
import com.sausage.app.dao.rolePermission.RolePermissionDAO;
import com.sausage.app.entity.RolePermission;
import org.springframework.stereotype.Repository;

@Repository
public class RolePermissionDAOImpl extends AbstractHibernateDAO<RolePermission> implements RolePermissionDAO {
    public RolePermissionDAOImpl() { setClazz(RolePermission.class); }
}

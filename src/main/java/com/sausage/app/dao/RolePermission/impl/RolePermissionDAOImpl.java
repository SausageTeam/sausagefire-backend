package com.sausage.app.dao.RolePermission.impl;

import com.sausage.app.dao.AbstractHibernateDAO;
import com.sausage.app.dao.RolePermission.RolePermissionDAO;
import com.sausage.app.entity.RolePermission;
import org.springframework.stereotype.Repository;

@Repository
public class RolePermissionDAOImpl extends AbstractHibernateDAO<RolePermission> implements RolePermissionDAO {
    public RolePermissionDAOImpl() { setClazz(RolePermission.class); }
}

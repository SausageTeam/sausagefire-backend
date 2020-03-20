package com.sausage.app.dao.userRole.impl;

import com.sausage.app.dao.AbstractHibernateDAO;
import com.sausage.app.dao.userRole.UserRoleDAO;
import com.sausage.app.entity.UserRole;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import javax.persistence.Query;
import java.util.List;

@Repository
public class UserRoleDAOImpl extends AbstractHibernateDAO<UserRole> implements UserRoleDAO {

    private static final String GET_USER_ROLE = "FROM UserRole WHERE userID = :userId";

    public UserRoleDAOImpl() {
        setClazz(UserRole.class);
    }

    @Override
    public UserRole getUserRoleByUserId(int userId) {
        Session session = getCurrentSession();
        Query query = session.createQuery(GET_USER_ROLE);
        query.setParameter("userId", userId);

        @SuppressWarnings("unchecked")
        List<UserRole> userRoles = (List<UserRole>) query.getResultList();
        return (userRoles.size() > 0) ? userRoles.get(0) : null;
    }
}

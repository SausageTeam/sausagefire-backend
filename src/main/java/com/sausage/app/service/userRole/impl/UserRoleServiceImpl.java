package com.sausage.app.service.userRole.impl;

import com.sausage.app.dao.userRole.UserRoleDAO;
import com.sausage.app.entity.UserRole;
import com.sausage.app.service.userRole.UserRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserRoleServiceImpl implements UserRoleService {

    private UserRoleDAO userRoleDAO;

    @Autowired
    public void setUserRoleDAO(UserRoleDAO userRoleDAO) {
        this.userRoleDAO = userRoleDAO;
    }

    @Override
    @Transactional
    public int getUserRoleId(int userId) {
        UserRole userRole = userRoleDAO.getUserRoleByUserId(userId);
        return userRole.getRoleID();
    }
}

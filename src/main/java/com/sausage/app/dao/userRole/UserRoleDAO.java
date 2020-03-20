package com.sausage.app.dao.userRole;

import com.sausage.app.entity.UserRole;

public interface UserRoleDAO {

    UserRole getUserRoleByUserId(int userId);

}

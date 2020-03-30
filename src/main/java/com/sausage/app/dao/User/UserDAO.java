package com.sausage.app.dao.User;

import com.sausage.app.entity.User;

public interface UserDAO {

    User getUserById(int id);

    User getUserByEmail(String email);

}

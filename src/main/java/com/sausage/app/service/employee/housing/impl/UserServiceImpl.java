package com.sausage.app.service.employee.housing.impl;

import com.sausage.app.entity.User;
import com.sausage.app.service.employee.housing.UserService;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class UserServiceImpl implements UserService {

    @Override
    @Transactional
    public int getPersonID(User user) {
        return user.getPerson().getId();
    }
}

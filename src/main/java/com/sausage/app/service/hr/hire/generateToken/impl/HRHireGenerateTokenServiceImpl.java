package com.sausage.app.service.hr.hire.generateToken.impl;

import com.sausage.app.constant.Constant;
import com.sausage.app.dao.RegistrationToken.RegistrationTokenDAO;
import com.sausage.app.dao.User.UserDAO;
import com.sausage.app.domain.hr.hire.generateToken.HireGenerateToken;
import com.sausage.app.entity.RegistrationToken;
import com.sausage.app.entity.User;
import com.sausage.app.fileIO.AES;
import com.sausage.app.service.hr.hire.generateToken.HRHireGenerateTokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import static com.sausage.app.constant.Constant.*;

@Service
public class HRHireGenerateTokenServiceImpl implements HRHireGenerateTokenService {

    private UserDAO userDAO;

    private RegistrationTokenDAO registrationTokenDAO;

    @Autowired
    public void setUserDAO(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    @Autowired
    public void setRegistrationTokenDAO(RegistrationTokenDAO registrationTokenDAO) {
        this.registrationTokenDAO = registrationTokenDAO;
    }

    @Override
    public boolean setHireGenerateToken(int userId, HireGenerateToken hireGenerateToken) {
        String email = hireGenerateToken.getEmail();
        User user = userDAO.getUserByEmail(email);
        if (user != null) {
            return false;
        } else {
            String token = AES.encrypt(email, SECRET_KEY);
            RegistrationToken registrationToken = RegistrationToken.builder()
                    .token(token)
                    .email(email)
                    .createdBy(userId)
                    .build();
            registrationTokenDAO.setRegistrationToken(registrationToken);
            String text = String.format(GENERATE_TOKEN_NOTIFICATION, token);
            return true;
        }
    }

}



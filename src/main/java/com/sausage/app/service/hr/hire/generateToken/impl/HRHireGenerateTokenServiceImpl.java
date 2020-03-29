package com.sausage.app.service.hr.hire.generateToken.impl;

import com.sausage.app.dao.RegistrationToken.RegistrationTokenDAO;
import com.sausage.app.dao.User.UserDAO;
import com.sausage.app.domain.hr.hire.generateToken.HireGenerateToken;
import com.sausage.app.entity.RegistrationToken;
import com.sausage.app.entity.User;
import com.sausage.app.security.util.AES;
import com.sausage.app.service.common.mail.EmailService;
import com.sausage.app.service.hr.hire.generateToken.HRHireGenerateTokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static com.sausage.app.constant.Constant.*;

@Service
public class HRHireGenerateTokenServiceImpl implements HRHireGenerateTokenService {

    private UserDAO userDAO;

    private RegistrationTokenDAO registrationTokenDAO;

    private EmailService emailService;

    @Autowired
    public void setUserDAO(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    @Autowired
    public void setRegistrationTokenDAO(RegistrationTokenDAO registrationTokenDAO) {
        this.registrationTokenDAO = registrationTokenDAO;
    }

    @Autowired
    public void setEmailService(EmailService emailService) {
        this.emailService = emailService;
    }

    @Override
    @Transactional
    public boolean setHireGenerateToken(int userId, HireGenerateToken hireGenerateToken) {
        String email = hireGenerateToken.getEmail();
        String title = hireGenerateToken.getTitle();
        String startDate = hireGenerateToken.getStartDate();
        String endDate = hireGenerateToken.getEndDate();
        User user = userDAO.getUserByEmail(email);

        RegistrationToken registrationToken = registrationTokenDAO.getRegistrationTokenByEmail(email);
        if (user != null) {
            return false;
        }

        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String formatDateTime = now.format(format);
        String decryptToken = String.format("%s %s %s %s %s", email, title, startDate, endDate, formatDateTime);
        String encryptToken = AES.encrypt(decryptToken, SECRET_KEY);
        if (registrationToken != null){
            String createDateTime = registrationToken.getCreatedDateTime();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern(DEFAULT_DATE_TIME_FORMAT);
            LocalDateTime expiryDate = LocalDateTime.parse(createDateTime, formatter).plusHours(registrationToken.getValidDuration());
            boolean usable = expiryDate.isAfter(LocalDateTime.now());
            if (!usable){
                registrationToken.setToken(encryptToken);
                registrationToken.setCreatedDateTime(formatDateTime);
                registrationToken.setCreatedUser(userId);
                registrationTokenDAO.setRegistrationToken(registrationToken);
            }
        }
        else {
            registrationToken = RegistrationToken.builder()
                    .token(encryptToken)
                    .validDuration(DEFAULT_REGISTRATION_TOKEN_VALID_DURATION)
                    .email(email)
                    .activeFlag(ACTIVE_FLAG)
                    .createdDateTime(formatDateTime)
                    .createdUser(userId)
                    .build();

            registrationTokenDAO.setRegistrationToken(registrationToken);
        }
        String body = String.format(GENERATE_TOKEN_NOTIFICATION_BODY, title.toUpperCase(), startDate, endDate, registrationToken.getToken());
        emailService.sendMail(email, GENERATE_TOKEN_NOTIFICATION_SUBJECT, body);
        return true;
    }

}




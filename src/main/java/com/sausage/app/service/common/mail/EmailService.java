package com.sausage.app.service.common.mail;

public interface EmailService {

    void sendMail(String to, String subject, String body);

}

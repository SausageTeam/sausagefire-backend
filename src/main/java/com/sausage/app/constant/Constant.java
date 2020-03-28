package com.sausage.app.constant;

public interface Constant {
    String AUTH_SERVICE = "http://localhost:9999/auth/login";

    String JWT_TOKEN_COOKIE_NAME = "JWT-TOKEN";
    String SIGNING_KEY = "signingKey";

    int DEFAULT_REGISTRATION_TOKEN_VALID_DURATION = 3;
    int ACTIVE_FLAG = 1;
    int INACTIVE_FLAG = 0;

    String SECRET_KEY = "sausage";

    String DEFAULT_AVATAR_PATH = "/static/image/avatar.jpg";
    String DEFAULT_FILE_PATH ="/static/%d/%s";
    int DEFAULT_AVATAR_WIDTH = 64;
    int DEFAULT_AVATAR_HEIGHT = 64;

    String EMPLOYEE_DRIVER_LICENSE_TITLE = "driver_license";

    String VISA_NOTIFICATION_SUBJECT = "IMPORTANT! Files are required to be upload!";
    String VISA_NOTIFICATION_BODY = "Hi %s,\n" +
            "This is the email from sausage.com that reminds you to upload your %s.";

    String GENERATE_TOKEN_NOTIFICATION_SUBJECT ="CONGRATS! You are accepted by SAUSAGE.COM, please register you work account";
    String GENERATE_TOKEN_NOTIFICATION_BODY = "Congrats,\n" +
            "You are hired as the position of %s, start from %s to %s.\n" +
            "This is the email from sausage.com that your registration token is \n" +
            "%s\n" +
            "Please use it in 3 hours. If the token is expired, please contact HR again.";
}

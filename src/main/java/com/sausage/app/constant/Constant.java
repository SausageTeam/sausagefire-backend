package com.sausage.app.constant;

public interface Constant {
    String JWT_TOKEN_COOKIE_NAME = "JWT-TOKEN";
    String SIGNING_KEY = "signingKey";

    String SECRET_KEY = "sausage";

    String DEFAULT_AVATAR_PATH = "/static/image/avatar.jpg";
    String EMPLOYEE_AVATAR_PATH ="/static/%d/avatar.jpg";
    int DEFAULT_AVATAR_WIDTH = 64;
    int DEFAULT_AVATAR_HEIGHT = 64;

    String EMPLOYEE_DRIVER_LICENSE_TITLE = "driver_license";

    String FILE_PATH = "/static/%d/%s.pdf";

    String VISA_NOTIFICATION = "Hi %s, this is the email from sausage.com that reminds you to upload your %s.";
    String GENERATE_TOKEN_NOTIFICATION = "Hi, this is the email from sausage.com that your registration token is %s, please use it in 3 hours. " +
            "If the token is expired, please contact HR again.";
}

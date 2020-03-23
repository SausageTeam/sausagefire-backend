package com.sausage.app.constant;

public interface Constant {
    String JWT_TOKEN_COOKIE_NAME = "JWT-TOKEN";
    String SIGNING_KEY = "signingKey";

    String DEFAULT_AVATAR_PATH = "/static/image/avatar.jpg";
    String EMPLOYEE_AVATAR_PATH ="/static/%d/avatar.jpg";
    int DEFAULT_AVATAR_WIDTH = 64;
    int DEFAULT_AVATAR_HEIGHT = 64;

    String EMPLOYEE_DRIVER_LICENSE_PATH = "/static/%d/driver_license.pdf";

    String APPLICATION_WORK_FLOW_PATH = "/static/%d/%d.pdf";
}

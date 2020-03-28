package com.sausage.app.controller.common;

import com.sausage.app.domain.common.GenericResponse;
import com.sausage.app.domain.common.ServiceStatus;
import com.sausage.app.domain.common.auth.Auth;
import com.sausage.app.domain.common.auth.AuthGetResponse;
import com.sausage.app.security.util.JwtUtil;
import com.sausage.app.service.common.auth.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

import static com.sausage.app.constant.Constant.*;

@RestController
@RequestMapping("/app")
public class AuthController {

    AuthService authService;

    @Autowired
    public void setAuthService(AuthService authService) {
        this.authService = authService;
    }

    @GetMapping("/auth")
    public @ResponseBody
    AuthGetResponse getAuth(HttpServletRequest httpServletRequest) {
        AuthGetResponse authGetResponse = new AuthGetResponse();
        String id = JwtUtil.getSubject(httpServletRequest, JWT_TOKEN_COOKIE_NAME, SIGNING_KEY);
        if (id == null) {
            authGetResponse.setRedirectUrl(AUTH_SERVICE);
            prepareResponse(authGetResponse, "401", false, "User not Found");
        } else {
            int userId = Integer.parseInt(id);
            Auth auth = authService.getAuth(userId);
            if (auth == null) {
                authGetResponse.setRedirectUrl(AUTH_SERVICE);
                prepareResponse(authGetResponse, "500", false, "Unexpected Error");
            } else {
                authGetResponse.setAuth(auth);
                prepareResponse(authGetResponse, "200", true, "");
            }
        }
        return authGetResponse;
    }

    private void prepareResponse(GenericResponse response, String statusCode, boolean success, String errorMessage) {
        response.setServiceStatus(new ServiceStatus(statusCode, success, errorMessage));
    }

}

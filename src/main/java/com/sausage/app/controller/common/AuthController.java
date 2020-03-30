package com.sausage.app.controller.common;

import com.sausage.app.domain.common.GenericResponse;
import com.sausage.app.domain.common.ServiceStatus;
import com.sausage.app.domain.common.auth.Auth;
import com.sausage.app.domain.common.auth.AuthGetResponse;
import com.sausage.app.security.util.JwtUtil;
import com.sausage.app.service.common.auth.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MultiValueMap;
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
    public ResponseEntity<String> getAuth(HttpServletRequest httpServletRequest) {
        ResponseEntity<String> responseEntity;
        HttpHeaders httpHeaders = new HttpHeaders();

        AuthGetResponse authGetResponse = new AuthGetResponse();
        String id = JwtUtil.getSubject(httpServletRequest, JWT_TOKEN_COOKIE_NAME, SIGNING_KEY);
        if (id == null) {
            httpHeaders.add("redirectUrl", AUTH_SERVICE);
            responseEntity = ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .headers(httpHeaders)
                    .body("Sorry, you need to login first 😅");
        } else {
            int userId = Integer.parseInt(id);
            Auth auth = authService.getAuth(userId);
            if (auth == null) {
                httpHeaders.add("redirectUrl", AUTH_SERVICE);
                responseEntity = ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .headers(httpHeaders)
                        .body("Sorry, no data found 😅");
            } else {
                httpHeaders.add("roleId", String.valueOf(auth.getRoleId()));
                httpHeaders.add("onboardingStatus", String.valueOf(auth.getOnboardingStatus()));
                httpHeaders.add("ifNeedVisa", String.valueOf(auth.isIfNeedVisa()));
                responseEntity = ResponseEntity.ok()
                        .headers(httpHeaders)
                        .body("Hello 😇");
            }
        }
        return responseEntity;
    }

    private void prepareResponse(GenericResponse response, String statusCode, boolean success, String errorMessage) {
        response.setServiceStatus(new ServiceStatus(statusCode, success, errorMessage));
    }

}

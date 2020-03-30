package com.sausage.app.controller.common;

import com.sausage.app.domain.common.GenericResponse;
import com.sausage.app.domain.common.ServiceStatus;
import com.sausage.app.domain.common.auth.Auth;
import com.sausage.app.domain.common.auth.AuthGetResponse;
import com.sausage.app.domain.common.nav.Nav;
import com.sausage.app.domain.common.nav.NavGetResponse;
import com.sausage.app.security.util.JwtUtil;
import com.sausage.app.service.common.nav.NavService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

import static com.sausage.app.constant.Constant.*;

@RestController
@RequestMapping("/app")
public class NavController {

    private NavService navService;

    @Autowired
    public void setNavService(NavService navService) {
        this.navService = navService;
    }

    @GetMapping("/nav")
    public ResponseEntity<Object> getNavAvatar(HttpServletRequest httpServletRequest) {
        ResponseEntity<Object> responseEntity;
        HttpHeaders httpHeaders = new HttpHeaders();

        NavGetResponse navGetResponse = new NavGetResponse();
        String id = JwtUtil.getSubject(httpServletRequest, JWT_TOKEN_COOKIE_NAME, SIGNING_KEY);
        if (id == null) {
            responseEntity = ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .headers(httpHeaders)
                    .body("Sorry, you are not authorized 😅");
        } else {
            int userId = Integer.parseInt(id);
            Nav nav = navService.getNav(userId);
            if (nav == null) {
                responseEntity = ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .headers(httpHeaders)
                        .body("Sorry, no data found 😅");
            } else {
                navGetResponse.setNav(nav);
                responseEntity = ResponseEntity.ok()
                        .headers(httpHeaders)
                        .body(navGetResponse);
            }
        }
        return responseEntity;
    }

}

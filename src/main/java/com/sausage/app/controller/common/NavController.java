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
    public @ResponseBody
    NavGetResponse getNavAvatar(HttpServletRequest httpServletRequest) {
        NavGetResponse navGetResponse = new NavGetResponse();
        String id = JwtUtil.getSubject(httpServletRequest, JWT_TOKEN_COOKIE_NAME, SIGNING_KEY);
        if (id == null) {
            navGetResponse.setRedirectUrl(AUTH_SERVICE);
            prepareResponse(navGetResponse, "401", false, "User not login");
        } else {
            int userId = Integer.parseInt(id);
            Nav nav = navService.getNav(userId);
            if (nav == null) {
                navGetResponse.setRedirectUrl(AUTH_SERVICE);
                prepareResponse(navGetResponse, "500", false, "Unexpected Error");
            } else {
                navGetResponse.setNav(nav);
                prepareResponse(navGetResponse, "200", true, "");
            }
        }
        return navGetResponse;
    }

    private void prepareResponse(GenericResponse response, String statusCode, boolean success, String errorMessage) {
        response.setServiceStatus(new ServiceStatus(statusCode, success, errorMessage));
    }

}

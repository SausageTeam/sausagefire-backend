package com.sausage.app.controller.common;

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
        System.out.println("id" + id);
        if (id != null) {
            int userId = Integer.parseInt(id);
            System.out.println(userId);
            Nav nav = navService.getNav(userId);
            navGetResponse.setNav(nav);
        }
        return navGetResponse;
    }
}

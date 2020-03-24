package com.sausage.app.controller.common;

import com.sausage.app.domain.common.app.AppDomain;
import com.sausage.app.domain.common.app.AppResponse;
import com.sausage.app.service.common.app.AppControllerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/app")
public class AppController {

    private AppControllerService appControllerService;

    @Autowired
    public void setAppControllerService(AppControllerService appControllerService) {
        this.appControllerService = appControllerService;
    }

    @GetMapping("/avatar")
    public @ResponseBody AppResponse getAppAvatar(HttpServletRequest httpServletRequest){
        AppResponse appResponse = new AppResponse();
//        int userId = Integer.parseInt(JwtUtil.getSubject(httpServletRequest, Constant.JWT_TOKEN_COOKIE_NAME, Constant.SIGNING_KEY));
        int userId = 2;
        AppDomain appDomain = appControllerService.getAppDomain(userId);
        appResponse.setAppDomain(appDomain);
        return appResponse;
    }
}

package com.sausage.app.controller.employee;

import com.sausage.app.domain.common.ServiceStatus;
import com.sausage.app.domain.onboarding.onboardingAvatar.OnboardingAvatar;
import com.sausage.app.domain.onboarding.onboardingAvatar.OnboardingAvatarGetResponse;
import com.sausage.app.domain.onboarding.onboardingPerson.OnboardingPerson;
import com.sausage.app.domain.onboarding.onboardingPerson.OnboardingPersonRequest;
import com.sausage.app.domain.onboarding.onboardingPerson.OnboardingPersonResponse;
import com.sausage.app.service.onboarding.OnboardingPersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/employee/onboarding")
public class OnboardingController {

    private OnboardingPersonService onboardingPersonService;

    @Autowired
    public void setOnboardingPersonService(OnboardingPersonService onboardingPersonService) {
        this.onboardingPersonService = onboardingPersonService;
    }

    /**
     * Person page
     */
    @GetMapping(value = "/person")
    public @ResponseBody OnboardingPersonResponse getOnboardingPerson(HttpServletRequest httpServletRequest){
        OnboardingPersonResponse res = new OnboardingPersonResponse();

//        int userId = Integer.parseInt(JwtUtil.getSubject(httpServletRequest, Constant.JWT_TOKEN_COOKIE_NAME, Constant.SIGNING_KEY));
        int userId = 9;
        OnboardingPerson onboardingPerson = onboardingPersonService.getOnboardingPerson(userId);
        res.setOnboardingPerson(onboardingPerson);
        prepareResponse(res, true, "");
        return res;
    }

    @PostMapping(value = "/person", consumes = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody OnboardingPersonResponse postOnboardingPerson(@RequestBody OnboardingPersonRequest req){
        OnboardingPersonResponse res = new OnboardingPersonResponse();
        //        int userId = Integer.parseInt(JwtUtil.getSubject(httpServletRequest, Constant.JWT_TOKEN_COOKIE_NAME, Constant.SIGNING_KEY));
        int userId = 8;
        res.setOnboardingPerson(req.getOnboardingPerson());
        onboardingPersonService.postOnboardingPerson(userId, req);
        prepareResponse(res, true, "");
        return res;
    }

    /**
     * Avatar page
     */
//    @GetMapping(value = "/avatar")
//    public @ResponseBody
//    OnboardingAvatarGetResponse getOnboardingAvatar(HttpServletRequest httpServletRequest){
//        OnboardingAvatarGetResponse onboardingAvatarGetResponse = new OnboardingAvatarGetResponse();
//        //        int userId = Integer.parseInt(JwtUtil.getSubject(httpServletRequest, Constant.JWT_TOKEN_COOKIE_NAME, Constant.SIGNING_KEY));
//        int userId = 9;
//        OnboardingAvatar onboardingAvatar
//
//    }
//    @PostMapping(value = "/avatar", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
//    public @ResponseBody
//    OnboardingAvatarGetResponse postOnboardingAvatar(@RequestBody OnboardingAvatarGetRequest onboardingAvatarRequest){
//
//    }

    private void prepareResponse(OnboardingPersonResponse response, boolean success, String errorMessage) {
        response.setServiceStatus(new ServiceStatus(success ? "SUCCESS" : "FAILED", success, errorMessage));
    }

}

package com.sausage.app.controller.employee;

import com.sausage.app.domain.common.GenericResponse;
import com.sausage.app.domain.common.ServiceStatus;
import com.sausage.app.domain.onboarding.onboardingAvatar.OnboardingAvatar;
import com.sausage.app.domain.onboarding.onboardingAvatar.OnboardingAvatarGetResponse;
import com.sausage.app.domain.onboarding.onboardingAvatar.OnboardingAvatarPostRequest;
import com.sausage.app.domain.onboarding.onboardingAvatar.OnboardingAvatarPostResponse;
import com.sausage.app.domain.onboarding.onboardingPerson.OnboardingPerson;
import com.sausage.app.domain.onboarding.onboardingPerson.OnboardingPersonRequest;
import com.sausage.app.domain.onboarding.onboardingPerson.OnboardingPersonResponse;
import com.sausage.app.domain.onboarding.onboardingVisa.OnboardingVisa;
import com.sausage.app.domain.onboarding.onboardingVisa.OnboardingVisaGetResponse;
import com.sausage.app.domain.onboarding.onboardingVisa.OnboardingVisaPostRequest;
import com.sausage.app.domain.onboarding.onboardingVisa.OnboardingVisaPostResponse;
import com.sausage.app.service.onboarding.OnboardingAvatarService;
import com.sausage.app.service.onboarding.OnboardingPersonService;
import com.sausage.app.service.onboarding.OnboardingVisaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.io.File;

@RestController
@RequestMapping("/employee/onboarding")
public class OnboardingController {

    private OnboardingPersonService onboardingPersonService;

    private OnboardingAvatarService onboardingAvatarService;

    private OnboardingVisaService onboardingVisaService;

    @Autowired
    public void setOnboardingPersonService(OnboardingPersonService onboardingPersonService) {
        this.onboardingPersonService = onboardingPersonService;
    }

    @Autowired
    public void setOnboardingAvatarService(OnboardingAvatarService onboardingAvatarService) {
        this.onboardingAvatarService = onboardingAvatarService;
    }

    @Autowired
    public void setOnboardingVisaService(OnboardingVisaService onboardingVisaService) {
        this.onboardingVisaService = onboardingVisaService;
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
        int userId = 9;
        res.setOnboardingPerson(req.getOnboardingPerson());
        onboardingPersonService.postOnboardingPerson(userId, req);
        prepareResponse(res, true, "");
        return res;
    }

    /**
     * Avatar page
     */
    @GetMapping(value = "/avatar")
    public @ResponseBody
    OnboardingAvatarGetResponse getOnboardingAvatar(HttpServletRequest httpServletRequest){
        OnboardingAvatarGetResponse onboardingAvatarGetResponse = new OnboardingAvatarGetResponse();
        //        int userId = Integer.parseInt(JwtUtil.getSubject(httpServletRequest, Constant.JWT_TOKEN_COOKIE_NAME, Constant.SIGNING_KEY));
        int userId = 9;
        OnboardingAvatar onboardingAvatar = onboardingAvatarService.getOnboardingAvatar(userId);
        onboardingAvatarGetResponse.setOnboardingAvatar(onboardingAvatar);
        prepareResponse(onboardingAvatarGetResponse, true, "");
        return onboardingAvatarGetResponse;
    }

    @PostMapping(value = "/avatar", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    public @ResponseBody
    OnboardingAvatarPostResponse postOnboardingAvatar(@RequestBody OnboardingAvatarPostRequest onboardingAvatarPostRequest){
        OnboardingAvatarPostResponse onboardingAvatarPostResponse = new OnboardingAvatarPostResponse();
        //        int userId = Integer.parseInt(JwtUtil.getSubject(httpServletRequest, Constant.JWT_TOKEN_COOKIE_NAME, Constant.SIGNING_KEY));
        int userId = 9;
        OnboardingAvatar onboardingAvatar = onboardingAvatarPostRequest.getOnboardingAvatar();
        onboardingAvatarService.setOnboardingAvatar(userId, onboardingAvatar);
        prepareResponse(onboardingAvatarPostResponse, true, "");
        return onboardingAvatarPostResponse;
    }

    /**
     * Visa page
     */
    @GetMapping(value = "/visa")
    public @ResponseBody
    OnboardingVisaGetResponse getOnboardingVisa(HttpServletRequest httpServletRequest){
        OnboardingVisaGetResponse onboardingVisaGetResponse = new OnboardingVisaGetResponse();
        //        int userId = Integer.parseInt(JwtUtil.getSubject(httpServletRequest, Constant.JWT_TOKEN_COOKIE_NAME, Constant.SIGNING_KEY));
        int userId = 9;
        OnboardingVisa onboardingVisa = onboardingVisaService.getOnboardingVisa(userId);
        onboardingVisaGetResponse.setOnboardingVisa(onboardingVisa);
        prepareResponse(onboardingVisaGetResponse, true, "");
        return onboardingVisaGetResponse;
    }

    @PostMapping(value = "/visa")
    public @ResponseBody
    OnboardingVisaPostResponse postOnboardingVisa(@RequestBody OnboardingVisaPostRequest onboardingVisaPostRequest){
        OnboardingVisaPostResponse onboardingVisaPostResponse = new OnboardingVisaPostResponse();
        //        int userId = Integer.parseInt(JwtUtil.getSubject(httpServletRequest, Constant.JWT_TOKEN_COOKIE_NAME, Constant.SIGNING_KEY));
        int userId = 9;
        OnboardingVisa onboardingVisa = onboardingVisaPostRequest.getOnboardingVisa();
        onboardingVisaService.setOnboardingVisa(userId, onboardingVisa);
        prepareResponse(onboardingVisaPostResponse, true, "");
        return onboardingVisaPostResponse;
    }


    private void prepareResponse(GenericResponse response, boolean success, String errorMessage) {
        response.setServiceStatus(new ServiceStatus(success ? "SUCCESS" : "FAILED", success, errorMessage));
    }

}

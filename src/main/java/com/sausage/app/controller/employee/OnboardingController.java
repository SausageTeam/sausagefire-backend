package com.sausage.app.controller.employee;

import com.sausage.app.domain.common.GenericResponse;
import com.sausage.app.domain.common.ServiceStatus;
import com.sausage.app.domain.onboarding.onboardingAvatar.OnboardingAvatar;
import com.sausage.app.domain.onboarding.onboardingAvatar.OnboardingAvatarGetResponse;
import com.sausage.app.domain.onboarding.onboardingAvatar.OnboardingAvatarPostRequest;
import com.sausage.app.domain.onboarding.onboardingAvatar.OnboardingAvatarPostResponse;
import com.sausage.app.domain.onboarding.onboardingDriving.OnboardingDriving;
import com.sausage.app.domain.onboarding.onboardingDriving.OnboardingDrivingGetResponse;
import com.sausage.app.domain.onboarding.onboardingDriving.OnboardingDrivingPostRequest;
import com.sausage.app.domain.onboarding.onboardingDriving.OnboardingDrivingPostResponse;
import com.sausage.app.domain.onboarding.onboardingPerson.OnboardingPerson;
import com.sausage.app.domain.onboarding.onboardingPerson.OnboardingPersonRequest;
import com.sausage.app.domain.onboarding.onboardingPerson.OnboardingPersonResponse;
import com.sausage.app.domain.onboarding.onboardingReference.OnboardingReference;
import com.sausage.app.domain.onboarding.onboardingReference.OnboardingReferenceGetResponse;
import com.sausage.app.domain.onboarding.onboardingReference.OnboardingReferencePostRequest;
import com.sausage.app.domain.onboarding.onboardingReference.OnboardingReferencePostResponse;
import com.sausage.app.domain.onboarding.onboardingVisa.OnboardingVisa;
import com.sausage.app.domain.onboarding.onboardingVisa.OnboardingVisaGetResponse;
import com.sausage.app.domain.onboarding.onboardingVisa.OnboardingVisaPostRequest;
import com.sausage.app.domain.onboarding.onboardingVisa.OnboardingVisaPostResponse;
import com.sausage.app.service.onboarding.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/employee/onboarding")
public class OnboardingController {

    private OnboardingPersonService onboardingPersonService;

    private OnboardingAvatarService onboardingAvatarService;

    private OnboardingVisaService onboardingVisaService;

    private OnboardingDrivingService onboardingDrivingService;

    private OnboardingReferenceService onboardingReferenceService;

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

    @Autowired
    public void setOnboardingDrivingService(OnboardingDrivingService onboardingDrivingService) {
        this.onboardingDrivingService = onboardingDrivingService;
    }

    @Autowired
    public void setOnboardingReferenceService(OnboardingReferenceService onboardingReferenceService) {
        this.onboardingReferenceService = onboardingReferenceService;
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

    @PostMapping(value = "/avatar", consumes = MediaType.APPLICATION_JSON_VALUE)
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

    @PostMapping(value = "/visa", consumes = MediaType.APPLICATION_JSON_VALUE)
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

    /**
     * Driving page
     */
    @GetMapping(value = "/driving")
    public @ResponseBody
    OnboardingDrivingGetResponse getOnboardingDriving(HttpServletRequest httpServletRequest){
        OnboardingDrivingGetResponse onboardingDrivingGetResponse = new OnboardingDrivingGetResponse();
        //        int userId = Integer.parseInt(JwtUtil.getSubject(httpServletRequest, Constant.JWT_TOKEN_COOKIE_NAME, Constant.SIGNING_KEY));
        int userId = 9;
        OnboardingDriving onboardingDriving = onboardingDrivingService.getOnboardingDriving(userId);
        onboardingDrivingGetResponse.setOnboardingDriving(onboardingDriving);
        prepareResponse(onboardingDrivingGetResponse, true, "");
        return  onboardingDrivingGetResponse;
    }

    @PostMapping(value = "/driving", consumes = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody
    OnboardingDrivingPostResponse postOnboardingDriving(@RequestBody OnboardingDrivingPostRequest onboardingDrivingPostRequest){
        OnboardingDrivingPostResponse onboardingDrivingPostResponse = new OnboardingDrivingPostResponse();
        //        int userId = Integer.parseInt(JwtUtil.getSubject(httpServletRequest, Constant.JWT_TOKEN_COOKIE_NAME, Constant.SIGNING_KEY));
        int userId = 9;
        OnboardingDriving onboardingDriving = onboardingDrivingPostRequest.getOnboardingDriving();
        onboardingDrivingService.setOnboardingDriving(userId, onboardingDriving);
        prepareResponse(onboardingDrivingPostResponse, true, "");
        return onboardingDrivingPostResponse;
    }

    /**
     * Reference page
     */
    @GetMapping(value = "/reference")
    public @ResponseBody
    OnboardingReferenceGetResponse getOnboardingReference(HttpServletRequest httpServletRequest){
        OnboardingReferenceGetResponse onboardingReferenceGetResponse = new OnboardingReferenceGetResponse();
        //        int userId = Integer.parseInt(JwtUtil.getSubject(httpServletRequest, Constant.JWT_TOKEN_COOKIE_NAME, Constant.SIGNING_KEY));
        int userId = 9;
        OnboardingReference onboardingReference = onboardingReferenceService.getOnboardingReference(userId);
        onboardingReferenceGetResponse.setOnboardingReference(onboardingReference);
        prepareResponse(onboardingReferenceGetResponse, true, "");
        return onboardingReferenceGetResponse;
    }

    @PostMapping(value = "/reference", consumes = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody
    OnboardingReferencePostResponse postOnboardingReference(@RequestBody OnboardingReferencePostRequest onboardingReferencePostRequest){
        OnboardingReferencePostResponse onboardingReferencePostResponse = new OnboardingReferencePostResponse();
        //        int userId = Integer.parseInt(JwtUtil.getSubject(httpServletRequest, Constant.JWT_TOKEN_COOKIE_NAME, Constant.SIGNING_KEY));
        int userId = 9;
        OnboardingReference onboardingReference = onboardingReferencePostRequest.getOnboardingReference();
        onboardingReferenceService.setOnboardingReference(userId, onboardingReference);
        prepareResponse(onboardingReferencePostResponse, true, "");
        return onboardingReferencePostResponse;
    }

    private void prepareResponse(GenericResponse response, boolean success, String errorMessage) {
        response.setServiceStatus(new ServiceStatus(success ? "SUCCESS" : "FAILED", success, errorMessage));
    }

}

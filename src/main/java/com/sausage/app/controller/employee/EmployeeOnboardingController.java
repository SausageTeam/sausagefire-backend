package com.sausage.app.controller.employee;

import com.sausage.app.domain.common.GenericResponse;
import com.sausage.app.domain.common.ServiceStatus;
import com.sausage.app.domain.employee.onboarding.onboardingAvatar.OnboardingAvatar;
import com.sausage.app.domain.employee.onboarding.onboardingAvatar.OnboardingAvatarGetResponse;
import com.sausage.app.domain.employee.onboarding.onboardingAvatar.OnboardingAvatarPostRequest;
import com.sausage.app.domain.employee.onboarding.onboardingAvatar.OnboardingAvatarPostResponse;
import com.sausage.app.domain.employee.onboarding.onboardingDriving.OnboardingDriving;
import com.sausage.app.domain.employee.onboarding.onboardingDriving.OnboardingDrivingGetResponse;
import com.sausage.app.domain.employee.onboarding.onboardingDriving.OnboardingDrivingPostRequest;
import com.sausage.app.domain.employee.onboarding.onboardingDriving.OnboardingDrivingPostResponse;
import com.sausage.app.domain.employee.onboarding.onboardingEmergency.OnboardingEmergency;
import com.sausage.app.domain.employee.onboarding.onboardingEmergency.OnboardingEmergencyGetResponse;
import com.sausage.app.domain.employee.onboarding.onboardingEmergency.OnboardingEmergencyPostRequest;
import com.sausage.app.domain.employee.onboarding.onboardingEmergency.OnboardingEmergencyPostResponse;
import com.sausage.app.domain.employee.onboarding.onboardingPerson.*;
import com.sausage.app.domain.employee.onboarding.onboardingReference.OnboardingReference;
import com.sausage.app.domain.employee.onboarding.onboardingReference.OnboardingReferenceGetResponse;
import com.sausage.app.domain.employee.onboarding.onboardingReference.OnboardingReferencePostRequest;
import com.sausage.app.domain.employee.onboarding.onboardingReference.OnboardingReferencePostResponse;
import com.sausage.app.domain.employee.onboarding.onboardingVisa.OnboardingVisa;
import com.sausage.app.domain.employee.onboarding.onboardingVisa.OnboardingVisaGetResponse;
import com.sausage.app.domain.employee.onboarding.onboardingVisa.OnboardingVisaPostRequest;
import com.sausage.app.domain.employee.onboarding.onboardingVisa.OnboardingVisaPostResponse;
import com.sausage.app.fileIO.URIConvert;
import com.sausage.app.service.common.FileStorageService;
import com.sausage.app.service.employee.onboarding.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@RestController
@RequestMapping("/employee/onboarding")
public class EmployeeOnboardingController {

    private EmployeeOnboardingPersonService employeeOnboardingPersonService;

    private EmployeeOnboardingAvatarService employeeOnboardingAvatarService;

    private EmployeeOnboardingVisaService employeeOnboardingVisaService;

    private EmployeeOnboardingDrivingService employeeOnboardingDrivingService;

    private EmployeeOnboardingReferenceService employeeOnboardingReferenceService;

    private EmployeeOnboardingEmergencyService employeeOnboardingEmergencyService;

    @Autowired
    public void setEmployeeOnboardingPersonService(EmployeeOnboardingPersonService employeeOnboardingPersonService) {
        this.employeeOnboardingPersonService = employeeOnboardingPersonService;
    }

    @Autowired
    public void setEmployeeOnboardingAvatarService(EmployeeOnboardingAvatarService employeeOnboardingAvatarService) {
        this.employeeOnboardingAvatarService = employeeOnboardingAvatarService;
    }

    @Autowired
    public void setEmployeeOnboardingVisaService(EmployeeOnboardingVisaService employeeOnboardingVisaService) {
        this.employeeOnboardingVisaService = employeeOnboardingVisaService;
    }

    @Autowired
    public void setEmployeeOnboardingDrivingService(EmployeeOnboardingDrivingService employeeOnboardingDrivingService) {
        this.employeeOnboardingDrivingService = employeeOnboardingDrivingService;
    }

    @Autowired
    public void setEmployeeOnboardingReferenceService(EmployeeOnboardingReferenceService employeeOnboardingReferenceService) {
        this.employeeOnboardingReferenceService = employeeOnboardingReferenceService;
    }

    @Autowired
    public void setEmployeeOnboardingEmergencyService(EmployeeOnboardingEmergencyService employeeOnboardingEmergencyService) {
        this.employeeOnboardingEmergencyService = employeeOnboardingEmergencyService;
    }

    /**
     * Person page
     */
    @GetMapping(value = "/person")
    public @ResponseBody
    OnboardingPersonGetResponse getOnboardingPerson(HttpServletRequest httpServletRequest) {
        OnboardingPersonGetResponse onboardingPersonGetResponse = new OnboardingPersonGetResponse();
//        int userId = Integer.parseInt(JwtUtil.getSubject(httpServletRequest, Constant.JWT_TOKEN_COOKIE_NAME, Constant.SIGNING_KEY));
        int userId = 2;
        OnboardingPerson onboardingPerson = employeeOnboardingPersonService.getOnboardingPerson(userId);
        if (onboardingPerson == null) {
            prepareResponse(onboardingPersonGetResponse, false, "Unexpected Error. It might be caused by missing data");
        } else {
            onboardingPersonGetResponse.setOnboardingPerson(onboardingPerson);
            prepareResponse(onboardingPersonGetResponse, true, "");
        }
        return onboardingPersonGetResponse;
    }

    @PostMapping(value = "/person", consumes = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody
    OnboardingPersonPostResponse setOnboardingPerson(@RequestBody OnboardingPersonPostRequest onboardingPersonPostRequest) {
        OnboardingPersonPostResponse onboardingPersonPostResponse = new OnboardingPersonPostResponse();
        //        int userId = Integer.parseInt(JwtUtil.getSubject(httpServletRequest, Constant.JWT_TOKEN_COOKIE_NAME, Constant.SIGNING_KEY));
        int userId = 2;
        OnboardingPerson onboardingPerson = onboardingPersonPostRequest.getOnboardingPerson();
        if (onboardingPerson == null) {
            prepareResponse(onboardingPersonPostResponse, false, "Unexpected Error.");
        } else {
            onboardingPersonPostResponse.setOnboardingPerson(onboardingPerson);
            employeeOnboardingPersonService.setOnboardingPerson(userId, onboardingPerson);
            prepareResponse(onboardingPersonPostResponse, true, "");
        }
        return onboardingPersonPostResponse;
    }

    /**
     * Avatar page
     */
    @GetMapping(value = "/avatar")
    public @ResponseBody
    OnboardingAvatarGetResponse getOnboardingAvatar(HttpServletRequest httpServletRequest) throws IOException {
        OnboardingAvatarGetResponse onboardingAvatarGetResponse = new OnboardingAvatarGetResponse();
        //        int userId = Integer.parseInt(JwtUtil.getSubject(httpServletRequest, Constant.JWT_TOKEN_COOKIE_NAME, Constant.SIGNING_KEY));
        int userId = 2;
        OnboardingAvatar onboardingAvatar = employeeOnboardingAvatarService.getOnboardingAvatar(userId);
        onboardingAvatarGetResponse.setOnboardingAvatar(onboardingAvatar);
        prepareResponse(onboardingAvatarGetResponse, true, "");
        return onboardingAvatarGetResponse;
    }

    @PostMapping(value = "/avatar")
    public @ResponseBody
    OnboardingAvatarPostResponse postOnboardingAvatar(@RequestParam("avatar") MultipartFile file) {
        OnboardingAvatarPostResponse onboardingAvatarPostResponse = new OnboardingAvatarPostResponse();
        //        int userId = Integer.parseInt(JwtUtil.getSubject(httpServletRequest, Constant.JWT_TOKEN_COOKIE_NAME, Constant.SIGNING_KEY));
        int userId = 2;
        employeeOnboardingAvatarService.setOnboardingAvatar(userId, file);
        prepareResponse(onboardingAvatarPostResponse, true, "");
        return onboardingAvatarPostResponse;
    }

    /**
     * Visa page
     */
    @GetMapping(value = "/visa")
    public @ResponseBody
    OnboardingVisaGetResponse getOnboardingVisa(HttpServletRequest httpServletRequest) {
        OnboardingVisaGetResponse onboardingVisaGetResponse = new OnboardingVisaGetResponse();
        //        int userId = Integer.parseInt(JwtUtil.getSubject(httpServletRequest, Constant.JWT_TOKEN_COOKIE_NAME, Constant.SIGNING_KEY));
        int userId = 2;
        OnboardingVisa onboardingVisa = employeeOnboardingVisaService.getOnboardingVisa(userId);
        onboardingVisaGetResponse.setOnboardingVisa(onboardingVisa);
        prepareResponse(onboardingVisaGetResponse, true, "");
        return onboardingVisaGetResponse;
    }

    @PostMapping(value = "/visa", consumes = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody
    OnboardingVisaPostResponse postOnboardingVisa(@RequestBody OnboardingVisaPostRequest onboardingVisaPostRequest) {
        OnboardingVisaPostResponse onboardingVisaPostResponse = new OnboardingVisaPostResponse();
        //        int userId = Integer.parseInt(JwtUtil.getSubject(httpServletRequest, Constant.JWT_TOKEN_COOKIE_NAME, Constant.SIGNING_KEY));
        int userId = 2;
        OnboardingVisa onboardingVisa = onboardingVisaPostRequest.getOnboardingVisa();
        employeeOnboardingVisaService.setOnboardingVisa(userId, onboardingVisa);
        prepareResponse(onboardingVisaPostResponse, true, "");
        return onboardingVisaPostResponse;
    }

    /**
     * Driving page
     */
    @GetMapping(value = "/driving")
    public @ResponseBody
    OnboardingDrivingGetResponse getOnboardingDriving(HttpServletRequest httpServletRequest) {
        OnboardingDrivingGetResponse onboardingDrivingGetResponse = new OnboardingDrivingGetResponse();
        //        int userId = Integer.parseInt(JwtUtil.getSubject(httpServletRequest, Constant.JWT_TOKEN_COOKIE_NAME, Constant.SIGNING_KEY));
        int userId = 2;
        OnboardingDriving onboardingDriving = employeeOnboardingDrivingService.getOnboardingDriving(userId);
        onboardingDrivingGetResponse.setOnboardingDriving(onboardingDriving);
        prepareResponse(onboardingDrivingGetResponse, true, "");
        return onboardingDrivingGetResponse;
    }

    @PostMapping(value = "/driving", consumes = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody
    OnboardingDrivingPostResponse postOnboardingDriving(@RequestBody OnboardingDrivingPostRequest onboardingDrivingPostRequest) {
        OnboardingDrivingPostResponse onboardingDrivingPostResponse = new OnboardingDrivingPostResponse();
        //        int userId = Integer.parseInt(JwtUtil.getSubject(httpServletRequest, Constant.JWT_TOKEN_COOKIE_NAME, Constant.SIGNING_KEY));
        int userId = 2;
        OnboardingDriving onboardingDriving = onboardingDrivingPostRequest.getOnboardingDriving();
        employeeOnboardingDrivingService.setOnboardingDriving(userId, onboardingDriving);
        prepareResponse(onboardingDrivingPostResponse, true, "");
        return onboardingDrivingPostResponse;
    }

    /**
     * Reference page
     */
    @GetMapping(value = "/reference")
    public @ResponseBody
    OnboardingReferenceGetResponse getOnboardingReference(HttpServletRequest httpServletRequest) {
        OnboardingReferenceGetResponse onboardingReferenceGetResponse = new OnboardingReferenceGetResponse();
        //        int userId = Integer.parseInt(JwtUtil.getSubject(httpServletRequest, Constant.JWT_TOKEN_COOKIE_NAME, Constant.SIGNING_KEY));
        int userId = 2;
        OnboardingReference onboardingReference = employeeOnboardingReferenceService.getOnboardingReference(userId);
        onboardingReferenceGetResponse.setOnboardingReference(onboardingReference);
        prepareResponse(onboardingReferenceGetResponse, true, "");
        return onboardingReferenceGetResponse;
    }

    @PostMapping(value = "/reference", consumes = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody
    OnboardingReferencePostResponse postOnboardingReference(@RequestBody OnboardingReferencePostRequest onboardingReferencePostRequest) {
        OnboardingReferencePostResponse onboardingReferencePostResponse = new OnboardingReferencePostResponse();
        //        int userId = Integer.parseInt(JwtUtil.getSubject(httpServletRequest, Constant.JWT_TOKEN_COOKIE_NAME, Constant.SIGNING_KEY));
        int userId = 2;
        OnboardingReference onboardingReference = onboardingReferencePostRequest.getOnboardingReference();
        employeeOnboardingReferenceService.setOnboardingReference(userId, onboardingReference);
        prepareResponse(onboardingReferencePostResponse, true, "");
        return onboardingReferencePostResponse;
    }

    /**
     * Emergency page
     */
    @GetMapping(value = "/emergency")
    public @ResponseBody
    OnboardingEmergencyGetResponse getOnboardingEmergency(HttpServletRequest httpServletRequest) {
        OnboardingEmergencyGetResponse onboardingEmergencyGetResponse = new OnboardingEmergencyGetResponse();
        //        int userId = Integer.parseInt(JwtUtil.getSubject(httpServletRequest, Constant.JWT_TOKEN_COOKIE_NAME, Constant.SIGNING_KEY));
        int userId = 2;
        OnboardingEmergency onboardingEmergency = employeeOnboardingEmergencyService.getOnboardingEmergency(userId);
        onboardingEmergencyGetResponse.setOnboardingEmergency(onboardingEmergency);
        prepareResponse(onboardingEmergencyGetResponse, true, "");
        return onboardingEmergencyGetResponse;
    }

    @PostMapping(value = "/emergency", consumes = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody
    OnboardingEmergencyPostResponse postOnboardingEmergency(@RequestBody OnboardingEmergencyPostRequest onboardingEmergencyPostRequest) {
        OnboardingEmergencyPostResponse onboardingEmergencyPostResponse = new OnboardingEmergencyPostResponse();
        //        int userId = Integer.parseInt(JwtUtil.getSubject(httpServletRequest, Constant.JWT_TOKEN_COOKIE_NAME, Constant.SIGNING_KEY));
        int userId = 2;
        OnboardingEmergency onboardingEmergency = onboardingEmergencyPostRequest.getOnboardingEmergency();
        employeeOnboardingEmergencyService.setOnboardingEmergency(userId, onboardingEmergency);
        prepareResponse(onboardingEmergencyPostResponse, true, "");
        return onboardingEmergencyPostResponse;
    }

    private void prepareResponse(GenericResponse response, boolean success, String errorMessage) {
        response.setServiceStatus(new ServiceStatus(success ? "SUCCESS" : "FAILED", success, errorMessage));
    }

}

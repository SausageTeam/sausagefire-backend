package com.sausage.app.controller.employee;

import com.sausage.app.domain.common.GenericResponse;
import com.sausage.app.domain.common.ServiceStatus;
import com.sausage.app.domain.employee.onboarding.onboardingAvatar.OnboardingAvatar;
import com.sausage.app.domain.employee.onboarding.onboardingAvatar.OnboardingAvatarGetResponse;
import com.sausage.app.domain.employee.onboarding.onboardingAvatar.OnboardingAvatarPostResponse;
import com.sausage.app.domain.employee.onboarding.onboardingDriving.OnboardingDriving;
import com.sausage.app.domain.employee.onboarding.onboardingDriving.OnboardingDrivingGetResponse;
import com.sausage.app.domain.employee.onboarding.onboardingDriving.OnboardingDrivingPostRequest;
import com.sausage.app.domain.employee.onboarding.onboardingDriving.OnboardingDrivingPostResponse;
import com.sausage.app.domain.employee.onboarding.onboardingEmergency.OnboardingEmergency;
import com.sausage.app.domain.employee.onboarding.onboardingEmergency.OnboardingEmergencyGetResponse;
import com.sausage.app.domain.employee.onboarding.onboardingEmergency.OnboardingEmergencyPostRequest;
import com.sausage.app.domain.employee.onboarding.onboardingEmergency.OnboardingEmergencyPostResponse;
import com.sausage.app.domain.employee.onboarding.onboardingPerson.OnboardingPerson;
import com.sausage.app.domain.employee.onboarding.onboardingPerson.OnboardingPersonGetResponse;
import com.sausage.app.domain.employee.onboarding.onboardingPerson.OnboardingPersonPostRequest;
import com.sausage.app.domain.employee.onboarding.onboardingPerson.OnboardingPersonPostResponse;
import com.sausage.app.domain.employee.onboarding.onboardingReference.OnboardingReference;
import com.sausage.app.domain.employee.onboarding.onboardingReference.OnboardingReferenceGetResponse;
import com.sausage.app.domain.employee.onboarding.onboardingReference.OnboardingReferencePostRequest;
import com.sausage.app.domain.employee.onboarding.onboardingReference.OnboardingReferencePostResponse;
import com.sausage.app.domain.employee.onboarding.onboardingVisa.OnboardingVisa;
import com.sausage.app.domain.employee.onboarding.onboardingVisa.OnboardingVisaGetResponse;
import com.sausage.app.domain.employee.onboarding.onboardingVisa.OnboardingVisaPostRequest;
import com.sausage.app.domain.employee.onboarding.onboardingVisa.OnboardingVisaPostResponse;
import com.sausage.app.security.util.JwtUtil;
import com.sausage.app.service.employee.onboarding.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;

import static com.sausage.app.constant.Constant.JWT_TOKEN_COOKIE_NAME;
import static com.sausage.app.constant.Constant.SIGNING_KEY;

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
        String id = JwtUtil.getSubject(httpServletRequest, JWT_TOKEN_COOKIE_NAME, SIGNING_KEY);
        if (id == null) {
            prepareResponse(onboardingPersonGetResponse, "401", false, "User not Found");
        } else {
            int userId = Integer.parseInt(id);
            OnboardingPerson onboardingPerson = employeeOnboardingPersonService.getOnboardingPerson(userId);
            if (onboardingPerson == null) {
                prepareResponse(onboardingPersonGetResponse, "500", false, "Unexpected Error");
            } else {
                onboardingPersonGetResponse.setOnboardingPerson(onboardingPerson);
                prepareResponse(onboardingPersonGetResponse, "200", true, "");
            }
        }
        return onboardingPersonGetResponse;
    }

    @PostMapping(value = "/person", consumes = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody
    OnboardingPersonPostResponse setOnboardingPerson(HttpServletRequest httpServletRequest, @RequestBody OnboardingPersonPostRequest onboardingPersonPostRequest) {
        OnboardingPersonPostResponse onboardingPersonPostResponse = new OnboardingPersonPostResponse();
        String id = JwtUtil.getSubject(httpServletRequest, JWT_TOKEN_COOKIE_NAME, SIGNING_KEY);
        if (id == null) {
            prepareResponse(onboardingPersonPostResponse, "401", false, "User not Found");
        } else {
            int userId = Integer.parseInt(id);
            OnboardingPerson onboardingPerson = onboardingPersonPostRequest.getOnboardingPerson();
            if (onboardingPerson == null) {
                prepareResponse(onboardingPersonPostResponse, "500", false, "Unexpected Error");
            } else {
                onboardingPersonPostResponse.setOnboardingPerson(onboardingPerson);
                employeeOnboardingPersonService.setOnboardingPerson(userId, onboardingPerson);
                prepareResponse(onboardingPersonPostResponse, "200", true, "");
            }
        }
        return onboardingPersonPostResponse;
    }

    /**
     * Avatar page
     */
    @GetMapping(value = "/avatar")
    public @ResponseBody
    OnboardingAvatarGetResponse getOnboardingAvatar(HttpServletRequest httpServletRequest) {
        OnboardingAvatarGetResponse onboardingAvatarGetResponse = new OnboardingAvatarGetResponse();
        String id = JwtUtil.getSubject(httpServletRequest, JWT_TOKEN_COOKIE_NAME, SIGNING_KEY);
        if (id == null) {
            prepareResponse(onboardingAvatarGetResponse, "401", false, "User not Found");
        } else {
            int userId = Integer.parseInt(id);
            OnboardingAvatar onboardingAvatar = employeeOnboardingAvatarService.getOnboardingAvatar(userId);
            if (onboardingAvatar == null) {
                prepareResponse(onboardingAvatarGetResponse, "500", false, "Unexpected Error");
            } else
                onboardingAvatarGetResponse.setOnboardingAvatar(onboardingAvatar);
            prepareResponse(onboardingAvatarGetResponse, "200", true, "");
        }
        return onboardingAvatarGetResponse;
    }

    @PostMapping(value = "/avatar")
    public @ResponseBody
    OnboardingAvatarPostResponse postOnboardingAvatar(HttpServletRequest httpServletRequest, @RequestParam("avatar") MultipartFile file) {
        OnboardingAvatarPostResponse onboardingAvatarPostResponse = new OnboardingAvatarPostResponse();
        String id = JwtUtil.getSubject(httpServletRequest, JWT_TOKEN_COOKIE_NAME, SIGNING_KEY);
        if (id == null) {
            prepareResponse(onboardingAvatarPostResponse, "401", false, "User not Found");
        } else {
            int userId = Integer.parseInt(id);
            employeeOnboardingAvatarService.setOnboardingAvatar(userId, file);
            prepareResponse(onboardingAvatarPostResponse, "200", true, "");
        }
        return onboardingAvatarPostResponse;
    }

    /**
     * Visa page
     */
    @GetMapping(value = "/visa")
    public @ResponseBody
    OnboardingVisaGetResponse getOnboardingVisa(HttpServletRequest httpServletRequest) {
        OnboardingVisaGetResponse onboardingVisaGetResponse = new OnboardingVisaGetResponse();
        String id = JwtUtil.getSubject(httpServletRequest, JWT_TOKEN_COOKIE_NAME, SIGNING_KEY);
        if (id == null) {
            prepareResponse(onboardingVisaGetResponse, "401", false, "User not Found");
        } else {
            int userId = Integer.parseInt(id);
            OnboardingVisa onboardingVisa = employeeOnboardingVisaService.getOnboardingVisa(userId);
            if (onboardingVisa == null) {
                prepareResponse(onboardingVisaGetResponse, "500", false, "Unexpected Error");
            } else {
                onboardingVisaGetResponse.setOnboardingVisa(onboardingVisa);
                prepareResponse(onboardingVisaGetResponse, "200", true, "");
            }
        }
        return onboardingVisaGetResponse;
    }

    @PostMapping(value = "/visa", consumes = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody
    OnboardingVisaPostResponse postOnboardingVisa(HttpServletRequest httpServletRequest, @RequestBody OnboardingVisaPostRequest onboardingVisaPostRequest) {
        OnboardingVisaPostResponse onboardingVisaPostResponse = new OnboardingVisaPostResponse();
        String id = JwtUtil.getSubject(httpServletRequest, JWT_TOKEN_COOKIE_NAME, SIGNING_KEY);
        if (id == null) {
            prepareResponse(onboardingVisaPostResponse, "401", false, "User not Found");
        } else {
            int userId = Integer.parseInt(id);
            OnboardingVisa onboardingVisa = onboardingVisaPostRequest.getOnboardingVisa();
            if (onboardingVisa == null) {
                prepareResponse(onboardingVisaPostResponse, "500", false, "Unexpected Error");
            } else {
                employeeOnboardingVisaService.setOnboardingVisa(userId, onboardingVisa);
                prepareResponse(onboardingVisaPostResponse, "200", true, "");
            }
        }
        return onboardingVisaPostResponse;
    }

    /**
     * Driving page
     */
    @GetMapping(value = "/driving")
    public @ResponseBody
    OnboardingDrivingGetResponse getOnboardingDriving(HttpServletRequest httpServletRequest) {
        OnboardingDrivingGetResponse onboardingDrivingGetResponse = new OnboardingDrivingGetResponse();
        String id = JwtUtil.getSubject(httpServletRequest, JWT_TOKEN_COOKIE_NAME, SIGNING_KEY);
        if (id == null) {
            prepareResponse(onboardingDrivingGetResponse, "401", false, "User not Found");
        } else {
            int userId = Integer.parseInt(id);
            OnboardingDriving onboardingDriving = employeeOnboardingDrivingService.getOnboardingDriving(userId);
            if (onboardingDriving == null) {
                prepareResponse(onboardingDrivingGetResponse, "500", false, "Unexpected Error");
            } else {
                onboardingDrivingGetResponse.setOnboardingDriving(onboardingDriving);
                prepareResponse(onboardingDrivingGetResponse, "200", true, "");
            }
        }
        return onboardingDrivingGetResponse;
    }

    @PostMapping(value = "/driving", consumes = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody
    OnboardingDrivingPostResponse postOnboardingDriving(HttpServletRequest httpServletRequest, @RequestBody OnboardingDrivingPostRequest onboardingDrivingPostRequest) {
        OnboardingDrivingPostResponse onboardingDrivingPostResponse = new OnboardingDrivingPostResponse();
        String id = JwtUtil.getSubject(httpServletRequest, JWT_TOKEN_COOKIE_NAME, SIGNING_KEY);
        if (id == null) {
            prepareResponse(onboardingDrivingPostResponse, "401", false, "User not Found");
        } else {
            int userId = Integer.parseInt(id);
            OnboardingDriving onboardingDriving = onboardingDrivingPostRequest.getOnboardingDriving();
            if (onboardingDriving == null) {
                prepareResponse(onboardingDrivingPostResponse, "500", false, "Unexpected Error");
            } else {
                employeeOnboardingDrivingService.setOnboardingDriving(userId, onboardingDriving);
                prepareResponse(onboardingDrivingPostResponse, "200", true, "");
            }
        }
        return onboardingDrivingPostResponse;
    }

    /**
     * Reference page
     */
    @GetMapping(value = "/reference")
    public @ResponseBody
    OnboardingReferenceGetResponse getOnboardingReference(HttpServletRequest httpServletRequest) {
        OnboardingReferenceGetResponse onboardingReferenceGetResponse = new OnboardingReferenceGetResponse();
        String id = JwtUtil.getSubject(httpServletRequest, JWT_TOKEN_COOKIE_NAME, SIGNING_KEY);
        if (id == null) {
            prepareResponse(onboardingReferenceGetResponse, "401", false, "User not Found");
        } else {
            int userId = Integer.parseInt(id);
            OnboardingReference onboardingReference = employeeOnboardingReferenceService.getOnboardingReference(userId);
            if (onboardingReference == null) {
                prepareResponse(onboardingReferenceGetResponse, "500", false, "Unexpected Error");
            } else {
                onboardingReferenceGetResponse.setOnboardingReference(onboardingReference);
                prepareResponse(onboardingReferenceGetResponse, "200", true, "");
            }
        }
        return onboardingReferenceGetResponse;
    }

    @PostMapping(value = "/reference", consumes = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody
    OnboardingReferencePostResponse postOnboardingReference(HttpServletRequest httpServletRequest, @RequestBody OnboardingReferencePostRequest onboardingReferencePostRequest) {
        OnboardingReferencePostResponse onboardingReferencePostResponse = new OnboardingReferencePostResponse();
        String id = JwtUtil.getSubject(httpServletRequest, JWT_TOKEN_COOKIE_NAME, SIGNING_KEY);
        if (id == null) {
            prepareResponse(onboardingReferencePostResponse, "401", false, "User not Found");
        } else {
            int userId = Integer.parseInt(id);
            OnboardingReference onboardingReference = onboardingReferencePostRequest.getOnboardingReference();
            if (onboardingReference == null) {
                prepareResponse(onboardingReferencePostResponse, "500", false, "Unexpected Error");
            } else {
                employeeOnboardingReferenceService.setOnboardingReference(userId, onboardingReference);
                prepareResponse(onboardingReferencePostResponse, "200", true, "");
            }
        }
        return onboardingReferencePostResponse;
    }

    /**
     * Emergency page
     */
    @GetMapping(value = "/emergency")
    public @ResponseBody
    OnboardingEmergencyGetResponse getOnboardingEmergency(HttpServletRequest httpServletRequest) {
        OnboardingEmergencyGetResponse onboardingEmergencyGetResponse = new OnboardingEmergencyGetResponse();
        String id = JwtUtil.getSubject(httpServletRequest, JWT_TOKEN_COOKIE_NAME, SIGNING_KEY);
        if (id == null) {
            prepareResponse(onboardingEmergencyGetResponse, "401", false, "User not Found");
        } else {
            int userId = Integer.parseInt(id);
            OnboardingEmergency onboardingEmergency = employeeOnboardingEmergencyService.getOnboardingEmergency(userId);
            if (onboardingEmergency == null) {
                prepareResponse(onboardingEmergencyGetResponse, "500", false, "Unexpected Error");
            } else {
                onboardingEmergencyGetResponse.setOnboardingEmergency(onboardingEmergency);
                prepareResponse(onboardingEmergencyGetResponse, "200", true, "");
            }
        }
        return onboardingEmergencyGetResponse;
    }

    @PostMapping(value = "/emergency", consumes = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody
    OnboardingEmergencyPostResponse postOnboardingEmergency(HttpServletRequest httpServletRequest, @RequestBody OnboardingEmergencyPostRequest onboardingEmergencyPostRequest) {
        OnboardingEmergencyPostResponse onboardingEmergencyPostResponse = new OnboardingEmergencyPostResponse();
        String id = JwtUtil.getSubject(httpServletRequest, JWT_TOKEN_COOKIE_NAME, SIGNING_KEY);
        if (id == null) {
            prepareResponse(onboardingEmergencyPostResponse, "401", false, "User not Found");
        } else {
            int userId = Integer.parseInt(id);
            OnboardingEmergency onboardingEmergency = onboardingEmergencyPostRequest.getOnboardingEmergency();
            if (onboardingEmergency == null) {
                prepareResponse(onboardingEmergencyPostResponse, "500", false, "Unexpected Error");
            } else {
                employeeOnboardingEmergencyService.setOnboardingEmergency(userId, onboardingEmergency);
                prepareResponse(onboardingEmergencyPostResponse, "200", true, "");
            }
        }
        return onboardingEmergencyPostResponse;
    }

    private void prepareResponse(GenericResponse response, String statusCode, boolean success, String errorMessage) {
        response.setServiceStatus(new ServiceStatus(statusCode, success, errorMessage));
    }

}

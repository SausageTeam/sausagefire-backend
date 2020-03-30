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
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;

import static com.sausage.app.constant.Constant.*;

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
    public ResponseEntity<Object>
    getOnboardingPerson(HttpServletRequest httpServletRequest) {
        ResponseEntity<Object> responseEntity;
        HttpHeaders httpHeaders = new HttpHeaders();

        OnboardingPersonGetResponse onboardingPersonGetResponse = new OnboardingPersonGetResponse();
        String id = JwtUtil.getSubject(httpServletRequest, JWT_TOKEN_COOKIE_NAME, SIGNING_KEY);
        if (id == null) {
            httpHeaders.add("redirectUrl", AUTH_SERVICE);
            responseEntity = ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .headers(httpHeaders)
                    .body("Sorry, you are not authorized 😅");
        } else {
            int userId = Integer.parseInt(id);
            OnboardingPerson onboardingPerson = employeeOnboardingPersonService.getOnboardingPerson(userId);
            if (onboardingPerson == null) {
                httpHeaders.add("redirectUrl", AUTH_SERVICE);
                responseEntity = ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .headers(httpHeaders)
                        .body("Sorry, no data found 😅");
            } else {
                onboardingPersonGetResponse.setOnboardingPerson(onboardingPerson);
                responseEntity = ResponseEntity.ok()
                        .headers(httpHeaders)
                        .body(onboardingPersonGetResponse);
            }
        }
        return responseEntity;
    }

    @PostMapping(value = "/person", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object>
    setOnboardingPerson(HttpServletRequest httpServletRequest, @RequestBody OnboardingPersonPostRequest onboardingPersonPostRequest) {
        ResponseEntity<Object> responseEntity;
        HttpHeaders httpHeaders = new HttpHeaders();

        OnboardingPersonPostResponse onboardingPersonPostResponse = new OnboardingPersonPostResponse();
        String id = JwtUtil.getSubject(httpServletRequest, JWT_TOKEN_COOKIE_NAME, SIGNING_KEY);
        if (id == null) {
            httpHeaders.add("redirectUrl", AUTH_SERVICE);
            responseEntity = ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .headers(httpHeaders)
                    .body("Sorry, you are not authorized 😅");
        } else {
            int userId = Integer.parseInt(id);
            OnboardingPerson onboardingPerson = onboardingPersonPostRequest.getOnboardingPerson();
            if (onboardingPerson == null) {
                httpHeaders.add("redirectUrl", AUTH_SERVICE);
                responseEntity = ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .headers(httpHeaders)
                        .body("Sorry, no data found 😅");
            } else {
                onboardingPersonPostResponse.setOnboardingPerson(onboardingPerson);
                employeeOnboardingPersonService.setOnboardingPerson(userId, onboardingPerson);
                responseEntity = ResponseEntity.ok()
                        .headers(httpHeaders)
                        .body(onboardingPersonPostResponse);
            }
        }
        return responseEntity;
    }

    /**
     * Avatar page
     */
    @GetMapping(value = "/avatar")
    public ResponseEntity<Object>
    getOnboardingAvatar(HttpServletRequest httpServletRequest) {
        ResponseEntity<Object> responseEntity;
        HttpHeaders httpHeaders = new HttpHeaders();

        OnboardingAvatarGetResponse onboardingAvatarGetResponse = new OnboardingAvatarGetResponse();
        String id = JwtUtil.getSubject(httpServletRequest, JWT_TOKEN_COOKIE_NAME, SIGNING_KEY);
        if (id == null) {
            httpHeaders.add("redirectUrl", AUTH_SERVICE);
            responseEntity = ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .headers(httpHeaders)
                    .body("Sorry, you are not authorized 😅");
        } else {
            int userId = Integer.parseInt(id);
            OnboardingAvatar onboardingAvatar = employeeOnboardingAvatarService.getOnboardingAvatar(userId);
            if (onboardingAvatar == null) {
                httpHeaders.add("redirectUrl", AUTH_SERVICE);
                responseEntity = ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .headers(httpHeaders)
                        .body("Sorry, no data found 😅");
            } else {
                onboardingAvatarGetResponse.setOnboardingAvatar(onboardingAvatar);
                responseEntity = ResponseEntity.ok()
                        .headers(httpHeaders)
                        .body(onboardingAvatarGetResponse);
            }
        }
        return responseEntity;
    }

    @PostMapping(value = "/avatar")
    public ResponseEntity<Object>
    postOnboardingAvatar(HttpServletRequest httpServletRequest, @RequestParam("avatar") MultipartFile file) {
        ResponseEntity<Object> responseEntity;
        HttpHeaders httpHeaders = new HttpHeaders();

        OnboardingAvatarPostResponse onboardingAvatarPostResponse = new OnboardingAvatarPostResponse();
        String id = JwtUtil.getSubject(httpServletRequest, JWT_TOKEN_COOKIE_NAME, SIGNING_KEY);
        if (id == null) {
            httpHeaders.add("redirectUrl", AUTH_SERVICE);
            responseEntity = ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .headers(httpHeaders)
                    .body("Sorry, you are not authorized 😅");
        } else {
            int userId = Integer.parseInt(id);
            employeeOnboardingAvatarService.setOnboardingAvatar(userId, file);
            responseEntity = ResponseEntity.ok()
                    .headers(httpHeaders)
                    .body(onboardingAvatarPostResponse);
        }
        return responseEntity;
    }

    /**
     * Visa page
     */
    @GetMapping(value = "/visa")
    public ResponseEntity<Object>
    getOnboardingVisa(HttpServletRequest httpServletRequest) {
        ResponseEntity<Object> responseEntity;
        HttpHeaders httpHeaders = new HttpHeaders();

        OnboardingVisaGetResponse onboardingVisaGetResponse = new OnboardingVisaGetResponse();
        String id = JwtUtil.getSubject(httpServletRequest, JWT_TOKEN_COOKIE_NAME, SIGNING_KEY);
        if (id == null) {
            httpHeaders.add("redirectUrl", AUTH_SERVICE);
            responseEntity = ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .headers(httpHeaders)
                    .body("Sorry, you are not authorized 😅");
        } else {
            int userId = Integer.parseInt(id);
            OnboardingVisa onboardingVisa = employeeOnboardingVisaService.getOnboardingVisa(userId);
            if (onboardingVisa == null) {
                httpHeaders.add("redirectUrl", AUTH_SERVICE);
                responseEntity = ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .headers(httpHeaders)
                        .body("Sorry, no data found 😅");
            } else {
                onboardingVisaGetResponse.setOnboardingVisa(onboardingVisa);
                responseEntity = ResponseEntity.ok()
                        .headers(httpHeaders)
                        .body(onboardingVisaGetResponse);
            }
        }
        return responseEntity;
    }

    @PostMapping(value = "/visa", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object>
    postOnboardingVisa(HttpServletRequest httpServletRequest, @RequestBody OnboardingVisaPostRequest onboardingVisaPostRequest) {
        ResponseEntity<Object> responseEntity;
        HttpHeaders httpHeaders = new HttpHeaders();

        OnboardingVisaPostResponse onboardingVisaPostResponse = new OnboardingVisaPostResponse();
        String id = JwtUtil.getSubject(httpServletRequest, JWT_TOKEN_COOKIE_NAME, SIGNING_KEY);
        if (id == null) {
            httpHeaders.add("redirectUrl", AUTH_SERVICE);
            responseEntity = ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .headers(httpHeaders)
                    .body("Sorry, you are not authorized 😅");
        } else {
            int userId = Integer.parseInt(id);
            OnboardingVisa onboardingVisa = onboardingVisaPostRequest.getOnboardingVisa();
            if (onboardingVisa == null) {
                httpHeaders.add("redirectUrl", AUTH_SERVICE);
                responseEntity = ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .headers(httpHeaders)
                        .body("Sorry, no data found 😅");
            } else {
                employeeOnboardingVisaService.setOnboardingVisa(userId, onboardingVisa);
                responseEntity = ResponseEntity.ok()
                        .headers(httpHeaders)
                        .body(onboardingVisaPostResponse);
            }
        }
        return responseEntity;
    }

    /**
     * Driving page
     */
    @GetMapping(value = "/driving")
    public ResponseEntity<Object>
    getOnboardingDriving(HttpServletRequest httpServletRequest) {
        ResponseEntity<Object> responseEntity;
        HttpHeaders httpHeaders = new HttpHeaders();

        OnboardingDrivingGetResponse onboardingDrivingGetResponse = new OnboardingDrivingGetResponse();
        String id = JwtUtil.getSubject(httpServletRequest, JWT_TOKEN_COOKIE_NAME, SIGNING_KEY);
        if (id == null) {
            httpHeaders.add("redirectUrl", AUTH_SERVICE);
            responseEntity = ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .headers(httpHeaders)
                    .body("Sorry, you are not authorized 😅");
        } else {
            int userId = Integer.parseInt(id);
            OnboardingDriving onboardingDriving = employeeOnboardingDrivingService.getOnboardingDriving(userId);
            if (onboardingDriving == null) {
                httpHeaders.add("redirectUrl", AUTH_SERVICE);
                responseEntity = ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .headers(httpHeaders)
                        .body("Sorry, no data found 😅");
            } else {
                onboardingDrivingGetResponse.setOnboardingDriving(onboardingDriving);
                responseEntity = ResponseEntity.ok()
                        .headers(httpHeaders)
                        .body(onboardingDrivingGetResponse);
            }
        }
        return responseEntity;
    }

    @PostMapping(value = "/driving", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object>
    postOnboardingDriving(HttpServletRequest httpServletRequest, @RequestBody OnboardingDrivingPostRequest onboardingDrivingPostRequest) {
        ResponseEntity<Object> responseEntity;
        HttpHeaders httpHeaders = new HttpHeaders();

        OnboardingDrivingPostResponse onboardingDrivingPostResponse = new OnboardingDrivingPostResponse();
        String id = JwtUtil.getSubject(httpServletRequest, JWT_TOKEN_COOKIE_NAME, SIGNING_KEY);
        if (id == null) {
            httpHeaders.add("redirectUrl", AUTH_SERVICE);
            responseEntity = ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .headers(httpHeaders)
                    .body("Sorry, you are not authorized 😅");
        } else {
            int userId = Integer.parseInt(id);
            OnboardingDriving onboardingDriving = onboardingDrivingPostRequest.getOnboardingDriving();
            if (onboardingDriving == null) {
                httpHeaders.add("redirectUrl", AUTH_SERVICE);
                responseEntity = ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .headers(httpHeaders)
                        .body("Sorry, no data found 😅");
            } else {
                employeeOnboardingDrivingService.setOnboardingDriving(userId, onboardingDriving);
                responseEntity = ResponseEntity.ok()
                        .headers(httpHeaders)
                        .body(onboardingDrivingPostResponse);
            }
        }
        return responseEntity;
    }

    /**
     * Reference page
     */
    @GetMapping(value = "/reference")
    public ResponseEntity<Object>
    getOnboardingReference(HttpServletRequest httpServletRequest) {
        ResponseEntity<Object> responseEntity;
        HttpHeaders httpHeaders = new HttpHeaders();

        OnboardingReferenceGetResponse onboardingReferenceGetResponse = new OnboardingReferenceGetResponse();
        String id = JwtUtil.getSubject(httpServletRequest, JWT_TOKEN_COOKIE_NAME, SIGNING_KEY);
        if (id == null) {
            httpHeaders.add("redirectUrl", AUTH_SERVICE);
            responseEntity = ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .headers(httpHeaders)
                    .body("Sorry, you are not authorized 😅");
        } else {
            int userId = Integer.parseInt(id);
            OnboardingReference onboardingReference = employeeOnboardingReferenceService.getOnboardingReference(userId);
            if (onboardingReference == null) {
                httpHeaders.add("redirectUrl", AUTH_SERVICE);
                responseEntity = ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .headers(httpHeaders)
                        .body("Sorry, no data found 😅");
            } else {
                onboardingReferenceGetResponse.setOnboardingReference(onboardingReference);
                responseEntity = ResponseEntity.ok()
                        .headers(httpHeaders)
                        .body(onboardingReferenceGetResponse);
            }
        }
        return responseEntity;
    }

    @PostMapping(value = "/reference", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object>
    postOnboardingReference(HttpServletRequest httpServletRequest, @RequestBody OnboardingReferencePostRequest onboardingReferencePostRequest) {
        ResponseEntity<Object> responseEntity;
        HttpHeaders httpHeaders = new HttpHeaders();

        OnboardingReferencePostResponse onboardingReferencePostResponse = new OnboardingReferencePostResponse();
        String id = JwtUtil.getSubject(httpServletRequest, JWT_TOKEN_COOKIE_NAME, SIGNING_KEY);
        if (id == null) {
            httpHeaders.add("redirectUrl", AUTH_SERVICE);
            responseEntity = ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .headers(httpHeaders)
                    .body("Sorry, you are not authorized 😅");
        } else {
            int userId = Integer.parseInt(id);
            OnboardingReference onboardingReference = onboardingReferencePostRequest.getOnboardingReference();
            if (onboardingReference == null) {
                httpHeaders.add("redirectUrl", AUTH_SERVICE);
                responseEntity = ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .headers(httpHeaders)
                        .body("Sorry, no data found 😅");
            } else {
                employeeOnboardingReferenceService.setOnboardingReference(userId, onboardingReference);
                responseEntity = ResponseEntity.ok()
                        .headers(httpHeaders)
                        .body(onboardingReferencePostResponse);
            }
        }
        return responseEntity;
    }

    /**
     * Emergency page
     */
    @GetMapping(value = "/emergency")
    public ResponseEntity<Object>
    getOnboardingEmergency(HttpServletRequest httpServletRequest) {
        ResponseEntity<Object> responseEntity;
        HttpHeaders httpHeaders = new HttpHeaders();

        OnboardingEmergencyGetResponse onboardingEmergencyGetResponse = new OnboardingEmergencyGetResponse();
        String id = JwtUtil.getSubject(httpServletRequest, JWT_TOKEN_COOKIE_NAME, SIGNING_KEY);
        if (id == null) {
            httpHeaders.add("redirectUrl", AUTH_SERVICE);
            responseEntity = ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .headers(httpHeaders)
                    .body("Sorry, you are not authorized 😅");
        } else {
            int userId = Integer.parseInt(id);
            OnboardingEmergency onboardingEmergency = employeeOnboardingEmergencyService.getOnboardingEmergency(userId);
            if (onboardingEmergency == null) {
                httpHeaders.add("redirectUrl", AUTH_SERVICE);
                responseEntity = ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .headers(httpHeaders)
                        .body("Sorry, no data found 😅");
            } else {
                onboardingEmergencyGetResponse.setOnboardingEmergency(onboardingEmergency);
                responseEntity = ResponseEntity.ok()
                        .headers(httpHeaders)
                        .body(onboardingEmergencyGetResponse);
            }
        }
        return responseEntity;
    }

    @PostMapping(value = "/emergency", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object>
    postOnboardingEmergency(HttpServletRequest httpServletRequest, @RequestBody OnboardingEmergencyPostRequest onboardingEmergencyPostRequest) {
        ResponseEntity<Object> responseEntity;
        HttpHeaders httpHeaders = new HttpHeaders();

        OnboardingEmergencyPostResponse onboardingEmergencyPostResponse = new OnboardingEmergencyPostResponse();
        String id = JwtUtil.getSubject(httpServletRequest, JWT_TOKEN_COOKIE_NAME, SIGNING_KEY);
        if (id == null) {
            httpHeaders.add("redirectUrl", AUTH_SERVICE);
            responseEntity = ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .headers(httpHeaders)
                    .body("Sorry, you are not authorized 😅");
        } else {
            int userId = Integer.parseInt(id);
            OnboardingEmergency onboardingEmergency = onboardingEmergencyPostRequest.getOnboardingEmergency();
            if (onboardingEmergency == null) {
                httpHeaders.add("redirectUrl", AUTH_SERVICE);
                responseEntity = ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .headers(httpHeaders)
                        .body("Sorry, no data found 😅");
            } else {
                employeeOnboardingEmergencyService.setOnboardingEmergency(userId, onboardingEmergency);
                responseEntity = ResponseEntity.ok()
                        .headers(httpHeaders)
                        .body(onboardingEmergencyPostResponse);
            }
        }
        return responseEntity;
    }

}

package com.sausage.app.controller.employee;

import com.sausage.app.domain.employee.profile.profileAddress.ProfileAddress;
import com.sausage.app.domain.employee.profile.profileAddress.ProfileAddressGetResponse;
import com.sausage.app.domain.employee.profile.profileAddress.ProfileAddressPostRequest;
import com.sausage.app.domain.employee.profile.profileAddress.ProfileAddressPostResponse;
import com.sausage.app.domain.employee.profile.profileContact.ProfileContact;
import com.sausage.app.domain.employee.profile.profileContact.ProfileContactGetResponse;
import com.sausage.app.domain.employee.profile.profileContact.ProfileContactPostRequest;
import com.sausage.app.domain.employee.profile.profileContact.ProfileContactPostResponse;
import com.sausage.app.domain.employee.profile.profileDocument.ProfileDocumentGetResponse;
import com.sausage.app.domain.employee.profile.profileDocument.ProfileDocumentPostRequest;
import com.sausage.app.domain.employee.profile.profileDocument.ProfileDocumentPostResponse;
import com.sausage.app.domain.employee.profile.profileEmergencyContact.ProfileEmergencyContact;
import com.sausage.app.domain.employee.profile.profileEmergencyContact.ProfileEmergencyContactGetResponse;
import com.sausage.app.domain.employee.profile.profileEmergencyContact.ProfileEmergencyContactPostRequest;
import com.sausage.app.domain.employee.profile.profileEmergencyContact.ProfileEmergencyContactPostResponse;
import com.sausage.app.domain.employee.profile.profileEmployment.ProfileEmployment;
import com.sausage.app.domain.employee.profile.profileEmployment.ProfileEmploymentGetResponse;
import com.sausage.app.domain.employee.profile.profileEmployment.ProfileEmploymentPostRequest;
import com.sausage.app.domain.employee.profile.profileEmployment.ProfileEmploymentPostResponse;
import com.sausage.app.domain.employee.profile.profileName.ProfileName;
import com.sausage.app.domain.employee.profile.profileName.ProfileNameGetResponse;
import com.sausage.app.domain.employee.profile.profileName.ProfileNamePostRequest;
import com.sausage.app.domain.employee.profile.profileName.ProfileNamePostResponse;
import com.sausage.app.security.util.JwtUtil;
import com.sausage.app.service.employee.profile.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

import static com.sausage.app.constant.Constant.JWT_TOKEN_COOKIE_NAME;
import static com.sausage.app.constant.Constant.SIGNING_KEY;

@RestController
@RequestMapping("/employee/profile")
public class EmployeeProfileController {

    private EmployeeProfileNameService employeeProfileNameService;

    private EmployeeProfileAddressService employeeProfileAddressService;

    private EmployeeProfileContactService employeeProfileContactService;

    private EmployeeProfileEmploymentService employeeProfileEmploymentService;

    private EmployeeProfileEmergencyContactService employeeProfileEmergencyContactService;

    private EmployeeProfileDocumentService employeeProfileDocumentService;

    @Autowired
    public void setEmployeeProfileNameService(EmployeeProfileNameService employeeProfileNameService) {
        this.employeeProfileNameService = employeeProfileNameService;
    }

    @Autowired
    public void setEmployeeProfileAddressService(EmployeeProfileAddressService employeeProfileAddressService) {
        this.employeeProfileAddressService = employeeProfileAddressService;
    }

    @Autowired
    public void setEmployeeProfileContactService(EmployeeProfileContactService employeeProfileContactService) {
        this.employeeProfileContactService = employeeProfileContactService;
    }

    @Autowired
    public void setEmployeeProfileEmploymentService(EmployeeProfileEmploymentService employeeProfileEmploymentService) {
        this.employeeProfileEmploymentService = employeeProfileEmploymentService;
    }

    @Autowired
    public void setEmployeeProfileEmergencyContactService(EmployeeProfileEmergencyContactService employeeProfileEmergencyContactService) {
        this.employeeProfileEmergencyContactService = employeeProfileEmergencyContactService;
    }

    @Autowired
    public void setEmployeeProfileDocumentService(EmployeeProfileDocumentService employeeProfileDocumentService) {
        this.employeeProfileDocumentService = employeeProfileDocumentService;
    }

    /**
     * Name section
     */
    @GetMapping(value = "/name")
    public ResponseEntity<Object> getProfileName(HttpServletRequest httpServletRequest) {
        ResponseEntity<Object> responseEntity;
        HttpHeaders httpHeaders = new HttpHeaders();

        ProfileNameGetResponse profileNameGetResponse = new ProfileNameGetResponse();
        String id = JwtUtil.getSubject(httpServletRequest, JWT_TOKEN_COOKIE_NAME, SIGNING_KEY);
        if (id == null) {
            responseEntity = ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .headers(httpHeaders)
                    .body("Sorry, you are not authorized 😅");
        } else {
            int userId = Integer.parseInt(id);
            ProfileName profileName = employeeProfileNameService.getProfileName(userId);
            if (profileName == null) {
                responseEntity = ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .headers(httpHeaders)
                        .body("Sorry, no data found 😅");
            } else {
                profileNameGetResponse.setProfileName(profileName);
                responseEntity = ResponseEntity.ok()
                        .headers(httpHeaders)
                        .body(profileNameGetResponse);
            }
        }
        return responseEntity;
    }

    @PostMapping(value = "/name")
    public ResponseEntity<Object> postProfileName(HttpServletRequest httpServletRequest, @RequestBody ProfileNamePostRequest profileNamePostRequest) {
        ResponseEntity<Object> responseEntity;
        HttpHeaders httpHeaders = new HttpHeaders();

        ProfileNamePostResponse profileNamePostResponse = new ProfileNamePostResponse();
        String id = JwtUtil.getSubject(httpServletRequest, JWT_TOKEN_COOKIE_NAME, SIGNING_KEY);
        if (id == null) {
            responseEntity = ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .headers(httpHeaders)
                    .body("Sorry, you are not authorized 😅");
        } else {
            int userId = Integer.parseInt(id);
            ProfileName profileName = profileNamePostRequest.getProfileName();
            if (profileName == null) {
                responseEntity = ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .headers(httpHeaders)
                        .body("Sorry, no data found 😅");
            } else {
                employeeProfileNameService.setProfileName(userId, profileName);
                responseEntity = ResponseEntity.ok()
                        .headers(httpHeaders)
                        .body(profileNamePostResponse);
            }
        }
        return responseEntity;
    }

    /**
     * Address section
     */
    @GetMapping(value = "/address")
    public ResponseEntity<Object> getProfileAddress(HttpServletRequest httpServletRequest) {
        ResponseEntity<Object> responseEntity;
        HttpHeaders httpHeaders = new HttpHeaders();

        ProfileAddressGetResponse profileAddressGetResponse = new ProfileAddressGetResponse();
        String id = JwtUtil.getSubject(httpServletRequest, JWT_TOKEN_COOKIE_NAME, SIGNING_KEY);
        if (id == null) {
            responseEntity = ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .headers(httpHeaders)
                    .body("Sorry, you are not authorized 😅");
        } else {
            int userId = Integer.parseInt(id);
            ProfileAddress profileAddress = employeeProfileAddressService.getProfileAddress(userId);
            if (profileAddress == null) {
                responseEntity = ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .headers(httpHeaders)
                        .body("Sorry, no data found 😅");
            } else {
                profileAddressGetResponse.setProfileAddress(profileAddress);
                responseEntity = ResponseEntity.ok()
                        .headers(httpHeaders)
                        .body(profileAddressGetResponse);
            }
        }
        return responseEntity;
    }

    @PostMapping(value = "/address")
    public ResponseEntity<Object> postProfileAddress(HttpServletRequest httpServletRequest, @RequestBody ProfileAddressPostRequest profileAddressPostRequest) {
        ResponseEntity<Object> responseEntity;
        HttpHeaders httpHeaders = new HttpHeaders();

        ProfileAddressPostResponse profileAddressPostResponse = new ProfileAddressPostResponse();
        String id = JwtUtil.getSubject(httpServletRequest, JWT_TOKEN_COOKIE_NAME, SIGNING_KEY);
        if (id == null) {
            responseEntity = ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .headers(httpHeaders)
                    .body("Sorry, you are not authorized 😅");
        } else {
            int userId = Integer.parseInt(id);
            ProfileAddress profileAddress = profileAddressPostRequest.getProfileAddress();
            if (profileAddress == null) {
                responseEntity = ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .headers(httpHeaders)
                        .body("Sorry, no data found 😅");
            } else {
                employeeProfileAddressService.setProfileAddress(userId, profileAddress);
                responseEntity = ResponseEntity.ok()
                        .headers(httpHeaders)
                        .body(profileAddressPostResponse);
            }
        }
        return responseEntity;
    }

    /**
     * Contact section
     */
    @GetMapping(value = "/contact")
    public ResponseEntity<Object> getProfileContact(HttpServletRequest httpServletRequest) {
        ResponseEntity<Object> responseEntity;
        HttpHeaders httpHeaders = new HttpHeaders();

        ProfileContactGetResponse profileContactGetResponse = new ProfileContactGetResponse();
        String id = JwtUtil.getSubject(httpServletRequest, JWT_TOKEN_COOKIE_NAME, SIGNING_KEY);
        if (id == null) {
            responseEntity = ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .headers(httpHeaders)
                    .body("Sorry, you are not authorized 😅");
        } else {
            int userId = Integer.parseInt(id);
            ProfileContact profileContact = employeeProfileContactService.getProfileContact(userId);
            if (profileContact == null) {
                responseEntity = ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .headers(httpHeaders)
                        .body("Sorry, no data found 😅");
            } else {
                profileContactGetResponse.setProfileContact(profileContact);
                responseEntity = ResponseEntity.ok()
                        .headers(httpHeaders)
                        .body(profileContactGetResponse);
            }
        }
        return responseEntity;
    }

    @PostMapping(value = "/contact")
    public ResponseEntity<Object> postProfileContact(HttpServletRequest httpServletRequest, @RequestBody ProfileContactPostRequest profileContactPostRequest) {
        ResponseEntity<Object> responseEntity;
        HttpHeaders httpHeaders = new HttpHeaders();

        ProfileContactPostResponse profileContactPostResponse = new ProfileContactPostResponse();
        String id = JwtUtil.getSubject(httpServletRequest, JWT_TOKEN_COOKIE_NAME, SIGNING_KEY);
        if (id == null) {
            responseEntity = ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .headers(httpHeaders)
                    .body("Sorry, you are not authorized 😅");
        } else {
            int userId = Integer.parseInt(id);
            ProfileContact profileContact = profileContactPostRequest.getProfileContact();
            if (profileContact == null) {
                responseEntity = ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .headers(httpHeaders)
                        .body("Sorry, no data found 😅");
            } else {
                employeeProfileContactService.setProfileContact(userId, profileContact);
                responseEntity = ResponseEntity.ok()
                        .headers(httpHeaders)
                        .body(profileContactPostResponse);
            }
        }
        return responseEntity;
    }

    /**
     * Employment section
     */
    @GetMapping(value = "/employment")
    public ResponseEntity<Object> getProfileEmployment(HttpServletRequest httpServletRequest) {
        ResponseEntity<Object> responseEntity;
        HttpHeaders httpHeaders = new HttpHeaders();

        ProfileEmploymentGetResponse profileEmploymentGetResponse = new ProfileEmploymentGetResponse();
        String id = JwtUtil.getSubject(httpServletRequest, JWT_TOKEN_COOKIE_NAME, SIGNING_KEY);
        if (id == null) {
            responseEntity = ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .headers(httpHeaders)
                    .body("Sorry, you are not authorized 😅");
        } else {
            int userId = Integer.parseInt(id);
            ProfileEmployment profileEmployment = employeeProfileEmploymentService.getProfileEmployment(userId);
            if (profileEmployment == null) {
                responseEntity = ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .headers(httpHeaders)
                        .body("Sorry, no data found 😅");
            } else {
                profileEmploymentGetResponse.setProfileEmployment(profileEmployment);
                responseEntity = ResponseEntity.ok()
                        .headers(httpHeaders)
                        .body(profileEmploymentGetResponse);
            }
        }
        return responseEntity;
    }

    @PostMapping(value = "/employment")
    public ResponseEntity<Object> postProfileEmployment(HttpServletRequest httpServletRequest, @RequestBody ProfileEmploymentPostRequest profileEmploymentPostRequest) {
        ResponseEntity<Object> responseEntity;
        HttpHeaders httpHeaders = new HttpHeaders();

        ProfileEmploymentPostResponse profileEmploymentPostResponse = new ProfileEmploymentPostResponse();
        String id = JwtUtil.getSubject(httpServletRequest, JWT_TOKEN_COOKIE_NAME, SIGNING_KEY);
        if (id == null) {
            responseEntity = ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .headers(httpHeaders)
                    .body("Sorry, you are not authorized 😅");
        } else {
            int userId = Integer.parseInt(id);
            ProfileEmployment profileEmployment = profileEmploymentPostRequest.getProfileEmployment();
            if (profileEmployment == null) {
                responseEntity = ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .headers(httpHeaders)
                        .body("Sorry, no data found 😅");
            }else {
                employeeProfileEmploymentService.setProfileEmployment(userId, profileEmployment);
                responseEntity = ResponseEntity.ok()
                        .headers(httpHeaders)
                        .body(profileEmploymentPostResponse);
            }
        }
        return responseEntity;
    }

    /**
     * Emergency Contact section
     */
    @GetMapping(value = "/emergencyContact")
    public ResponseEntity<Object> getProfileEmergencyContact(HttpServletRequest httpServletRequest) {
        ResponseEntity<Object> responseEntity;
        HttpHeaders httpHeaders = new HttpHeaders();

        ProfileEmergencyContactGetResponse profileEmergencyContactGetResponse = new ProfileEmergencyContactGetResponse();
        String id = JwtUtil.getSubject(httpServletRequest, JWT_TOKEN_COOKIE_NAME, SIGNING_KEY);
        if (id == null) {
            responseEntity = ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .headers(httpHeaders)
                    .body("Sorry, you are not authorized 😅");
        } else {
            int userId = Integer.parseInt(id);
            ProfileEmergencyContact profileEmergencyContact = employeeProfileEmergencyContactService.getProfileEmergencyContact(userId);
            if (profileEmergencyContact == null) {
                responseEntity = ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .headers(httpHeaders)
                        .body("Sorry, no data found 😅");
            } else {
                profileEmergencyContactGetResponse.setProfileEmergencyContact(profileEmergencyContact);
                responseEntity = ResponseEntity.ok()
                        .headers(httpHeaders)
                        .body(profileEmergencyContactGetResponse);
            }
        }
        return responseEntity;
    }

    @PostMapping(value = "/emergencyContact")
    public ResponseEntity<Object> postProfileEmergencyContact(HttpServletRequest httpServletRequest, @RequestBody ProfileEmergencyContactPostRequest profileEmergencyContactPostRequest) {
        ResponseEntity<Object> responseEntity;
        HttpHeaders httpHeaders = new HttpHeaders();

        ProfileEmergencyContactPostResponse profileEmergencyContactPostResponse = new ProfileEmergencyContactPostResponse();
        String id = JwtUtil.getSubject(httpServletRequest, JWT_TOKEN_COOKIE_NAME, SIGNING_KEY);
        if (id == null) {
            responseEntity = ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .headers(httpHeaders)
                    .body("Sorry, you are not authorized 😅");
        } else {
            int userId = Integer.parseInt(id);
            ProfileEmergencyContact profileEmergencyContact = profileEmergencyContactPostRequest.getProfileEmergencyContact();
            if (profileEmergencyContact == null) {
                responseEntity = ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .headers(httpHeaders)
                        .body("Sorry, no data found 😅");
            } else {
                employeeProfileEmergencyContactService.setProfileEmergencyContact(userId, profileEmergencyContact);
                responseEntity = ResponseEntity.ok()
                        .headers(httpHeaders)
                        .body(profileEmergencyContactPostResponse);
            }
        }
        return responseEntity;
    }

    /**
     * Document section
     */
    @GetMapping(value = "/document")
    public ResponseEntity<Object> getProfileDocument(HttpServletRequest httpServletRequest) {
        ResponseEntity<Object> responseEntity;
        HttpHeaders httpHeaders = new HttpHeaders();

        ProfileDocumentGetResponse profileDocumentGetResponse = new ProfileDocumentGetResponse();
        String id = JwtUtil.getSubject(httpServletRequest, JWT_TOKEN_COOKIE_NAME, SIGNING_KEY);
        if (id == null) {
            responseEntity = ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .headers(httpHeaders)
                    .body("Sorry, you are not authorized 😅");
        } else {
            int userId = Integer.parseInt(id);
            responseEntity = ResponseEntity.ok()
                        .headers(httpHeaders)
                        .body(profileDocumentGetResponse);
        }
        return responseEntity;
    }

    @PostMapping(value = "/document")
    public ResponseEntity<Object> postProfileDocument(HttpServletRequest httpServletRequest, @RequestBody ProfileDocumentPostRequest profileDocumentPostRequest) {
        ResponseEntity<Object> responseEntity;
        HttpHeaders httpHeaders = new HttpHeaders();

        ProfileDocumentPostResponse profileDocumentPostResponse = new ProfileDocumentPostResponse();
        String id = JwtUtil.getSubject(httpServletRequest, JWT_TOKEN_COOKIE_NAME, SIGNING_KEY);
        if (id == null) {
            responseEntity = ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .headers(httpHeaders)
                    .body("Sorry, you are not authorized 😅");
        } else {
            int userId = Integer.parseInt(id);
            responseEntity = ResponseEntity.ok()
                        .headers(httpHeaders)
                        .body(profileDocumentPostResponse);
        }
        return responseEntity;
    }

}

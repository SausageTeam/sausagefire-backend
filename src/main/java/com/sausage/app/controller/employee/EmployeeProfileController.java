package com.sausage.app.controller.employee;

import com.sausage.app.domain.common.GenericResponse;
import com.sausage.app.domain.common.ServiceStatus;
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
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

import static com.sausage.app.constant.Constant.*;

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
    public ProfileNameGetResponse getProfileName(HttpServletRequest httpServletRequest) {
        ProfileNameGetResponse profileNameGetResponse = new ProfileNameGetResponse();
        int userId = Integer.parseInt(JwtUtil.getSubject(httpServletRequest, JWT_TOKEN_COOKIE_NAME, SIGNING_KEY));
        System.out.println("userID:" + userId);
//        int userId = 2;
        ProfileName profileName = employeeProfileNameService.getProfileName(userId);
        profileNameGetResponse.setProfileName(profileName);
        prepareResponse(profileNameGetResponse, true, "");
        return profileNameGetResponse;
    }

    @PostMapping(value = "/name")
    public ProfileNamePostResponse postProfileName(@RequestBody ProfileNamePostRequest profileNamePostRequest) {
        ProfileNamePostResponse profileNamePostResponse = new ProfileNamePostResponse();
        //        int userId = Integer.parseInt(JwtUtil.getSubject(httpServletRequest, Constant.JWT_TOKEN_COOKIE_NAME, Constant.SIGNING_KEY));
        int userId = 2;
        ProfileName profileName = profileNamePostRequest.getProfileName();
        employeeProfileNameService.setProfileName(userId, profileName);
        prepareResponse(profileNamePostResponse, true, "");
        return profileNamePostResponse;
    }

    /**
     * Address section
     */
    @GetMapping(value = "/address")
    public ProfileAddressGetResponse getProfileAddress(HttpServletRequest httpServletRequest) {
        ProfileAddressGetResponse profileAddressGetResponse = new ProfileAddressGetResponse();
        //        int userId = Integer.parseInt(JwtUtil.getSubject(httpServletRequest, Constant.JWT_TOKEN_COOKIE_NAME, Constant.SIGNING_KEY));
        int userId = 2;
        ProfileAddress profileAddress = employeeProfileAddressService.getProfileAddress(userId);
        profileAddressGetResponse.setProfileAddress(profileAddress);
        prepareResponse(profileAddressGetResponse, true, "");
        return profileAddressGetResponse;
    }

    @PostMapping(value = "/address")
    public ProfileAddressPostResponse postProfileAddress(@RequestBody ProfileAddressPostRequest profileAddressPostRequest) {
        ProfileAddressPostResponse profileAddressPostResponse = new ProfileAddressPostResponse();
        //        int userId = Integer.parseInt(JwtUtil.getSubject(httpServletRequest, Constant.JWT_TOKEN_COOKIE_NAME, Constant.SIGNING_KEY));
        int userId = 2;
        ProfileAddress profileAddress = profileAddressPostRequest.getProfileAddress();
        employeeProfileAddressService.setProfileAddress(userId, profileAddress);
        prepareResponse(profileAddressPostResponse, true, "");
        return profileAddressPostResponse;
    }

    /**
     * Contact section
     */
    @GetMapping(value = "/contact")
    public ProfileContactGetResponse getProfileContact(HttpServletRequest httpServletRequest) {
        ProfileContactGetResponse profileContactGetResponse = new ProfileContactGetResponse();
        //        int userId = Integer.parseInt(JwtUtil.getSubject(httpServletRequest, Constant.JWT_TOKEN_COOKIE_NAME, Constant.SIGNING_KEY));
        int userId = 2;
        ProfileContact profileContact = employeeProfileContactService.getProfileContact(userId);
        profileContactGetResponse.setProfileContact(profileContact);
        prepareResponse(profileContactGetResponse, true, "");
        return profileContactGetResponse;
    }

    @PostMapping(value = "/contact")
    public ProfileContactPostResponse postProfileContact(@RequestBody ProfileContactPostRequest profileContactPostRequest) {
        ProfileContactPostResponse profileContactPostResponse = new ProfileContactPostResponse();
        //        int userId = Integer.parseInt(JwtUtil.getSubject(httpServletRequest, Constant.JWT_TOKEN_COOKIE_NAME, Constant.SIGNING_KEY));
        int userId = 2;
        ProfileContact profileContact = profileContactPostRequest.getProfileContact();
        employeeProfileContactService.setProfileContact(userId, profileContact);
        prepareResponse(profileContactPostResponse, true, "");
        return profileContactPostResponse;
    }

    /**
     * Employment section
     */
    @GetMapping(value = "/employment")
    public ProfileEmploymentGetResponse getProfileEmployment(HttpServletRequest httpServletRequest) {
        ProfileEmploymentGetResponse profileEmploymentGetResponse = new ProfileEmploymentGetResponse();
        //        int userId = Integer.parseInt(JwtUtil.getSubject(httpServletRequest, Constant.JWT_TOKEN_COOKIE_NAME, Constant.SIGNING_KEY));
        int userId = 2;
        ProfileEmployment profileEmployment = employeeProfileEmploymentService.getProfileEmployment(userId);
        profileEmploymentGetResponse.setProfileEmployment(profileEmployment);
        prepareResponse(profileEmploymentGetResponse, true, "");
        return profileEmploymentGetResponse;
    }

    @PostMapping(value = "/employment")
    public ProfileEmploymentPostResponse postProfileEmployment(@RequestBody ProfileEmploymentPostRequest profileEmploymentPostRequest) {
        ProfileEmploymentPostResponse profileEmploymentPostResponse = new ProfileEmploymentPostResponse();
        //        int userId = Integer.parseInt(JwtUtil.getSubject(httpServletRequest, Constant.JWT_TOKEN_COOKIE_NAME, Constant.SIGNING_KEY));
        int userId = 2;
        ProfileEmployment profileEmployment = profileEmploymentPostRequest.getProfileEmployment();
        employeeProfileEmploymentService.setProfileEmployment(userId, profileEmployment);
        prepareResponse(profileEmploymentPostResponse, true, "");
        return profileEmploymentPostResponse;
    }

    /**
     * Emergency Contact section
     */
    @GetMapping(value = "/emergencyContact")
    public ProfileEmergencyContactGetResponse getProfileEmergencyContact(HttpServletRequest httpServletRequest) {
        ProfileEmergencyContactGetResponse profileEmergencyContactGetResponse = new ProfileEmergencyContactGetResponse();
        //        int userId = Integer.parseInt(JwtUtil.getSubject(httpServletRequest, Constant.JWT_TOKEN_COOKIE_NAME, Constant.SIGNING_KEY));
        int userId = 2;
        ProfileEmergencyContact profileEmergencyContact = employeeProfileEmergencyContactService.getProfileEmergencyContact(userId);
        profileEmergencyContactGetResponse.setProfileEmergencyContact(profileEmergencyContact);
        prepareResponse(profileEmergencyContactGetResponse, true, "");
        return profileEmergencyContactGetResponse;
    }

    @PostMapping(value = "/emergencyContact")
    public ProfileEmergencyContactPostResponse postProfileEmergencyContact(@RequestBody ProfileEmergencyContactPostRequest profileEmergencyContactPostRequest) {
        ProfileEmergencyContactPostResponse profileEmergencyContactPostResponse = new ProfileEmergencyContactPostResponse();
        //        int userId = Integer.parseInt(JwtUtil.getSubject(httpServletRequest, Constant.JWT_TOKEN_COOKIE_NAME, Constant.SIGNING_KEY));
        int userId = 2;
        ProfileEmergencyContact profileEmergencyContact = profileEmergencyContactPostRequest.getProfileEmergencyContact();
        employeeProfileEmergencyContactService.setProfileEmergencyContact(userId, profileEmergencyContact);
        prepareResponse(profileEmergencyContactPostResponse, true, "");
        return profileEmergencyContactPostResponse;
    }

    /**
     * Document section
     */
    @GetMapping(value = "/document")
    public ProfileDocumentGetResponse getProfileDocument(HttpServletRequest httpServletRequest) {
        ProfileDocumentGetResponse profileDocumentGetResponse = new ProfileDocumentGetResponse();
        //        int userId = Integer.parseInt(JwtUtil.getSubject(httpServletRequest, Constant.JWT_TOKEN_COOKIE_NAME, Constant.SIGNING_KEY));
        int userId = 2;

        prepareResponse(profileDocumentGetResponse, true, "");
        return profileDocumentGetResponse;
    }

    @PostMapping(value = "/document")
    public ProfileDocumentPostResponse postProfileDocument(@RequestBody ProfileDocumentPostRequest profileDocumentPostRequest) {
        ProfileDocumentPostResponse profileDocumentPostResponse = new ProfileDocumentPostResponse();
        //        int userId = Integer.parseInt(JwtUtil.getSubject(httpServletRequest, Constant.JWT_TOKEN_COOKIE_NAME, Constant.SIGNING_KEY));
        int userId = 2;

        prepareResponse(profileDocumentPostResponse, true, "");
        return profileDocumentPostResponse;
    }

    private void prepareResponse(GenericResponse response, boolean success, String errorMessage) {
        response.setServiceStatus(new ServiceStatus(success ? "SUCCESS" : "FAILED", success, errorMessage));
    }
}

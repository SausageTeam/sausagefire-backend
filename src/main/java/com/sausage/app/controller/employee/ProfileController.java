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
import com.sausage.app.service.employee.profile.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/employee/profile")
public class ProfileController {

    private ProfileNameService profileNameService;

    private ProfileAddressService profileAddressService;

    private ProfileContactService profileContactService;

    private ProfileEmploymentService profileEmploymentService;

    private ProfileEmergencyContactService profileEmergencyContactService;

    private ProfileDocumentService profileDocumentService;

    @Autowired
    public void setProfileNameService(ProfileNameService profileNameService) {
        this.profileNameService = profileNameService;
    }

    @Autowired
    public void setProfileAddressService(ProfileAddressService profileAddressService) {
        this.profileAddressService = profileAddressService;
    }

    @Autowired
    public void setProfileContactService(ProfileContactService profileContactService) {
        this.profileContactService = profileContactService;
    }

    @Autowired
    public void setProfileEmploymentService(ProfileEmploymentService profileEmploymentService) {
        this.profileEmploymentService = profileEmploymentService;
    }

    @Autowired
    public void setProfileEmergencyContactService(ProfileEmergencyContactService profileEmergencyContactService) {
        this.profileEmergencyContactService = profileEmergencyContactService;
    }

    @Autowired
    public void setProfileDocumentService(ProfileDocumentService profileDocumentService) {
        this.profileDocumentService = profileDocumentService;
    }

    /**
     * Name section
     */
    @GetMapping(value = "/name")
    public ProfileNameGetResponse getProfileName(HttpServletRequest httpServletRequest){
        ProfileNameGetResponse profileNameGetResponse = new ProfileNameGetResponse();
        //        int userId = Integer.parseInt(JwtUtil.getSubject(httpServletRequest, Constant.JWT_TOKEN_COOKIE_NAME, Constant.SIGNING_KEY));
        int userId = 9;
        ProfileName profileName = profileNameService.getProfileName(userId);
        profileNameGetResponse.setProfileName(profileName);
        prepareResponse(profileNameGetResponse, true, "");
        return profileNameGetResponse;
    }

    @PostMapping(value = "/name")
    public ProfileNamePostResponse postProfileName(@RequestBody ProfileNamePostRequest profileNamePostRequest){
        ProfileNamePostResponse profileNamePostResponse = new ProfileNamePostResponse();
        //        int userId = Integer.parseInt(JwtUtil.getSubject(httpServletRequest, Constant.JWT_TOKEN_COOKIE_NAME, Constant.SIGNING_KEY));
        int userId = 9;
        ProfileName profileName = profileNamePostRequest.getProfileName();
        profileNameService.setProfileName(userId, profileName);
        prepareResponse(profileNamePostResponse, true, "");
        return profileNamePostResponse;
    }

    /**
     * Address section
     */
    @GetMapping(value = "/address")
    public ProfileAddressGetResponse getProfileAddress(HttpServletRequest httpServletRequest){
        ProfileAddressGetResponse profileAddressGetResponse = new ProfileAddressGetResponse();
        //        int userId = Integer.parseInt(JwtUtil.getSubject(httpServletRequest, Constant.JWT_TOKEN_COOKIE_NAME, Constant.SIGNING_KEY));
        int userId = 9;
        ProfileAddress profileAddress = profileAddressService.getProfileAddress(userId);
        profileAddressGetResponse.setProfileAddress(profileAddress);
        prepareResponse(profileAddressGetResponse, true, "");
        return profileAddressGetResponse;
    }

    @PostMapping(value = "/address")
    public ProfileAddressPostResponse postProfileAddress(@RequestBody ProfileAddressPostRequest profileAddressPostRequest){
        ProfileAddressPostResponse profileAddressPostResponse = new ProfileAddressPostResponse();
        //        int userId = Integer.parseInt(JwtUtil.getSubject(httpServletRequest, Constant.JWT_TOKEN_COOKIE_NAME, Constant.SIGNING_KEY));
        int userId = 9;
        ProfileAddress profileAddress = profileAddressPostRequest.getProfileAddress();
        profileAddressService.setProfileAddress(userId, profileAddress);
        prepareResponse(profileAddressPostResponse, true, "");
        return profileAddressPostResponse;
    }

    /**
     * Contact section
     */
    @GetMapping(value = "/contact")
    public ProfileContactGetResponse getProfileContact(HttpServletRequest httpServletRequest){
        ProfileContactGetResponse profileContactGetResponse = new ProfileContactGetResponse();
        //        int userId = Integer.parseInt(JwtUtil.getSubject(httpServletRequest, Constant.JWT_TOKEN_COOKIE_NAME, Constant.SIGNING_KEY));
        int userId = 9;
        ProfileContact profileContact = profileContactService.getProfileContact(userId);
        profileContactGetResponse.setProfileContact(profileContact);
        prepareResponse(profileContactGetResponse, true, "");
        return profileContactGetResponse;
    }

    @PostMapping(value = "/contact")
    public ProfileContactPostResponse postProfileContact(@RequestBody ProfileContactPostRequest profileContactPostRequest){
        ProfileContactPostResponse profileContactPostResponse = new ProfileContactPostResponse();
        //        int userId = Integer.parseInt(JwtUtil.getSubject(httpServletRequest, Constant.JWT_TOKEN_COOKIE_NAME, Constant.SIGNING_KEY));
        int userId = 9;
        ProfileContact profileContact = profileContactPostRequest.getProfileContact();
        profileContactService.setProfileContact(userId, profileContact);
        prepareResponse(profileContactPostResponse, true, "");
        return profileContactPostResponse;
    }

    /**
     * Employment section
     */
    @GetMapping(value = "/employment")
    public ProfileEmploymentGetResponse getProfileEmployment(HttpServletRequest httpServletRequest){
        ProfileEmploymentGetResponse profileEmploymentGetResponse = new ProfileEmploymentGetResponse();
        //        int userId = Integer.parseInt(JwtUtil.getSubject(httpServletRequest, Constant.JWT_TOKEN_COOKIE_NAME, Constant.SIGNING_KEY));
        int userId = 9;
        ProfileEmployment profileEmployment = profileEmploymentService.getProfileEmployment(userId);
        profileEmploymentGetResponse.setProfileEmployment(profileEmployment);
        prepareResponse(profileEmploymentGetResponse, true, "");
        return profileEmploymentGetResponse;
    }

    @PostMapping(value = "/employment")
    public ProfileEmploymentPostResponse postProfileEmployment(@RequestBody ProfileEmploymentPostRequest profileEmploymentPostRequest){
        ProfileEmploymentPostResponse profileEmploymentPostResponse = new ProfileEmploymentPostResponse();
        //        int userId = Integer.parseInt(JwtUtil.getSubject(httpServletRequest, Constant.JWT_TOKEN_COOKIE_NAME, Constant.SIGNING_KEY));
        int userId = 9;
        ProfileEmployment profileEmployment = profileEmploymentPostRequest.getProfileEmployment();
        profileEmploymentService.setProfileEmployment(userId, profileEmployment);
        prepareResponse(profileEmploymentPostResponse, true, "");
        return profileEmploymentPostResponse;
    }

    /**
     * Emergency Contact section
     */
    @GetMapping(value = "/emergencyContact")
    public ProfileEmergencyContactGetResponse getProfileEmergencyContact(HttpServletRequest httpServletRequest){
        ProfileEmergencyContactGetResponse profileEmergencyContactGetResponse = new ProfileEmergencyContactGetResponse();
        //        int userId = Integer.parseInt(JwtUtil.getSubject(httpServletRequest, Constant.JWT_TOKEN_COOKIE_NAME, Constant.SIGNING_KEY));
        int userId = 9;
        ProfileEmergencyContact profileEmergencyContact = profileEmergencyContactService.getProfileEmergencyContact(userId);
        profileEmergencyContactGetResponse.setProfileEmergencyContact(profileEmergencyContact);
        prepareResponse(profileEmergencyContactGetResponse, true, "");
        return profileEmergencyContactGetResponse;
    }

    @PostMapping(value = "/emergencyContact")
    public ProfileEmergencyContactPostResponse postProfileEmergencyContact(@RequestBody ProfileEmergencyContactPostRequest profileEmergencyContactPostRequest){
        ProfileEmergencyContactPostResponse profileEmergencyContactPostResponse = new ProfileEmergencyContactPostResponse();
        //        int userId = Integer.parseInt(JwtUtil.getSubject(httpServletRequest, Constant.JWT_TOKEN_COOKIE_NAME, Constant.SIGNING_KEY));
        int userId = 9;
        ProfileEmergencyContact profileEmergencyContact = profileEmergencyContactPostRequest.getProfileEmergencyContact();
        profileEmergencyContactService.setProfileEmergencyContact(userId, profileEmergencyContact);
        prepareResponse(profileEmergencyContactPostResponse, true, "");
        return profileEmergencyContactPostResponse;
    }

    /**
     * Document section
     */
    @GetMapping(value = "/document")
    public ProfileDocumentGetResponse getProfileDocument(HttpServletRequest httpServletRequest){
        ProfileDocumentGetResponse profileDocumentGetResponse = new ProfileDocumentGetResponse();
        //        int userId = Integer.parseInt(JwtUtil.getSubject(httpServletRequest, Constant.JWT_TOKEN_COOKIE_NAME, Constant.SIGNING_KEY));
        int userId = 9;

        prepareResponse(profileDocumentGetResponse, true, "");
        return profileDocumentGetResponse;
    }

    @PostMapping(value = "/document")
    public ProfileDocumentPostResponse postProfileDocument(@RequestBody ProfileDocumentPostRequest profileDocumentPostRequest){
        ProfileDocumentPostResponse profileDocumentPostResponse = new ProfileDocumentPostResponse();
        //        int userId = Integer.parseInt(JwtUtil.getSubject(httpServletRequest, Constant.JWT_TOKEN_COOKIE_NAME, Constant.SIGNING_KEY));
        int userId = 9;

        prepareResponse(profileDocumentPostResponse, true, "");
        return profileDocumentPostResponse;
    }

    private void prepareResponse(GenericResponse response, boolean success, String errorMessage) {
        response.setServiceStatus(new ServiceStatus(success ? "SUCCESS" : "FAILED", success, errorMessage));
    }
}

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
        String id = JwtUtil.getSubject(httpServletRequest, JWT_TOKEN_COOKIE_NAME, SIGNING_KEY);
        if (id == null) {
            prepareResponse(profileNameGetResponse, "401", false, "User not found");
        } else {
            int userId = Integer.parseInt(id);
            ProfileName profileName = employeeProfileNameService.getProfileName(userId);
            if (profileName == null) {
                prepareResponse(profileNameGetResponse, "500", false, "Unexpected Error");
            } else {
                profileNameGetResponse.setProfileName(profileName);
                prepareResponse(profileNameGetResponse, "200", true, "");
            }
        }
        return profileNameGetResponse;
    }

    @PostMapping(value = "/name")
    public ProfileNamePostResponse postProfileName(HttpServletRequest httpServletRequest, @RequestBody ProfileNamePostRequest profileNamePostRequest) {
        ProfileNamePostResponse profileNamePostResponse = new ProfileNamePostResponse();
        String id = JwtUtil.getSubject(httpServletRequest, JWT_TOKEN_COOKIE_NAME, SIGNING_KEY);
        if (id == null) {
            prepareResponse(profileNamePostResponse, "401", false, "User not found");
        } else {
            int userId = Integer.parseInt(id);
            ProfileName profileName = profileNamePostRequest.getProfileName();
            if (profileName == null) {
                prepareResponse(profileNamePostResponse, "500", false, "Unexpected Error");
            } else {
                employeeProfileNameService.setProfileName(userId, profileName);
                prepareResponse(profileNamePostResponse, "200", true, "");
            }
        }
        return profileNamePostResponse;
    }

    /**
     * Address section
     */
    @GetMapping(value = "/address")
    public ProfileAddressGetResponse getProfileAddress(HttpServletRequest httpServletRequest) {
        ProfileAddressGetResponse profileAddressGetResponse = new ProfileAddressGetResponse();
        String id = JwtUtil.getSubject(httpServletRequest, JWT_TOKEN_COOKIE_NAME, SIGNING_KEY);
        if (id == null) {
            prepareResponse(profileAddressGetResponse, "401", false, "User not found");
        } else {
            int userId = Integer.parseInt(id);
            ProfileAddress profileAddress = employeeProfileAddressService.getProfileAddress(userId);
            if (profileAddress == null) {
                prepareResponse(profileAddressGetResponse, "500", false, "Unexpected Error");
            } else {
                profileAddressGetResponse.setProfileAddress(profileAddress);
                prepareResponse(profileAddressGetResponse, "200", true, "");
            }
        }
        return profileAddressGetResponse;
    }

    @PostMapping(value = "/address")
    public ProfileAddressPostResponse postProfileAddress(HttpServletRequest httpServletRequest, @RequestBody ProfileAddressPostRequest profileAddressPostRequest) {
        ProfileAddressPostResponse profileAddressPostResponse = new ProfileAddressPostResponse();
        String id = JwtUtil.getSubject(httpServletRequest, JWT_TOKEN_COOKIE_NAME, SIGNING_KEY);
        if (id == null) {
            prepareResponse(profileAddressPostResponse, "401", false, "User not found");
        } else {
            int userId = Integer.parseInt(id);
            ProfileAddress profileAddress = profileAddressPostRequest.getProfileAddress();
            if (profileAddress == null) {
                prepareResponse(profileAddressPostResponse, "500", true, "Unexpected Error");
            } else {
                employeeProfileAddressService.setProfileAddress(userId, profileAddress);
                prepareResponse(profileAddressPostResponse, "200", true, "");
            }
        }
        return profileAddressPostResponse;
    }

    /**
     * Contact section
     */
    @GetMapping(value = "/contact")
    public ProfileContactGetResponse getProfileContact(HttpServletRequest httpServletRequest) {
        ProfileContactGetResponse profileContactGetResponse = new ProfileContactGetResponse();
        String id = JwtUtil.getSubject(httpServletRequest, JWT_TOKEN_COOKIE_NAME, SIGNING_KEY);
        if (id == null) {
            prepareResponse(profileContactGetResponse, "401", false, "User not found");
        } else {
            int userId = Integer.parseInt(id);
            ProfileContact profileContact = employeeProfileContactService.getProfileContact(userId);
            if (profileContact == null) {
                prepareResponse(profileContactGetResponse, "500", false, "Unexpected Error");
            } else {
                profileContactGetResponse.setProfileContact(profileContact);
                prepareResponse(profileContactGetResponse, "200", true, "");
            }
        }
        return profileContactGetResponse;
    }

    @PostMapping(value = "/contact")
    public ProfileContactPostResponse postProfileContact(HttpServletRequest httpServletRequest, @RequestBody ProfileContactPostRequest profileContactPostRequest) {
        ProfileContactPostResponse profileContactPostResponse = new ProfileContactPostResponse();
        String id = JwtUtil.getSubject(httpServletRequest, JWT_TOKEN_COOKIE_NAME, SIGNING_KEY);
        if (id == null) {
            prepareResponse(profileContactPostResponse, "401", false, "User not found");
        } else {
            int userId = Integer.parseInt(id);
            ProfileContact profileContact = profileContactPostRequest.getProfileContact();
            if (profileContact == null) {
                prepareResponse(profileContactPostResponse, "500", false, "Unexpected Error");
            } else {
                employeeProfileContactService.setProfileContact(userId, profileContact);
                prepareResponse(profileContactPostResponse, "200", true, "");
            }
        }
        return profileContactPostResponse;
    }

    /**
     * Employment section
     */
    @GetMapping(value = "/employment")
    public ProfileEmploymentGetResponse getProfileEmployment(HttpServletRequest httpServletRequest) {
        ProfileEmploymentGetResponse profileEmploymentGetResponse = new ProfileEmploymentGetResponse();
        String id = JwtUtil.getSubject(httpServletRequest, JWT_TOKEN_COOKIE_NAME, SIGNING_KEY);
        if (id == null) {
            prepareResponse(profileEmploymentGetResponse, "401", false, "User not found");
        } else {
            int userId = Integer.parseInt(id);
            ProfileEmployment profileEmployment = employeeProfileEmploymentService.getProfileEmployment(userId);
            if (profileEmployment == null) {
                prepareResponse(profileEmploymentGetResponse, "500", false, "Unexpected Error");
            } else {
                profileEmploymentGetResponse.setProfileEmployment(profileEmployment);
                prepareResponse(profileEmploymentGetResponse, "200", true, "");
            }
        }
        return profileEmploymentGetResponse;
    }

    @PostMapping(value = "/employment")
    public ProfileEmploymentPostResponse postProfileEmployment(HttpServletRequest httpServletRequest, @RequestBody ProfileEmploymentPostRequest profileEmploymentPostRequest) {
        ProfileEmploymentPostResponse profileEmploymentPostResponse = new ProfileEmploymentPostResponse();
        String id = JwtUtil.getSubject(httpServletRequest, JWT_TOKEN_COOKIE_NAME, SIGNING_KEY);
        if (id == null) {
            prepareResponse(profileEmploymentPostResponse, "401", false, "User not found");
        } else {
            int userId = Integer.parseInt(id);
            ProfileEmployment profileEmployment = profileEmploymentPostRequest.getProfileEmployment();
            if (profileEmployment == null) {
                prepareResponse(profileEmploymentPostResponse, "500", false, "Unexpected Error");
            }
            employeeProfileEmploymentService.setProfileEmployment(userId, profileEmployment);
            prepareResponse(profileEmploymentPostResponse, "200", true, "");
        }
        return profileEmploymentPostResponse;
    }

    /**
     * Emergency Contact section
     */
    @GetMapping(value = "/emergencyContact")
    public ProfileEmergencyContactGetResponse getProfileEmergencyContact(HttpServletRequest httpServletRequest) {
        ProfileEmergencyContactGetResponse profileEmergencyContactGetResponse = new ProfileEmergencyContactGetResponse();
        String id = JwtUtil.getSubject(httpServletRequest, JWT_TOKEN_COOKIE_NAME, SIGNING_KEY);
        if (id == null) {
            prepareResponse(profileEmergencyContactGetResponse, "401", false, "User not found");
        } else {
            int userId = Integer.parseInt(id);
            ProfileEmergencyContact profileEmergencyContact = employeeProfileEmergencyContactService.getProfileEmergencyContact(userId);
            if (profileEmergencyContact == null) {
                prepareResponse(profileEmergencyContactGetResponse, "500", false, "Unexpected Error");
            } else {
                profileEmergencyContactGetResponse.setProfileEmergencyContact(profileEmergencyContact);
                prepareResponse(profileEmergencyContactGetResponse, "200", true, "");
            }
        }
        return profileEmergencyContactGetResponse;
    }

    @PostMapping(value = "/emergencyContact")
    public ProfileEmergencyContactPostResponse postProfileEmergencyContact(HttpServletRequest httpServletRequest, @RequestBody ProfileEmergencyContactPostRequest profileEmergencyContactPostRequest) {
        ProfileEmergencyContactPostResponse profileEmergencyContactPostResponse = new ProfileEmergencyContactPostResponse();
        String id = JwtUtil.getSubject(httpServletRequest, JWT_TOKEN_COOKIE_NAME, SIGNING_KEY);
        if (id == null) {
            prepareResponse(profileEmergencyContactPostResponse, "401", false, "User not found");
        } else {
            int userId = Integer.parseInt(id);
            ProfileEmergencyContact profileEmergencyContact = profileEmergencyContactPostRequest.getProfileEmergencyContact();
            if (profileEmergencyContact == null) {
                prepareResponse(profileEmergencyContactPostResponse, "500", false, "Unexpected Error");
            } else {
                employeeProfileEmergencyContactService.setProfileEmergencyContact(userId, profileEmergencyContact);
                prepareResponse(profileEmergencyContactPostResponse, "200", true, "");
            }
        }
        return profileEmergencyContactPostResponse;
    }

    /**
     * Document section
     */
    @GetMapping(value = "/document")
    public ProfileDocumentGetResponse getProfileDocument(HttpServletRequest httpServletRequest) {
        ProfileDocumentGetResponse profileDocumentGetResponse = new ProfileDocumentGetResponse();
        String id = JwtUtil.getSubject(httpServletRequest, JWT_TOKEN_COOKIE_NAME, SIGNING_KEY);
        if (id == null) {
            prepareResponse(profileDocumentGetResponse, "401", false, "User not found");
        } else {
            int userId = Integer.parseInt(id);
            prepareResponse(profileDocumentGetResponse, "200",true, "");
        }
        return profileDocumentGetResponse;
    }

    @PostMapping(value = "/document")
    public ProfileDocumentPostResponse postProfileDocument(HttpServletRequest httpServletRequest, @RequestBody ProfileDocumentPostRequest profileDocumentPostRequest) {
        ProfileDocumentPostResponse profileDocumentPostResponse = new ProfileDocumentPostResponse();
        String id = JwtUtil.getSubject(httpServletRequest, JWT_TOKEN_COOKIE_NAME, SIGNING_KEY);
        if (id == null) {
            prepareResponse(profileDocumentPostResponse, "401", false, "User not found");
        } else {
            int userId = Integer.parseInt(id);
            prepareResponse(profileDocumentPostResponse, "200",true, "");
        }
        return profileDocumentPostResponse;
    }

    private void prepareResponse(GenericResponse response, String statusCode, boolean success, String errorMessage) {
        response.setServiceStatus(new ServiceStatus(statusCode, success, errorMessage));
    }

}

package com.sausage.app.controller.hr;

import com.sausage.app.domain.common.GenericResponse;
import com.sausage.app.domain.common.ServiceStatus;
import com.sausage.app.domain.hr.employeeProfile.*;
import com.sausage.app.security.util.JwtUtil;
import com.sausage.app.service.hr.employeeProfile.HREmployeeProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

import static com.sausage.app.constant.Constant.JWT_TOKEN_COOKIE_NAME;
import static com.sausage.app.constant.Constant.SIGNING_KEY;

@RestController
@RequestMapping("/hr/employee-profile")
public class HREmployeeProfileController {

    private HREmployeeProfileService HREmployeeProfileService;

    @Autowired
    public void setHREmployeeProfileService(HREmployeeProfileService HREmployeeProfileService) {
        this.HREmployeeProfileService = HREmployeeProfileService;
    }

    @GetMapping
    public @ResponseBody
    EmployeeProfileGetResponse getEmployeeProfile(HttpServletRequest httpServletRequest) {
        EmployeeProfileGetResponse employeeProfileGetResponse = new EmployeeProfileGetResponse();
        String id = JwtUtil.getSubject(httpServletRequest, JWT_TOKEN_COOKIE_NAME, SIGNING_KEY);
        if (id == null) {
            prepareResponse(employeeProfileGetResponse, "401", false, "User not Found");
        } else {
            EmployeeProfile employeeProfile = HREmployeeProfileService.getEmployeeProfile();
            if (employeeProfile == null) {
                prepareResponse(employeeProfileGetResponse, "500", false, "Unexpected Error");
            } else {
                employeeProfileGetResponse.setEmployeeProfile(employeeProfile);
                prepareResponse(employeeProfileGetResponse, "200", true, "");
            }
        }
        return employeeProfileGetResponse;
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody
    EmployeeProfilePostResponse postEmployeeProfile(HttpServletRequest httpServletRequest, @RequestBody EmployeeProfilePostRequest employeeProfilePostRequest) {
        EmployeeProfilePostResponse employeeProfilePostResponse = new EmployeeProfilePostResponse();
        String id = JwtUtil.getSubject(httpServletRequest, JWT_TOKEN_COOKIE_NAME, SIGNING_KEY);
        if (id == null) {
            prepareResponse(employeeProfilePostResponse, "401", false, "User not Found");
        } else {
            prepareResponse(employeeProfilePostResponse, "200", true, "");
        }
        return employeeProfilePostResponse;
    }

    private void prepareResponse(GenericResponse response, String statusCode, boolean success, String errorMessage) {
        response.setServiceStatus(new ServiceStatus(statusCode, success, errorMessage));
    }

}

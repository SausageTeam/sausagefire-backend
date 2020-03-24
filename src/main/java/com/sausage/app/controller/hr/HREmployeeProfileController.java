package com.sausage.app.controller.hr;

import com.sausage.app.domain.common.GenericResponse;
import com.sausage.app.domain.common.ServiceStatus;
import com.sausage.app.domain.hr.employeeProfile.*;
import com.sausage.app.service.hr.employeeProfile.HREmployeeProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

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
    EmployeeProfileGetResponse getEmployeeProfile(HttpServletRequest httpServletRequest){
        EmployeeProfileGetResponse employeeProfileGetResponse = new EmployeeProfileGetResponse();
        //        int userId = Integer.parseInt(JwtUtil.getSubject(httpServletRequest, Constant.JWT_TOKEN_COOKIE_NAME, Constant.SIGNING_KEY));
        int userId = 9;
        EmployeeProfile employeeProfile = HREmployeeProfileService.getEmployeeProfile();
        employeeProfileGetResponse.setEmployeeProfile(employeeProfile);
        prepareResponse(employeeProfileGetResponse, true, "");
        return employeeProfileGetResponse;
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody
    EmployeeProfilePostResponse postEmployeeProfile(@RequestBody EmployeeProfilePostRequest employeeProfilePostRequest){
        EmployeeProfilePostResponse employeeProfilePostResponse = new EmployeeProfilePostResponse();
        //        int userId = Integer.parseInt(JwtUtil.getSubject(httpServletRequest, Constant.JWT_TOKEN_COOKIE_NAME, Constant.SIGNING_KEY));
        int userId = 9;

        prepareResponse(employeeProfilePostResponse, true, "");
        return employeeProfilePostResponse;
    }

    private void prepareResponse(GenericResponse response, boolean success, String errorMessage) {
        response.setServiceStatus(new ServiceStatus(success ? "SUCCESS" : "FAILED", success, errorMessage));
    }
}

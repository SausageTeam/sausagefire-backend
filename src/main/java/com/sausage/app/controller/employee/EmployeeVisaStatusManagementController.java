package com.sausage.app.controller.employee;

import com.sausage.app.domain.common.GenericResponse;
import com.sausage.app.domain.common.ServiceStatus;
import com.sausage.app.domain.employee.visaStatusManagement.VisaStatusManagement;
import com.sausage.app.domain.employee.visaStatusManagement.VisaStatusManagementGetResponse;
import com.sausage.app.domain.employee.visaStatusManagement.VisaStatusManagementPostRequest;
import com.sausage.app.domain.employee.visaStatusManagement.VisaStatusManagementPostResponse;
import com.sausage.app.service.employee.visaStatusManagement.EmployeeVisaStatusManagementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.io.File;

@RestController
@RequestMapping("/employee/visa-status-management")
public class EmployeeVisaStatusManagementController {

    private EmployeeVisaStatusManagementService employeeVisaStatusManagementService;

    @Autowired
    public void setEmployeeVisaStatusManagementService(EmployeeVisaStatusManagementService employeeVisaStatusManagementService) {
        this.employeeVisaStatusManagementService = employeeVisaStatusManagementService;
    }

    @GetMapping
    public @ResponseBody
    VisaStatusManagementGetResponse getVisaStatusManagement(HttpServletRequest httpServletRequest) {
        VisaStatusManagementGetResponse visaStatusManagementGetResponse = new VisaStatusManagementGetResponse();
        //        int userId = Integer.parseInt(JwtUtil.getSubject(httpServletRequest, Constant.JWT_TOKEN_COOKIE_NAME, Constant.SIGNING_KEY));
        int userId = 2;
        VisaStatusManagement visaStatusManagement = employeeVisaStatusManagementService.getVisaStatusManagement(userId);
        visaStatusManagementGetResponse.setVisaStatusManagement(visaStatusManagement);
        prepareResponse(visaStatusManagementGetResponse, true, "");
        return visaStatusManagementGetResponse;
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody
    VisaStatusManagementPostResponse postVisaStatusManagement(@RequestBody VisaStatusManagementPostRequest visaStatusManagementPostRequest) {
        VisaStatusManagementPostResponse visaStatusManagementPostResponse = new VisaStatusManagementPostResponse();
        //        int userId = Integer.parseInt(JwtUtil.getSubject(httpServletRequest, Constant.JWT_TOKEN_COOKIE_NAME, Constant.SIGNING_KEY));
        int userId = 2;
        File file = visaStatusManagementPostRequest.getFile();
        employeeVisaStatusManagementService.setVisaStatusManagement(userId, file);
        prepareResponse(visaStatusManagementPostResponse, true, "");
        return visaStatusManagementPostResponse;
    }

    private void prepareResponse(GenericResponse response, boolean success, String errorMessage) {
        response.setServiceStatus(new ServiceStatus(success ? "SUCCESS" : "FAILED", success, errorMessage));
    }
}
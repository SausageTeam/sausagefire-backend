package com.sausage.app.controller.employee;

import com.sausage.app.domain.common.GenericResponse;
import com.sausage.app.domain.common.ServiceStatus;
import com.sausage.app.domain.employee.visaStatusManagement.VisaStatusManagement;
import com.sausage.app.domain.employee.visaStatusManagement.VisaStatusManagementGetResponse;
import com.sausage.app.domain.employee.visaStatusManagement.VisaStatusManagementPostRequest;
import com.sausage.app.domain.employee.visaStatusManagement.VisaStatusManagementPostResponse;
import com.sausage.app.security.util.JwtUtil;
import com.sausage.app.service.employee.visaStatusManagement.EmployeeVisaStatusManagementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.io.File;

import static com.sausage.app.constant.Constant.JWT_TOKEN_COOKIE_NAME;
import static com.sausage.app.constant.Constant.SIGNING_KEY;

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
        String id = JwtUtil.getSubject(httpServletRequest, JWT_TOKEN_COOKIE_NAME, SIGNING_KEY);
        if (id == null) {
            prepareResponse(visaStatusManagementGetResponse, "401", false, "User not Found");
        } else {
            int userId = Integer.parseInt(id);
            VisaStatusManagement visaStatusManagement = employeeVisaStatusManagementService.getVisaStatusManagement(userId);
            if (visaStatusManagement == null) {
                prepareResponse(visaStatusManagementGetResponse, "500", false, "Unexpected Error");
            } else {
                visaStatusManagementGetResponse.setVisaStatusManagement(visaStatusManagement);
                prepareResponse(visaStatusManagementGetResponse, "200", true, "");
            }
        }
        return visaStatusManagementGetResponse;
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody
    VisaStatusManagementPostResponse postVisaStatusManagement(HttpServletRequest httpServletRequest, @RequestBody VisaStatusManagementPostRequest visaStatusManagementPostRequest) {
        VisaStatusManagementPostResponse visaStatusManagementPostResponse = new VisaStatusManagementPostResponse();
        String id = JwtUtil.getSubject(httpServletRequest, JWT_TOKEN_COOKIE_NAME, SIGNING_KEY);
        if (id == null) {
            prepareResponse(visaStatusManagementPostResponse, "401", false, "User not Found");
        } else {
            int userId = Integer.parseInt(id);
            File file = visaStatusManagementPostRequest.getFile();
            if (file == null){
                prepareResponse(visaStatusManagementPostResponse, "500", false, "Expected Error");
            }
            employeeVisaStatusManagementService.setVisaStatusManagement(userId, file);
            prepareResponse(visaStatusManagementPostResponse, "200", true, "");
        }
        return visaStatusManagementPostResponse;
    }

    private void prepareResponse(GenericResponse response, String statusCode, boolean success, String errorMessage) {
        response.setServiceStatus(new ServiceStatus(statusCode, success, errorMessage));
    }

}

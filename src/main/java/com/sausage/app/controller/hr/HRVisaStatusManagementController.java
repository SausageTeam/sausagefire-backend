package com.sausage.app.controller.hr;

import com.sausage.app.domain.common.GenericResponse;
import com.sausage.app.domain.common.ServiceStatus;
import com.sausage.app.domain.hr.visaStatusManagement.VisaStatusManagement;
import com.sausage.app.domain.hr.visaStatusManagement.VisaStatusManagementGetResponse;
import com.sausage.app.domain.hr.visaStatusManagement.VisaStatusManagementPostRequest;
import com.sausage.app.domain.hr.visaStatusManagement.VisaStatusManagementPostResponse;
import com.sausage.app.security.util.JwtUtil;
import com.sausage.app.service.hr.visaStatusManagement.HRVisaStatusManagementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

import static com.sausage.app.constant.Constant.JWT_TOKEN_COOKIE_NAME;
import static com.sausage.app.constant.Constant.SIGNING_KEY;

@RestController
@RequestMapping("/hr/visa-status-management")
public class HRVisaStatusManagementController {

    HRVisaStatusManagementService HRVisaStatusManagementService;

    @Autowired
    public void setHRVisaStatusManagementService(HRVisaStatusManagementService HRVisaStatusManagementService) {
        this.HRVisaStatusManagementService = HRVisaStatusManagementService;
    }

    @GetMapping
    public @ResponseBody
    VisaStatusManagementGetResponse getVisaStatusManagement(HttpServletRequest httpServletRequest) {
        VisaStatusManagementGetResponse visaStatusManagementGetResponse = new VisaStatusManagementGetResponse();
        String id = JwtUtil.getSubject(httpServletRequest, JWT_TOKEN_COOKIE_NAME, SIGNING_KEY);
        if (id == null) {
            prepareResponse(visaStatusManagementGetResponse, "401", false, "User not Found");
        } else {
            VisaStatusManagement visaStatusManagement = HRVisaStatusManagementService.getVisaStatusManagement();
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
            int employeeId = visaStatusManagementPostRequest.getEmployeeId();
            HRVisaStatusManagementService.setVisaStatusManagement(employeeId);
            prepareResponse(visaStatusManagementPostResponse, "200", true, "");
        }
        return visaStatusManagementPostResponse;
    }

    private void prepareResponse(GenericResponse response, String statusCode, boolean success, String errorMessage) {
        response.setServiceStatus(new ServiceStatus(statusCode, success, errorMessage));
    }

}

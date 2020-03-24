package com.sausage.app.controller.hr;

import com.sausage.app.domain.common.GenericResponse;
import com.sausage.app.domain.common.ServiceStatus;
import com.sausage.app.domain.hr.visaStatusManagement.VisaStatusManagement;
import com.sausage.app.domain.hr.visaStatusManagement.VisaStatusManagementGetResponse;
import com.sausage.app.domain.hr.visaStatusManagement.VisaStatusManagementPostRequest;
import com.sausage.app.domain.hr.visaStatusManagement.VisaStatusManagementPostResponse;
import com.sausage.app.service.hr.visaStatusManagement.HRVisaStatusManagementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

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
    VisaStatusManagementGetResponse getVisaStatusManagement(HttpServletRequest httpServletRequest){
        VisaStatusManagementGetResponse visaStatusManagementGetResponse = new VisaStatusManagementGetResponse();
        //        int userId = Integer.parseInt(JwtUtil.getSubject(httpServletRequest, Constant.JWT_TOKEN_COOKIE_NAME, Constant.SIGNING_KEY));
        int userId = 9;
        VisaStatusManagement visaStatusManagement = HRVisaStatusManagementService.getVisaStatusManagement();
        visaStatusManagementGetResponse.setVisaStatusManagement(visaStatusManagement);
        prepareResponse(visaStatusManagementGetResponse, true, "");
        return visaStatusManagementGetResponse;
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody
    VisaStatusManagementPostResponse postVisaStatusManagement(@RequestBody VisaStatusManagementPostRequest visaStatusManagementPostRequest){
        VisaStatusManagementPostResponse visaStatusManagementPostResponse = new VisaStatusManagementPostResponse();
        //        int userId = Integer.parseInt(JwtUtil.getSubject(httpServletRequest, Constant.JWT_TOKEN_COOKIE_NAME, Constant.SIGNING_KEY));
        int userId = 9;
        int employeeId = visaStatusManagementPostRequest.getEmployeeId();
        HRVisaStatusManagementService.setVisaStatusManagement(employeeId);
        prepareResponse(visaStatusManagementPostResponse, true, "");
        return visaStatusManagementPostResponse;
    }

    private void prepareResponse(GenericResponse response, boolean success, String errorMessage) {
        response.setServiceStatus(new ServiceStatus(success ? "SUCCESS" : "FAILED", success, errorMessage));
    }

}

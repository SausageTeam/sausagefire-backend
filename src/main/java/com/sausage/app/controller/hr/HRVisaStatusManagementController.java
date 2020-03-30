package com.sausage.app.controller.hr;

import com.sausage.app.domain.hr.visaStatusManagement.VisaStatusManagement;
import com.sausage.app.domain.hr.visaStatusManagement.VisaStatusManagementGetResponse;
import com.sausage.app.domain.hr.visaStatusManagement.VisaStatusManagementPostRequest;
import com.sausage.app.domain.hr.visaStatusManagement.VisaStatusManagementPostResponse;
import com.sausage.app.security.util.JwtUtil;
import com.sausage.app.service.hr.visaStatusManagement.HRVisaStatusManagementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<Object> getVisaStatusManagement(HttpServletRequest httpServletRequest) {
        ResponseEntity<Object> responseEntity;

        VisaStatusManagementGetResponse visaStatusManagementGetResponse = new VisaStatusManagementGetResponse();
        String id = JwtUtil.getSubject(httpServletRequest, JWT_TOKEN_COOKIE_NAME, SIGNING_KEY);
        if (id == null) {
            responseEntity = ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body("Sorry, you are not authorized 😅");
        } else {
            VisaStatusManagement visaStatusManagement = HRVisaStatusManagementService.getVisaStatusManagement();
            if (visaStatusManagement == null) {
                responseEntity = ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body("Sorry, no data found 😅");
            } else {
                visaStatusManagementGetResponse.setVisaStatusManagement(visaStatusManagement);
                responseEntity = ResponseEntity.ok()
                        .body(visaStatusManagementGetResponse);
            }
        }
        return responseEntity;
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> postVisaStatusManagement(HttpServletRequest httpServletRequest, @RequestBody VisaStatusManagementPostRequest visaStatusManagementPostRequest) {
        ResponseEntity<Object> responseEntity;

        VisaStatusManagementPostResponse visaStatusManagementPostResponse = new VisaStatusManagementPostResponse();
        String id = JwtUtil.getSubject(httpServletRequest, JWT_TOKEN_COOKIE_NAME, SIGNING_KEY);
        if (id == null) {
            responseEntity = ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body("Sorry, you are not authorized 😅");
        } else {
            int employeeId = visaStatusManagementPostRequest.getEmployeeId();
            HRVisaStatusManagementService.setVisaStatusManagement(employeeId);
            responseEntity = ResponseEntity.ok()
                    .body(visaStatusManagementPostResponse);
        }
        return responseEntity;
    }

}

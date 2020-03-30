package com.sausage.app.controller.employee;

import com.sausage.app.domain.employee.visaStatusManagement.VisaStatusManagement;
import com.sausage.app.domain.employee.visaStatusManagement.VisaStatusManagementGetResponse;
import com.sausage.app.domain.employee.visaStatusManagement.VisaStatusManagementPostRequest;
import com.sausage.app.domain.employee.visaStatusManagement.VisaStatusManagementPostResponse;
import com.sausage.app.security.util.JwtUtil;
import com.sausage.app.service.employee.visaStatusManagement.EmployeeVisaStatusManagementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<Object> getVisaStatusManagement(HttpServletRequest httpServletRequest) {
        ResponseEntity<Object> responseEntity;

        VisaStatusManagementGetResponse visaStatusManagementGetResponse = new VisaStatusManagementGetResponse();
        String id = JwtUtil.getSubject(httpServletRequest, JWT_TOKEN_COOKIE_NAME, SIGNING_KEY);
        if (id == null) {
            responseEntity = ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body("Sorry, you are not authorized 😅");
        } else {
            int userId = Integer.parseInt(id);
            VisaStatusManagement visaStatusManagement = employeeVisaStatusManagementService.getVisaStatusManagement(userId);
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
            int userId = Integer.parseInt(id);
            File file = visaStatusManagementPostRequest.getFile();
            if (file == null) {
                responseEntity = ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body("Sorry, no data found 😅");
            } else {
                employeeVisaStatusManagementService.setVisaStatusManagement(userId, file);
                responseEntity = ResponseEntity.ok()
                        .body(visaStatusManagementPostResponse);
            }
        }
        return responseEntity;
    }

}

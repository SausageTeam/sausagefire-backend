package com.sausage.app.controller.hr;

import com.sausage.app.domain.hr.employeeProfile.EmployeeProfile;
import com.sausage.app.domain.hr.employeeProfile.EmployeeProfileGetResponse;
import com.sausage.app.domain.hr.employeeProfile.EmployeeProfilePostRequest;
import com.sausage.app.domain.hr.employeeProfile.EmployeeProfilePostResponse;
import com.sausage.app.security.util.JwtUtil;
import com.sausage.app.service.hr.employeeProfile.HREmployeeProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<Object> getEmployeeProfile(HttpServletRequest httpServletRequest) {
        ResponseEntity<Object> responseEntity;
        HttpHeaders httpHeaders = new HttpHeaders();

        EmployeeProfileGetResponse employeeProfileGetResponse = new EmployeeProfileGetResponse();
        String id = JwtUtil.getSubject(httpServletRequest, JWT_TOKEN_COOKIE_NAME, SIGNING_KEY);
        if (id == null) {
            responseEntity = ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .headers(httpHeaders)
                    .body("Sorry, you are not authorized 😅");
        } else {
            EmployeeProfile employeeProfile = HREmployeeProfileService.getEmployeeProfile();
            if (employeeProfile == null) {
                responseEntity = ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .headers(httpHeaders)
                        .body("Sorry, no data found 😅");
            } else {
                employeeProfileGetResponse.setEmployeeProfile(employeeProfile);
                responseEntity = ResponseEntity.ok()
                        .headers(httpHeaders)
                        .body(employeeProfileGetResponse);
            }
        }
        return responseEntity;
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> postEmployeeProfile(HttpServletRequest httpServletRequest, @RequestBody EmployeeProfilePostRequest employeeProfilePostRequest) {
        ResponseEntity<Object> responseEntity;
        HttpHeaders httpHeaders = new HttpHeaders();

        EmployeeProfilePostResponse employeeProfilePostResponse = new EmployeeProfilePostResponse();
        String id = JwtUtil.getSubject(httpServletRequest, JWT_TOKEN_COOKIE_NAME, SIGNING_KEY);
        if (id == null) {
            responseEntity = ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .headers(httpHeaders)
                    .body("Sorry, you are not authorized 😅");
        } else {
            responseEntity = ResponseEntity.ok()
                    .headers(httpHeaders)
                    .body(employeeProfilePostResponse);
        }
        return responseEntity;
    }

}

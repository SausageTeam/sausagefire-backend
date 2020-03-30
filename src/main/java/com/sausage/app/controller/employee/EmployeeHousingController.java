package com.sausage.app.controller.employee;

import com.sausage.app.domain.employee.housing.facilityReports.issue.FacilityReportsIssue;
import com.sausage.app.domain.employee.housing.facilityReports.issue.FacilityReportsIssueGetResponse;
import com.sausage.app.domain.employee.housing.facilityReports.issue.FacilityReportsIssuePostRequest;
import com.sausage.app.domain.employee.housing.facilityReports.issue.FacilityReportsIssuePostResponse;
import com.sausage.app.domain.employee.housing.facilityReports.record.*;
import com.sausage.app.domain.employee.housing.houseDetail.HouseDetail;
import com.sausage.app.domain.employee.housing.houseDetail.HouseDetailGetResponse;
import com.sausage.app.domain.employee.housing.houseDetail.HouseDetailPostRequest;
import com.sausage.app.domain.employee.housing.houseDetail.HouseDetailPostResponse;
import com.sausage.app.security.util.JwtUtil;
import com.sausage.app.service.employee.housing.EmployeeFacilityReportsIssueService;
import com.sausage.app.service.employee.housing.EmployeeFacilityReportsRecordService;
import com.sausage.app.service.employee.housing.EmployeeHouseDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

import static com.sausage.app.constant.Constant.JWT_TOKEN_COOKIE_NAME;
import static com.sausage.app.constant.Constant.SIGNING_KEY;

@RestController
@RequestMapping("/employee/housing")
public class EmployeeHousingController {

    private EmployeeHouseDetailService employeeHouseDetailService;

    private EmployeeFacilityReportsIssueService employeeFacilityReportsIssueService;

    private EmployeeFacilityReportsRecordService employeeFacilityReportsRecordService;

    @Autowired
    public void setEmployeeHouseDetailService(EmployeeHouseDetailService employeeHouseDetailService) {
        this.employeeHouseDetailService = employeeHouseDetailService;
    }

    @Autowired
    public void setEmployeeFacilityReportsIssueService(EmployeeFacilityReportsIssueService employeeFacilityReportsIssueService) {
        this.employeeFacilityReportsIssueService = employeeFacilityReportsIssueService;
    }

    @Autowired
    public void setEmployeeFacilityReportsRecordService(EmployeeFacilityReportsRecordService employeeFacilityReportsRecordService) {
        this.employeeFacilityReportsRecordService = employeeFacilityReportsRecordService;
    }

    @GetMapping("/house-detail")
    public ResponseEntity<Object> getHouseDetail(HttpServletRequest httpServletRequest) {
        ResponseEntity<Object> responseEntity;

        HouseDetailGetResponse houseDetailGetResponse = new HouseDetailGetResponse();
        String id = JwtUtil.getSubject(httpServletRequest, JWT_TOKEN_COOKIE_NAME, SIGNING_KEY);
        if (id == null) {
            responseEntity = ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body("Sorry, you are not authorized 😅");
        } else {
            int userId = Integer.parseInt(id);
            HouseDetail houseDetail = employeeHouseDetailService.getHouseDetail(userId);
            houseDetailGetResponse.setHouseDetail(houseDetail);
            responseEntity = ResponseEntity.ok()
                    .body(houseDetailGetResponse);
        }
        return responseEntity;
    }

    @PostMapping("/house-detail")
    public ResponseEntity<Object> postHouseDetail(HttpServletRequest httpServletRequest, @RequestBody HouseDetailPostRequest houseDetailPostRequest) {
        ResponseEntity<Object> responseEntity;
        HouseDetailPostResponse houseDetailPostResponse = new HouseDetailPostResponse();
        String id = JwtUtil.getSubject(httpServletRequest, JWT_TOKEN_COOKIE_NAME, SIGNING_KEY);
        if (id == null) {
            responseEntity = ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body("Sorry, you are not authorized 😅");
        } else {
            int userId = Integer.parseInt(id);
            responseEntity = ResponseEntity.ok()
                    .body(houseDetailPostResponse);
        }
        return responseEntity;
    }

    @GetMapping("/facility-reports/issue")
    public ResponseEntity<Object> getFacilityReportsIssue(HttpServletRequest httpServletRequest) {
        ResponseEntity<Object> responseEntity;
        FacilityReportsIssueGetResponse facilityReportsIssueGetResponse = new FacilityReportsIssueGetResponse();
        String id = JwtUtil.getSubject(httpServletRequest, JWT_TOKEN_COOKIE_NAME, SIGNING_KEY);
        if (id == null) {
            responseEntity = ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body("Sorry, you are not authorized 😅");
        } else {
            int userId = Integer.parseInt(id);
            responseEntity = ResponseEntity.ok()
                    .body(facilityReportsIssueGetResponse);
        }
        return responseEntity;
    }

    @PostMapping("/facility-reports/issue")
    public ResponseEntity<Object> postFacilityReportsIssue(HttpServletRequest httpServletRequest, @RequestBody FacilityReportsIssuePostRequest facilityReportsIssuePostRequest) {
        ResponseEntity<Object> responseEntity;
        FacilityReportsIssuePostResponse facilityReportsIssuePostResponse = new FacilityReportsIssuePostResponse();
        String id = JwtUtil.getSubject(httpServletRequest, JWT_TOKEN_COOKIE_NAME, SIGNING_KEY);
        if (id == null) {
            responseEntity = ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body("Sorry, you are not authorized 😅");
        } else {
            int userId = Integer.parseInt(id);
            FacilityReportsIssue facilityReportsIssue = facilityReportsIssuePostRequest.getFacilityReportsIssue();
            employeeFacilityReportsIssueService.setFacilityReportsIssue(userId, facilityReportsIssue);
            responseEntity = ResponseEntity.ok()
                    .body(facilityReportsIssuePostResponse);
        }
        return responseEntity;
    }

    @GetMapping("/facility-reports/record")
    public ResponseEntity<Object> getFacilityReportsRecord(HttpServletRequest httpServletRequest) {
        ResponseEntity<Object> responseEntity;
        FacilityReportsRecordGetResponse facilityReportsRecordGetResponse = new FacilityReportsRecordGetResponse();
        String id = JwtUtil.getSubject(httpServletRequest, JWT_TOKEN_COOKIE_NAME, SIGNING_KEY);
        if (id == null) {
            responseEntity = ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body("Sorry, you are not authorized 😅");
        } else {
            int userId = Integer.parseInt(id);
            FacilityReportsRecord facilityReportsRecord = employeeFacilityReportsRecordService.getFacilityReportsRecord(userId);
            facilityReportsRecordGetResponse.setFacilityReportsRecord(facilityReportsRecord);
            responseEntity = ResponseEntity.ok()
                    .body(facilityReportsRecordGetResponse);
        }
        return responseEntity;
    }

    @PostMapping("/facility-reports/record")
    public ResponseEntity<Object> postFacilityReportsRecord(HttpServletRequest httpServletRequest, @RequestBody FacilityReportsRecordPostRequest facilityReportsRecordPostRequest) {
        ResponseEntity<Object> responseEntity;
        FacilityReportsRecordPostResponse facilityReportsRecordPostResponse = new FacilityReportsRecordPostResponse();
        String id = JwtUtil.getSubject(httpServletRequest, JWT_TOKEN_COOKIE_NAME, SIGNING_KEY);
        if (id == null) {
            responseEntity = ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body("Sorry, you are not authorized 😅");
        } else {
            int userId = Integer.parseInt(id);
            FacilityRecordUpdate facilityRecordUpdate = facilityReportsRecordPostRequest.getFacilityRecordUpdate();
            employeeFacilityReportsRecordService.setFacilityReportsRecord(userId, facilityRecordUpdate);
            responseEntity = ResponseEntity.ok()
                    .body(facilityReportsRecordPostResponse);
        }
        return responseEntity;
    }

}

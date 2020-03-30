package com.sausage.app.controller.hr;

import com.sausage.app.domain.hr.dashboard.Dashboard;
import com.sausage.app.domain.hr.dashboard.DashboardGetResponse;
import com.sausage.app.domain.hr.dashboard.DashboardPostRequest;
import com.sausage.app.domain.hr.dashboard.DashboardPostResponse;
import com.sausage.app.security.util.JwtUtil;
import com.sausage.app.service.hr.dashboard.HRDashboardService;
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
@RequestMapping("/hr/dashboard")
public class HRDashboardController {

    private HRDashboardService HRDashboardService;

    @Autowired
    public void setHRDashboardService(HRDashboardService HRDashboardService) {
        this.HRDashboardService = HRDashboardService;
    }

    @GetMapping
    public ResponseEntity<Object> getHRDashboard(HttpServletRequest httpServletRequest) {
        ResponseEntity<Object> responseEntity;
        HttpHeaders httpHeaders = new HttpHeaders();

        DashboardGetResponse dashboardGetResponse = new DashboardGetResponse();
        String id = JwtUtil.getSubject(httpServletRequest, JWT_TOKEN_COOKIE_NAME, SIGNING_KEY);
        if (id == null) {
            responseEntity = ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .headers(httpHeaders)
                    .body("Sorry, you are not authorized 😅");
        } else {
            Dashboard dashboard = HRDashboardService.getHRDashboard();
            if (dashboard == null) {
                responseEntity = ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .headers(httpHeaders)
                        .body("Sorry, no data found 😅");
            } else {
                dashboardGetResponse.setDashboard(dashboard);
                responseEntity = ResponseEntity.ok()
                        .headers(httpHeaders)
                        .body(dashboardGetResponse);
            }
        }
        return responseEntity;
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> postHRDashboard(HttpServletRequest httpServletRequest, @RequestBody DashboardPostRequest dashboardPostRequest) {
        ResponseEntity<Object> responseEntity;
        HttpHeaders httpHeaders = new HttpHeaders();

        DashboardPostResponse dashboardPostResponse = new DashboardPostResponse();
        String id = JwtUtil.getSubject(httpServletRequest, JWT_TOKEN_COOKIE_NAME, SIGNING_KEY);
        if (id == null) {
            responseEntity = ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .headers(httpHeaders)
                    .body("Sorry, you are not authorized 😅");
        } else {
            int employeeId = dashboardPostRequest.getEmployeeId();
            HRDashboardService.postHRDashboard(employeeId);
            responseEntity = ResponseEntity.ok()
                    .headers(httpHeaders)
                    .body(dashboardPostResponse);
        }
        return responseEntity;
    }

}

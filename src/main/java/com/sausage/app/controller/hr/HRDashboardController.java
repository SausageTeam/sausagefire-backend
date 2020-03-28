package com.sausage.app.controller.hr;

import com.sausage.app.domain.common.GenericResponse;
import com.sausage.app.domain.common.ServiceStatus;
import com.sausage.app.domain.hr.dashboard.*;
import com.sausage.app.security.util.JwtUtil;
import com.sausage.app.service.hr.dashboard.HRDashboardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
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
    public @ResponseBody
    DashboardGetResponse getHRDashboard(HttpServletRequest httpServletRequest) {
        DashboardGetResponse dashboardGetResponse = new DashboardGetResponse();
        String id = JwtUtil.getSubject(httpServletRequest, JWT_TOKEN_COOKIE_NAME, SIGNING_KEY);
        if (id == null) {
            prepareResponse(dashboardGetResponse, "401", false, "User not Found");
        } else {
            Dashboard dashboard = HRDashboardService.getHRDashboard();
            if (dashboard == null) {
                prepareResponse(dashboardGetResponse, "500", false, "Unexpected Error");
            } else {
                dashboardGetResponse.setDashboard(dashboard);
                prepareResponse(dashboardGetResponse, "200", true, "");
            }
        }
        return dashboardGetResponse;
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody
    DashboardPostResponse postHRDashboard(HttpServletRequest httpServletRequest, @RequestBody DashboardPostRequest dashboardPostRequest) {
        DashboardPostResponse dashboardPostResponse = new DashboardPostResponse();
        String id = JwtUtil.getSubject(httpServletRequest, JWT_TOKEN_COOKIE_NAME, SIGNING_KEY);
        if (id == null) {
            prepareResponse(dashboardPostResponse, "401", false, "User not Found");
        } else {
            int employeeId = dashboardPostRequest.getEmployeeId();
            HRDashboardService.postHRDashboard(employeeId);
            prepareResponse(dashboardPostResponse, "200", true, "");
        }
        return dashboardPostResponse;
    }

    private void prepareResponse(GenericResponse response, String statusCode, boolean success, String errorMessage) {
        response.setServiceStatus(new ServiceStatus(statusCode, success, errorMessage));
    }

}

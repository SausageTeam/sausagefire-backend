package com.sausage.app.controller.hr;

import com.sausage.app.domain.common.GenericResponse;
import com.sausage.app.domain.common.ServiceStatus;
import com.sausage.app.domain.hr.dashboard.*;
import com.sausage.app.service.hr.dashboard.HRDashboardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

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
    DashboardGetResponse getHRDashboard(HttpServletRequest httpServletRequest){
        DashboardGetResponse dashboardGetResponse = new DashboardGetResponse();
        //        int userId = Integer.parseInt(JwtUtil.getSubject(httpServletRequest, Constant.JWT_TOKEN_COOKIE_NAME, Constant.SIGNING_KEY));
        int userId = 2;
        Dashboard dashboard = HRDashboardService.getHRDashboard();
        dashboardGetResponse.setDashboard(dashboard);
        prepareResponse(dashboardGetResponse, true, "");
        return dashboardGetResponse;
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody DashboardPostResponse postHRDashboard(@RequestBody DashboardPostRequest dashboardPostRequest){
        DashboardPostResponse dashboardPostResponse = new DashboardPostResponse();
        //        int userId = Integer.parseInt(JwtUtil.getSubject(httpServletRequest, Constant.JWT_TOKEN_COOKIE_NAME, Constant.SIGNING_KEY));
        int userId = 2;
        int employeeId = dashboardPostRequest.getEmployeeId();
        HRDashboardService.postHRDashboard(employeeId);
        prepareResponse(dashboardPostResponse, true, "");
        return dashboardPostResponse;
    }

    private void prepareResponse(GenericResponse response, boolean success, String errorMessage) {
        response.setServiceStatus(new ServiceStatus(success ? "SUCCESS" : "FAILED", success, errorMessage));
    }
}

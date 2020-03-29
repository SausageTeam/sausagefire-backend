package com.sausage.app.controller.employee;

import com.sausage.app.domain.common.ServiceStatus;
import com.sausage.app.domain.housing.maintenanceHistory.MaintenanceHistory;
import com.sausage.app.domain.housing.maintenanceHistory.MaintenanceHistoryResponse;
import com.sausage.app.domain.housing.housingInfo.HousingInfo;
import com.sausage.app.domain.housing.housingInfo.HousingInfoResponse;
import com.sausage.app.domain.housing.report.FacilityReportResponse;
import com.sausage.app.service.employee.housing.FacilityReportService;
import com.sausage.app.service.employee.housing.HousingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@RestController
@RequestMapping("/employee/housing")
public class HousingController {

    private HousingService housingService;
    private FacilityReportService facilityReportService;

    @Autowired
    public void setHousingService(HousingService housingService) {
        this.housingService = housingService;
    }

    @Autowired
    public void setFacilityReportService(FacilityReportService facilityReportService) {
        this.facilityReportService = facilityReportService;
    }

    public HousingController() {

    }

    @PostMapping("/housingInfo")
    public HousingInfoResponse getHousingInfo() {
        int userID = 2;
        HousingInfoResponse response = new HousingInfoResponse();
        HousingInfo housingInfo = housingService.getHousingInfo(userID);
        response.setHousingInfo(housingInfo);
        prepareHousingResponse(response, true, "");

        return response;
    }

    @PostMapping("/maintenanceHistory")
    public MaintenanceHistoryResponse getMaintenanceHistory() {
        int userID = 2;
        MaintenanceHistoryResponse response = new MaintenanceHistoryResponse();

        List<MaintenanceHistory> maintenanceHistoryList = facilityReportService.getReportListFromEmployee(userID);
        response.setMaintenanceHistoryList(maintenanceHistoryList);
        prepareMaintenanceHistoryResponse(response, true, "");

        return response;
    }

    @Transactional
    @PostMapping("/facilityReport")
    public FacilityReportResponse postFacilityReport(@RequestBody FacilityReportResponse response){
        int userID = 1;
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        String formatDateTime = now.format(format);
        int employeeID = facilityReportService.getEmployeeID(userID);
        facilityReportService.updateFacilityReport(response.getFacilityReport().getTitle(), employeeID, formatDateTime, response.getFacilityReport().getDescription(), "open");

        prepareFacilityReportResponse(response, true, "");
        return response;
    }

    private void prepareHousingResponse(HousingInfoResponse response, boolean success, String errorMessage) {
        response.setServiceStatus(new ServiceStatus(success ? "SUCCESS" : "FAILED", success, errorMessage));
    }

    private void prepareMaintenanceHistoryResponse(MaintenanceHistoryResponse response, boolean success, String errorMessage) {
        response.setServiceStatus(new ServiceStatus(success ? "SUCCESS" : "FAILED", success, errorMessage));
    }

    private void prepareFacilityReportResponse(FacilityReportResponse response, boolean success, String errorMessage) {
        response.setServiceStatus(new ServiceStatus(success ? "SUCCESS" : "FAILED", success, errorMessage));
    }
}

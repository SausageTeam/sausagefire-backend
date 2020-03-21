package com.sausage.app.controller.employee;

import com.sausage.app.dao.facilityReport.FacilityReportDAO;
import com.sausage.app.dao.facilityReport.impl.FacilityReportDAOImpl;
import com.sausage.app.domain.common.ServiceStatus;
import com.sausage.app.domain.housing.maintenanceHistory.MaintenanceHistory;
import com.sausage.app.domain.housing.maintenanceHistory.MaintenanceHistoryRequest;
import com.sausage.app.domain.housing.maintenanceHistory.MaintenanceHistoryResponse;
import com.sausage.app.domain.housing.housingInfo.HousingInfo;
import com.sausage.app.domain.housing.housingInfo.HousingInfoRequest;
import com.sausage.app.domain.housing.housingInfo.HousingInfoResponse;
import com.sausage.app.domain.housing.report.ReportRequest;
import com.sausage.app.domain.housing.report.ReportResponse;
import com.sausage.app.entity.FacilityReport;
import com.sausage.app.service.employee.housing.FacilityReportService;
import com.sausage.app.service.employee.housing.HousingService;
import com.sausage.app.service.employee.housing.impl.FacilityReportServiceImpl;
import com.sausage.app.service.employee.housing.impl.HousingServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
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
    public HousingInfoResponse getHousingInfo(@RequestBody HousingInfoRequest housingInfoRequest) {
        HousingInfoResponse response = new HousingInfoResponse();
        HousingInfo housingInfo = housingService.getHousingInfo(housingInfoRequest);
        response.setHousingInfo(housingInfo);
        prepareHousingResponse(response, true, "");

        return response;
    }

    @PostMapping("/maintenanceHistory")
    public MaintenanceHistoryResponse getMaintenanceHistory(@RequestBody MaintenanceHistoryRequest maintenanceHistoryRequest) {
        MaintenanceHistoryResponse response = new MaintenanceHistoryResponse();

        List<MaintenanceHistory> maintenanceHistoryList = facilityReportService.getReportListFromEmployee(maintenanceHistoryRequest.getUserID());
        response.setMaintenanceHistoryList(maintenanceHistoryList);
        prepareMaintenanceHistoryResponse(response, true, "");

        return response;
    }

    @Transactional
    @PostMapping("/facilityReport")
    public ReportResponse postFacilityReport(@RequestBody ReportRequest request){
        ReportResponse response = new ReportResponse();
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        String formatDateTime = now.format(format);
        int employeeID = facilityReportService.getEmployeeID(request.getUserID());
        facilityReportService.updateFacilityReport(request.getTitle(), employeeID, formatDateTime, request.getDescription(), "open");

        prepareFacilityReportResponse(response, true, "");
        return response;
    }

    private void prepareHousingResponse(HousingInfoResponse response, boolean success, String errorMessage) {
        response.setServiceStatus(new ServiceStatus(success ? "SUCCESS" : "FAILED", success, errorMessage));
    }

    private void prepareMaintenanceHistoryResponse(MaintenanceHistoryResponse response, boolean success, String errorMessage) {
        response.setServiceStatus(new ServiceStatus(success ? "SUCCESS" : "FAILED", success, errorMessage));
    }

    private void prepareFacilityReportResponse(ReportResponse response, boolean success, String errorMessage) {
        response.setServiceStatus(new ServiceStatus(success ? "SUCCESS" : "FAILED", success, errorMessage));
    }
}

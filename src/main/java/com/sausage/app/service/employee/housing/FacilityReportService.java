package com.sausage.app.service.employee.housing;

import com.sausage.app.domain.housing.maintenanceHistory.MaintenanceHistory;
import com.sausage.app.domain.housing.maintenanceHistory.MaintenanceHistoryRequest;
import com.sausage.app.entity.FacilityReport;

import java.util.List;

public interface FacilityReportService {
    public List<MaintenanceHistory> getReportListFromEmployee(int userID);
    FacilityReport updateFacilityReport(String title, int employeeID, String reportDate, String description, String status);
    int getEmployeeID(int userID);
}

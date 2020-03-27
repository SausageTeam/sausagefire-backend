package com.sausage.app.dao.FacilityReport;

public interface FacilityReportDAO {
    List<FacilityReport> getFacilityReportListFromEmployee(int employeeID);
    FacilityReport updateFacilityReport(FacilityReport facilityReport);
}

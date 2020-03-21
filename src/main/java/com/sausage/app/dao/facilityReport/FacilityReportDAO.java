package com.sausage.app.dao.facilityReport;

import com.sausage.app.entity.FacilityReport;

import java.util.List;

public interface FacilityReportDAO {
    List<FacilityReport> getFacilityReportListFromEmployee(int employeeID);

    FacilityReport updateFacilityReport(FacilityReport facilityReport);

}

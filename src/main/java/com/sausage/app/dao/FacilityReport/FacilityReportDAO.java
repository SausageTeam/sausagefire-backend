package com.sausage.app.dao.FacilityReport;

import com.sausage.app.entity.FacilityReport;

import java.util.List;

public interface FacilityReportDAO {

    void setFacilityReport(FacilityReport facilityReport);

    List<FacilityReport> getAllFacilityReport();

}

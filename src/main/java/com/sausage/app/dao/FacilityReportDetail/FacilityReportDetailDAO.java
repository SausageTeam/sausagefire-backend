package com.sausage.app.dao.FacilityReportDetail;

import com.sausage.app.entity.FacilityReport;
import com.sausage.app.entity.FacilityReportDetail;

import java.util.List;

public interface FacilityReportDetailDAO {

    FacilityReportDetail getFacilityReportDetailById(int id);

    void setFacilityReportDetail(FacilityReportDetail facilityReportDetail);

    List<FacilityReportDetail> getFacilityReportDetailsByReport(FacilityReport facilityReport);

}

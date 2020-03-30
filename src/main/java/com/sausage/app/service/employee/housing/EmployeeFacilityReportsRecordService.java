package com.sausage.app.service.employee.housing;

import com.sausage.app.domain.employee.housing.facilityReports.record.FacilityRecordUpdate;
import com.sausage.app.domain.employee.housing.facilityReports.record.FacilityReportsRecord;

import java.util.List;

public interface EmployeeFacilityReportsRecordService {

    FacilityReportsRecord getFacilityReportsRecord(int userId);

    void setFacilityReportsRecord(int userId, FacilityRecordUpdate facilityRecordUpdate);

}

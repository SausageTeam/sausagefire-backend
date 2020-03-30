package com.sausage.app.domain.employee.housing.facilityReports.record;

import lombok.*;

import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class FacilityReportsRecord {

    List<FacilityReportsRecordDetail> facilityReportsRecordDetailList;

}

package com.sausage.app.domain.employee.housing.facilityReports.record;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class FacilityRecordUpdate {

    int facilityReportDetailId;

    String description;

}

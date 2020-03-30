package com.sausage.app.domain.employee.housing.facilityReports.record;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class FacilityRecordComment {

    int facilityReportsRecordDetailId;

    String description;

    String createdUser;

    String commentDate;

}

package com.sausage.app.domain.employee.housing.facilityReports.record;

import lombok.*;

import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class FacilityReportsRecordDetail {

    String title;

    String description;

    String createdUser;

    String reportDate;

    String status;

    List<FacilityRecordComment> facilityRecordCommentList;

}

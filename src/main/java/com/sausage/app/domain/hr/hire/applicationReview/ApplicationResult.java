package com.sausage.app.domain.hr.hire.applicationReview;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ApplicationResult {

    int employeeId;

    boolean accept;

    String comments;

}

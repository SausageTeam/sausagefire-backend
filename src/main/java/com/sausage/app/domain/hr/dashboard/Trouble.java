package com.sausage.app.domain.hr.dashboard;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Dashboard {

    int employeeId;

    String firstName;

    String middleName;

    String lastName;

    String workAuthorization;

    String visaEndDate;

    int leftDays;

}

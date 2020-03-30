package com.sausage.app.domain.hr.employeeProfile;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EmployeeRecord {

    String firstName;

    String middleName;

    String lastName;

    String ssn;

    String startDate;

    String visaStatus;

}

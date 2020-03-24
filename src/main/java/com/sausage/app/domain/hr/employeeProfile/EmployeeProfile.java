package com.sausage.app.domain.hr.employeeProfile;

import lombok.*;

import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EmployeeProfile {

    List<EmployeeRecord> employeeRecordList;

}

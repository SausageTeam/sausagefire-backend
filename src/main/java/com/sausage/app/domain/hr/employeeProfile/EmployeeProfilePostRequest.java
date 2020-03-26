package com.sausage.app.domain.hr.employeeProfile;

import com.sausage.app.domain.common.ServiceStatus;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EmployeeProfilePostRequest {

    ServiceStatus serviceStatus;

}

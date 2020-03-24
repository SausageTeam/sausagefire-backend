package com.sausage.app.domain.hr.employeeProfile;

import com.sausage.app.domain.common.GenericResponse;
import com.sausage.app.domain.common.ServiceStatus;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EmployeeProfilePostResponse extends GenericResponse {

    ServiceStatus serviceStatus;

}

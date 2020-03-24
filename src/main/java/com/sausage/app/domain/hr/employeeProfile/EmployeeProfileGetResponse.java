package com.sausage.app.domain.hr.employeeProfile;

import com.sausage.app.domain.common.GenericResponse;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EmployeeProfileGetResponse extends GenericResponse {

    EmployeeProfile employeeProfile;

}

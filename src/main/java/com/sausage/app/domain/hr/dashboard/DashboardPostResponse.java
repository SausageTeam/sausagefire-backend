package com.sausage.app.domain.hr.dashboard;

import com.sausage.app.domain.common.GenericResponse;
import com.sausage.app.domain.common.ServiceStatus;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DashboardPostResponse extends GenericResponse {

    ServiceStatus serviceStatus;

}

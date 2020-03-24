package com.sausage.app.domain.visaStatusManagement;

import com.sausage.app.domain.common.GenericResponse;
import com.sausage.app.domain.common.ServiceStatus;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class VisaStatusManagementGetResponse extends GenericResponse {

    VisaStatusManagement visaStatusManagement;

    ServiceStatus serviceStatus;

}

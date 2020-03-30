package com.sausage.app.domain.hr.hire.generateToken;

import com.sausage.app.domain.common.GenericResponse;
import com.sausage.app.domain.common.ServiceStatus;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class HireGenerateTokenGetResponse extends GenericResponse {

    ServiceStatus serviceStatus;

}

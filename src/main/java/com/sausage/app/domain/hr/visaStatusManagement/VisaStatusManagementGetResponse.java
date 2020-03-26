package com.sausage.app.domain.hr.visaStatusManagement;

import com.sausage.app.domain.common.GenericResponse;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class VisaStatusManagementGetResponse extends GenericResponse {

    VisaStatusManagement visaStatusManagement;

}

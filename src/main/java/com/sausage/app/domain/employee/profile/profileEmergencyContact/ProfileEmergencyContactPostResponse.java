package com.sausage.app.domain.employee.profile.profileEmergencyContact;

import com.sausage.app.domain.common.GenericResponse;
import com.sausage.app.domain.common.ServiceStatus;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProfileEmergencyContactPostResponse extends GenericResponse {

    ServiceStatus serviceStatus;

}

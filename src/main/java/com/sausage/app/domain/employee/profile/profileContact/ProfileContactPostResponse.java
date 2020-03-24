package com.sausage.app.domain.employee.profile.profileContact;

import com.sausage.app.domain.common.GenericResponse;
import com.sausage.app.domain.common.ServiceStatus;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProfileContactPostResponse extends GenericResponse {

    ServiceStatus serviceStatus;

}

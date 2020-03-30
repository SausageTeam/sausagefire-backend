package com.sausage.app.domain.employee.profile.profileAddress;

import com.sausage.app.domain.common.GenericResponse;
import com.sausage.app.domain.common.ServiceStatus;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProfileAddressPostResponse extends GenericResponse {

    ServiceStatus serviceStatus;

}

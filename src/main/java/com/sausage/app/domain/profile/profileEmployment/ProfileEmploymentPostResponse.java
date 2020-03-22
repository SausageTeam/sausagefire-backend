package com.sausage.app.domain.profile.profileEmployment;

import com.sausage.app.domain.common.GenericResponse;
import com.sausage.app.domain.common.ServiceStatus;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProfileEmploymentPostResponse extends GenericResponse {

    ServiceStatus serviceStatus;

}

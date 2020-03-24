package com.sausage.app.domain.profile.profileName;

import com.sausage.app.domain.common.GenericResponse;
import com.sausage.app.domain.common.ServiceStatus;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProfileNamePostResponse extends GenericResponse {

    ServiceStatus serviceStatus;

}

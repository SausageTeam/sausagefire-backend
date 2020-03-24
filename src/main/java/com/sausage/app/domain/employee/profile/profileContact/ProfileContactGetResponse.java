package com.sausage.app.domain.profile.profileContact;

import com.sausage.app.domain.common.GenericResponse;
import com.sausage.app.domain.common.ServiceStatus;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProfileContactGetResponse extends GenericResponse {

    ProfileContact profileContact;

    ServiceStatus serviceStatus;

}

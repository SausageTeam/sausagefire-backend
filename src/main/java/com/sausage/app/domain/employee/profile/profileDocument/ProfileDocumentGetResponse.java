package com.sausage.app.domain.employee.profile.profileDocument;

import com.sausage.app.domain.common.GenericResponse;
import com.sausage.app.domain.common.ServiceStatus;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProfileDocumentGetResponse extends GenericResponse {

    ProfileDocument profileDocument;

    ServiceStatus serviceStatus;

}

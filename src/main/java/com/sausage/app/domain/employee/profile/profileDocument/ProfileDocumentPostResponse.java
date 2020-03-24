package com.sausage.app.domain.profile.profileDocument;

import com.sausage.app.domain.common.GenericResponse;
import com.sausage.app.domain.common.ServiceStatus;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProfileDocumentPostResponse extends GenericResponse {

    ServiceStatus serviceStatus;

}

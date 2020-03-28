package com.sausage.app.domain.common.auth;

import com.sausage.app.domain.common.GenericResponse;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AuthGetResponse extends GenericResponse {

    String redirectUrl;

    Auth auth;

}

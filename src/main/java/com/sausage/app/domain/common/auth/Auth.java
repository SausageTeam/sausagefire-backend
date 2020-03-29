package com.sausage.app.domain.common.auth;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Auth {

    int roleId;

    int onboardingStatus;

    boolean ifNeedVisa;

}

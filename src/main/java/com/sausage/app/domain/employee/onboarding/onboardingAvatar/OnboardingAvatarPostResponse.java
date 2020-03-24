package com.sausage.app.domain.employee.onboarding.onboardingAvatar;

import com.sausage.app.domain.common.GenericResponse;
import com.sausage.app.domain.common.ServiceStatus;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OnboardingAvatarPostResponse extends GenericResponse {

    ServiceStatus serviceStatus;

}

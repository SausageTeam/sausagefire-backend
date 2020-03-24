package com.sausage.app.domain.employee.onboarding.onboardingAvatar;

import com.sausage.app.domain.common.GenericResponse;
import com.sausage.app.domain.common.ServiceStatus;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OnboardingAvatarPostResponse extends GenericResponse {

    ServiceStatus serviceStatus;

}

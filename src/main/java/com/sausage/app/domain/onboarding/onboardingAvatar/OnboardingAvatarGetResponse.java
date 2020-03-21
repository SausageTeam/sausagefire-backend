package com.sausage.app.domain.onboarding.onboardingAvatar;

import com.sausage.app.domain.common.ServiceStatus;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OnboardingAvatarGetResponse {

    OnboardingAvatar onboardingAvatar;

    ServiceStatus serviceStatus;

}

package com.sausage.app.service.onboarding;

import com.sausage.app.domain.onboarding.onboardingAvatar.OnboardingAvatar;

public interface OnboardingAvatarService {

    OnboardingAvatar getOnboardingAvatar(int userId);
}

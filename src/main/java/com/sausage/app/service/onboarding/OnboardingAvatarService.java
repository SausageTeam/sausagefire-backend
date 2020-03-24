package com.sausage.app.service.employee.onboarding;

import com.sausage.app.domain.employee.onboarding.onboardingAvatar.OnboardingAvatar;

public interface OnboardingAvatarService {

    OnboardingAvatar getOnboardingAvatar(int userId);

    void setOnboardingAvatar(int userId, OnboardingAvatar onboardingAvatar);

}

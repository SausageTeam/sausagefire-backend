package com.sausage.app.service.onboarding;

import com.sausage.app.domain.onboarding.onboardingAvatar.OnboardingAvatar;

import java.io.File;

public interface OnboardingAvatarService {

    OnboardingAvatar getOnboardingAvatar(int userId);

    void setOnboardingAvatar(int userId, OnboardingAvatar onboardingAvatar);

}

package com.sausage.app.service.onboarding;

import com.sausage.app.domain.onboarding.onboardingDriving.OnboardingDriving;

public interface OnboardingDrivingService {

    OnboardingDriving getOnboardingDriving(int userId);

    void setOnboardingDriving(int userId, OnboardingDriving onboardingDriving);

}

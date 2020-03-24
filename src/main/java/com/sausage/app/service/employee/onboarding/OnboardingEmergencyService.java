package com.sausage.app.service.onboarding;

import com.sausage.app.domain.onboarding.onboardingEmergency.OnboardingEmergency;

public interface OnboardingEmergencyService {

    OnboardingEmergency getOnboardingEmergency(int userId);

    void setOnboardingEmergency(int userId, OnboardingEmergency onboardingEmergency);

}

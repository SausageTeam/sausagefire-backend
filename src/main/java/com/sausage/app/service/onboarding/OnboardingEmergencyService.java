package com.sausage.app.service.onboarding;

import com.sausage.app.domain.onboarding.onboardingEmergency.OnboardingEmergency;

import java.util.List;

public interface OnboardingEmergencyService {

    List<OnboardingEmergency> getOnboardingEmergency(int userId);

    void setOnboardingEmergency(int userId, OnboardingEmergency onboardingEmergency);

}

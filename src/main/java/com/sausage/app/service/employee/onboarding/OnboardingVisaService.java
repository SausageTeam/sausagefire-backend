package com.sausage.app.service.onboarding;

import com.sausage.app.domain.onboarding.onboardingVisa.OnboardingVisa;

public interface OnboardingVisaService {

    OnboardingVisa getOnboardingVisa(int userId);

    void setOnboardingVisa(int userId, OnboardingVisa onboardingVisa);

}

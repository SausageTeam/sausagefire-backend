package com.sausage.app.service.employee.onboarding;

import com.sausage.app.domain.employee.onboarding.onboardingVisa.OnboardingVisa;

public interface OnboardingVisaService {

    OnboardingVisa getOnboardingVisa(int userId);

    void setOnboardingVisa(int userId, OnboardingVisa onboardingVisa);

}

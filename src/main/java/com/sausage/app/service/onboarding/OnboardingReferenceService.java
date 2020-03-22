package com.sausage.app.service.onboarding;

import com.sausage.app.domain.onboarding.onboardingReference.OnboardingReference;

public interface OnboardingReferenceService {

    OnboardingReference getOnboardingReference(int userId);

    void setOnboardingReference(int userId, OnboardingReference onboardingReference);

}

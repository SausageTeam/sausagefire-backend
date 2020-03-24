package com.sausage.app.service.employee.onboarding;

import com.sausage.app.domain.employee.onboarding.onboardingReference.OnboardingReference;

public interface EmployeeOnboardingReferenceService {

    OnboardingReference getOnboardingReference(int userId);

    void setOnboardingReference(int userId, OnboardingReference onboardingReference);

}

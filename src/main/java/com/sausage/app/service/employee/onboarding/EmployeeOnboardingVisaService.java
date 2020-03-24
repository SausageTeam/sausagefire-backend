package com.sausage.app.service.employee.onboarding;

import com.sausage.app.domain.employee.onboarding.onboardingVisa.OnboardingVisa;

public interface EmployeeOnboardingVisaService {

    OnboardingVisa getOnboardingVisa(int userId);

    void setOnboardingVisa(int userId, OnboardingVisa onboardingVisa);

}

package com.sausage.app.service.employee.onboarding;

import com.sausage.app.domain.employee.onboarding.onboardingEmergency.OnboardingEmergency;

public interface EmployeeOnboardingEmergencyService {

    OnboardingEmergency getOnboardingEmergency(int userId);

    void setOnboardingEmergency(int userId, OnboardingEmergency onboardingEmergency);

}

package com.sausage.app.service.employee.onboarding;

import com.sausage.app.domain.employee.onboarding.onboardingDriving.OnboardingDriving;

public interface EmployeeOnboardingDrivingService {

    OnboardingDriving getOnboardingDriving(int userId);

    void setOnboardingDriving(int userId, OnboardingDriving onboardingDriving);

}

package com.sausage.app.service.employee.onboarding;

import com.sausage.app.domain.employee.onboarding.onboardingPerson.OnboardingPerson;

public interface EmployeeOnboardingPersonService {

    OnboardingPerson getOnboardingPerson(int userId);

    void postOnboardingPerson(int userId, OnboardingPerson onboardingPerson);

}

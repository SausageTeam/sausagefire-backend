package com.sausage.app.service.employee.onboarding;

import com.sausage.app.domain.employee.onboarding.onboardingPerson.OnboardingPerson;
import com.sausage.app.domain.employee.onboarding.onboardingPerson.OnboardingPersonRequest;

public interface EmployeeOnboardingPersonService {

    OnboardingPerson getOnboardingPerson(int userId);

    void postOnboardingPerson(int userId, OnboardingPersonRequest onboardingPersonRequest);

}

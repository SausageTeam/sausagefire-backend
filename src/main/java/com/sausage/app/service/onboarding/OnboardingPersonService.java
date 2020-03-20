package com.sausage.app.service.onboarding;

import com.sausage.app.domain.onboarding.onboardingPerson.OnboardingPerson;
import com.sausage.app.domain.onboarding.onboardingPerson.OnboardingPersonRequest;

public interface OnboardingPersonService {

    OnboardingPerson getOnboardingPerson(int userId);

    void postOnboardingPerson(int userId, OnboardingPersonRequest onboardingPersonRequest);

}

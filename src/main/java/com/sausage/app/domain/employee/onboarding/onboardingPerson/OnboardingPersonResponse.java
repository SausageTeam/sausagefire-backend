package com.sausage.app.domain.onboarding.onboardingPerson;

import com.sausage.app.domain.common.GenericResponse;
import com.sausage.app.domain.common.ServiceStatus;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OnboardingPersonResponse extends GenericResponse {

    OnboardingPerson onboardingPerson;

    ServiceStatus serviceStatus;

}

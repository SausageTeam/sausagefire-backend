package com.sausage.app.domain.employee.onboarding.onboardingVisa;

import com.sausage.app.domain.common.GenericResponse;
import com.sausage.app.domain.common.ServiceStatus;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OnboardingVisaGetResponse extends GenericResponse {

    OnboardingVisa onboardingVisa;

    ServiceStatus serviceStatus;

}

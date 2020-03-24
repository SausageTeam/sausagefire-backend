package com.sausage.app.domain.onboarding.onboardingVisa;

import com.sausage.app.domain.common.GenericResponse;
import com.sausage.app.domain.common.ServiceStatus;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OnboardingVisaPostResponse extends GenericResponse {

    ServiceStatus serviceStatus;

}

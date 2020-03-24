package com.sausage.app.domain.onboarding.onboardingEmergency;

import com.sausage.app.domain.common.GenericResponse;
import com.sausage.app.domain.common.ServiceStatus;
import com.sausage.app.domain.onboarding.onboardingReference.OnboardingReference;
import lombok.*;

import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OnboardingEmergencyGetResponse extends GenericResponse {

    OnboardingEmergency onboardingEmergency;

    ServiceStatus serviceStatus;

}

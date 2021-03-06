package com.sausage.app.domain.employee.onboarding.onboardingEmergency;

import com.sausage.app.domain.common.GenericResponse;
import com.sausage.app.domain.common.ServiceStatus;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OnboardingEmergencyGetResponse extends GenericResponse {

    OnboardingEmergency onboardingEmergency;

    ServiceStatus serviceStatus;

}

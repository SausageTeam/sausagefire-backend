package com.sausage.app.domain.onboarding.onboardingReference;

import com.sausage.app.domain.common.GenericResponse;
import com.sausage.app.domain.common.ServiceStatus;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OnboardingReferenceGetResponse extends GenericResponse {

    OnboardingReference onboardingReference;

    ServiceStatus serviceStatus;

}

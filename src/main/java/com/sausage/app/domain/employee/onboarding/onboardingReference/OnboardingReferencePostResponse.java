package com.sausage.app.domain.employee.onboarding.onboardingReference;

import com.sausage.app.domain.common.GenericResponse;
import com.sausage.app.domain.common.ServiceStatus;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OnboardingReferencePostResponse extends GenericResponse {

    ServiceStatus serviceStatus;

}

package com.sausage.app.domain.employee.onboarding.onboardingDriving;

import com.sausage.app.domain.common.GenericResponse;
import com.sausage.app.domain.common.ServiceStatus;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OnboardingDrivingGetResponse extends GenericResponse {

    OnboardingDriving onboardingDriving;

    ServiceStatus serviceStatus;
    
}

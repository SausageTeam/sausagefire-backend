package com.sausage.app.domain.onboarding.onboardingDriving;

import com.sausage.app.domain.common.GenericResponse;
import com.sausage.app.domain.common.ServiceStatus;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OnboardingDrivingPostResponse extends GenericResponse {

    ServiceStatus serviceStatus;

}
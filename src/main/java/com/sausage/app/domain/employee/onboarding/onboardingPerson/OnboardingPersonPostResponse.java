package com.sausage.app.domain.employee.onboarding.onboardingPerson;

import com.sausage.app.domain.common.GenericResponse;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OnboardingPersonPostResponse extends GenericResponse {

    OnboardingPerson onboardingPerson;

}

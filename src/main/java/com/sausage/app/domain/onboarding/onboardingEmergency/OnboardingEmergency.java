package com.sausage.app.domain.onboarding.onboardingEmergency;

import com.sausage.app.domain.onboarding.onboardingReference.OnboardingReferenceAddress;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OnboardingEmergency {

    String firstName;

    String lastName;

    String middleName;

    String email;

    String cellPhone;

    OnboardingReferenceAddress onboardingReferenceAddress;

    String relationship;

    String title;

}

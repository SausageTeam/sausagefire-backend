package com.sausage.app.domain.onboarding.onboardingReference;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OnboardingReference {

    String firstName;

    String lastName;

    String middleName;

    String email;

    String cellPhone;

    OnboardingReferenceAddress onboardingReferenceAddress;

    String relationship;

    String title;

}

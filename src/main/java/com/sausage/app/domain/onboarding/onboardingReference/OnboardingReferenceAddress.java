package com.sausage.app.domain.onboarding.onboardingReference;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OnboardingReferenceAddress {

    String addressLineOne;

    String addressLineTwo;

    String city;

    String zipCode;

    String stateName;

    String stateAbbr;

}

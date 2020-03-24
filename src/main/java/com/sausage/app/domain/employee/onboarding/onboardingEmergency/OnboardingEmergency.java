package com.sausage.app.domain.onboarding.onboardingEmergency;

import com.sausage.app.domain.common.AddressDomain;
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

    AddressDomain addressDomain;

    String relationship;

    String title;

}

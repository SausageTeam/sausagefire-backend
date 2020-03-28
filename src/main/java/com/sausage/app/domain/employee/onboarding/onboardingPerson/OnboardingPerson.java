package com.sausage.app.domain.employee.onboarding.onboardingPerson;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OnboardingPerson {

    String firstName;

    String lastName;

    String middleName;

    String preferredName;

    String email;

    String cellPhone;

    String alternatePhone;

    String gender;

    String ssn;

    String dob;

}

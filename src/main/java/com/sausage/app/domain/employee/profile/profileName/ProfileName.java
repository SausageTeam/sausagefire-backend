package com.sausage.app.domain.employee.profile.profileName;

import lombok.*;

import java.io.File;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProfileName {

    String firstName;

    String middleName;

    String lastName;

    String preferredName;

    String avatarUri;

    String dob;

    int age;

    String gender;

    String ssn;

}

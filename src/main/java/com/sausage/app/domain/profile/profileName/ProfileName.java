package com.sausage.app.domain.profile.profileName;

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

    File avatar;

    String dob;

    int age;

    String gender;

    String ssn;

}

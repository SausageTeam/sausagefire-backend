package com.sausage.app.domain.profile.profileEmergencyContact;

import com.sausage.app.domain.common.AddressDomain;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProfileEmergencyContact {

    String firstName;

    String middleName;

    String lastName;

    String cellPhone;

    AddressDomain addressDomain;

}

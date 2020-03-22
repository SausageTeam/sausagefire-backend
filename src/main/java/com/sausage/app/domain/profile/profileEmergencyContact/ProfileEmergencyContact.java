package com.sausage.app.domain.profile.profileEmergencyContact;

import com.sausage.app.domain.common.AddressDomain;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProfileEmergencyContact {

    String fullName;

    String cellPhone;

    AddressDomain addressDomain;

}

package com.sausage.app.domain.employee.profile.profileAddress;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProfileAddress {

    String addressLineOne;

    String addressLineTwo;

    String city;

    String zipCode;

    String stateName;

    String stateAbbr;

}

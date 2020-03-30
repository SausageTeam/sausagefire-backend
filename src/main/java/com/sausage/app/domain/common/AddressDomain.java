package com.sausage.app.domain.common;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AddressDomain {

    String addressLineOne;

    String addressLineTwo;

    String city;

    String zipCode;

    String stateName;

    String stateAbbr;

}

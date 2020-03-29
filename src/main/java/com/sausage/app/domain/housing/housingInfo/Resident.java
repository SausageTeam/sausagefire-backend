package com.sausage.app.domain.housing.housingInfo;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class Resident {
    private String firstName;
    private String middleName;
    private String lastName;
    private String phoneNumber;
}

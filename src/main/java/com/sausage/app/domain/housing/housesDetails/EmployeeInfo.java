package com.sausage.app.domain.housing.housesDetails;

import lombok.*;

import java.io.Serializable;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class EmployeeInfo implements Serializable {
    private String name;
    private String phoneNumber;
    private String email;
    private String car;
}

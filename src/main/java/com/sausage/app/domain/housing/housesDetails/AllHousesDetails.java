package com.sausage.app.domain.housing.housesDetails;

import lombok.*;

import java.io.Serializable;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class AllHousesDetails implements Serializable {
    private int id;
    private String address;
    private String landlord; // legal full name
    private String phoneNumber;
    private String email;
    private int numOfResidents;
}

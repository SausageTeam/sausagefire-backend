package com.sausage.app.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;

@Getter
@Setter
@Entity
@Table(name = "Address")
public class Address implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private int id;

    @Column(name = "ADDRESS_LINE_1")
    private String addressLineOne;

    @Column(name = "ADDRESS_LINE_2")
    private String addressLineTwo;

    @Column(name = "CITY")
    private String city;

    @Column(name = "ZIPCODE")
    private String zipcode;

    @Column(name = "STATE_NAME")
    private String stateName;

    @Column(name = "STATE_ABBR")
    private String stateAbbreviation;

    @Column(name = "PERSON_ID")
    private int personID;

}

package com.sausage.app.entity;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
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

    @Column(name = "ZIP_CODE")
    private String zipCode;


    @Column(name = "STATE_NAME")
    private String stateName;

    @Column(name = "STATE_ABBR")
    private String stateAbbr;

    @ManyToOne
    @JoinColumn(name = "PERSON_ID")
    private Person person;

}

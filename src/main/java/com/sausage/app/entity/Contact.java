package com.sausage.app.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;

@Getter
@Setter
@Entity
@Table(name = "Contact")
public class Contact implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private int id;

    @Column(name = "PERSON_ID")
    private int personID;

    @Column(name = "RELATIONSHIP")
    private String relationship;

    @Column(name = "TITLE")
    private String title;

    @Column(name = "IS_REFERENCE")
    private int isReference;

    @Column(name = "IS_EMERGENCY")
    private int isEmergency;

    @Column(name = "IS_LANDLORD")
    private int isLandlord;


}

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
@Table(name = "Contact")
public class Contact implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private int id;

    @OneToOne
    @JoinColumn(name = "PERSON_ID")
    private Person person;

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

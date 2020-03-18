package com.sausage.app.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;

@Getter
@Setter
@Entity
@Table(name = "Person")
public class Person implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private int id;

    @Column(name = "FIRST_NAME")
    private String firstName;

    @Column(name = "LAST_NAME")
    private String lastName;

    @Column(name = "MIDDLE_NAME")
    private String middleName;

    @Column(name = "EMAIL")
    private String email;

    @Column(name = "CELLPHONE")
    private String cellphone;

    @Column(name = "ALTERNATE_PHONE")
    private String alternatePhone;

    @Column(name = "GENDER")
    private String gender;

    @Column(name = "SSN")
    private String SSN;

    @Column(name = "DOB")
    private String DOB;

}

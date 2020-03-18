package com.sausage.app.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;

@Getter
@Setter
@Entity
@Table(name = "User")
public class User implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private int id;

    @Column(name = "USERNAME")
    private String userName;

    @Column(name = "PASSWORD")
    private String userPassword;

    @Column(name = "PERSON_ID")
    private int personId;

    @Column(name = "CREATE_DATE")
    private String createDate;

    @Column(name = "MODIFICATION_DATE")
    private String modificationDate;

}

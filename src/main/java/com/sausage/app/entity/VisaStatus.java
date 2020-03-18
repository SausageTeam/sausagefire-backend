package com.sausage.app.entity;

import lombok.Getter;
import lombok.Setter;
import org.omg.CORBA.PRIVATE_MEMBER;

import javax.persistence.*;
import java.io.Serializable;

@Getter
@Setter
@Entity
@Table(name = "VisaStatus")
public class VisaStatus implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private int id;

    @Column(name = "VISA_TYPE")
    private String visaType;

    @Column(name = "ACTIVE")
    private int active;

    @Column(name = "MODIFICATION_DATE")
    private String modificationDate;

    @Column(name = "CREATE_USER")
    private String createUser;
}

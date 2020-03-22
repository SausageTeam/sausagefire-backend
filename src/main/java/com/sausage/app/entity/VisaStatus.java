package com.sausage.app.entity;

import lombok.*;
import org.omg.CORBA.PRIVATE_MEMBER;

import javax.persistence.*;
import java.io.Serializable;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "VisaStatus")
public class VisaStatus implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private int id;

    @Column(name = "VISA_TYPE")
    private String visaType;

    @Column(name = "ACTIVE_FLAG")
    private int activeFlag;

    @Column(name = "MODIFICATION_DATE")
    private String modificationDate;

    @Column(name = "CREATE_USER")
    private int createUser;
}

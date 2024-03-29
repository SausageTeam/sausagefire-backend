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
@Table(name = "User")
public class User implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private int id;

    @Column(name = "USERNAME")
    private String userName;

    @Column(name = "EMAIL")
    private String email;

    @Column(name = "PASSWORD")
    private String userPassword;

    @OneToOne
    @JoinColumn(name = "PERSON_ID")
    private Person person;

    @Column(name = "CREATED_DATE_TIME")
    private String createdDateTime;

    @Column(name = "MODIFICATION_DATE_TIME")
    private String modificationDateTime;

}

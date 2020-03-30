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
@Table(name = "RegistrationToken")
public class RegistrationToken implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private int id;

    @Column(name = "TOKEN")
    private String token;

    @Column(name = "VALID_DURATION")
    private int validDuration;

    @Column(name = "EMAIL")
    private String email;

    @Column(name = "ACTIVE_FLAG")
    private int activeFlag;

    @Column(name = "CREATED_DATE_TIME")
    private String createdDateTime;

    @Column(name = "CREATED_USER")
    private int createdUser;

}

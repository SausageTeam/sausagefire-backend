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

    @Column(name = "CREATED_DATE_TIME")
    private String createdDateTime;

    @Column(name = "MODIFICATION_DATE_TIME")
    private String modificationDateTime;

    @Column(name = "CREATED_USER")
    private int createdUser;

}

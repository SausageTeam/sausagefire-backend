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
@Table(name = "Employee")
public class Employee implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private int id;

    @OneToOne
    @JoinColumn(name = "PERSON_ID")
    private Person person;

    @Column(name = "TITLE")
    private String title;

    @Column(name = "MANAGER_ID")
    private int managerID;

    @Column(name = "START_DATE")
    private String startDate;

    @Column(name = "END_DATE")
    private String endDate;

    @Column(name = "AVATAR")
    private String avatar;

    @Column(name = "CAR")
    private String car;

    @Column(name = "VISA_STATUS_ID")
    private int visaStatusId;

    @Column(name = "VISA_START_DATE")
    private String visaStartDate;

    @Column(name = "VISA_END_DATE")
    private String visaEndDate;

    @Column(name = "DRIVER_LICENSE")
    private String driverLicense;

    @Column(name = "DRIVER_LICENSE_EXPIRATION_DATE")
    private String driverLicenseExpirationDate;

    @Column(name = "HOUSE_ID")
    private int houseId;

    @Column(name = "REFERENCE_ID")
    private int referenceId;

    @Column(name = "EMERGENCY_ID")
    private int emergencyId;

}

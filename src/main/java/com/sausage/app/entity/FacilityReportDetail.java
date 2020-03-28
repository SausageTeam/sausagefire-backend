package com.sausage.app.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;

@Getter
@Setter
@Entity
@Table(name = "FacilityReportDetail")
public class FacilityReportDetail implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private int id;

    @Column(name = "REPORT_ID")
    private int reportID;

    @Column(name = "EMPLOYEE_ID")
    private int employeeID;

    @Column(name = "COMMENTS")
    private String comments;

    @Column(name = "CREATED_DATE_TIME")
    private String createdDate;

    @Column(name = "MODIFICATION_DATE_TIME")
    private String lastModificationDate;

}

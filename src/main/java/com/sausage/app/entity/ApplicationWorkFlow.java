package com.sausage.app.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;

@Getter
@Setter
@Entity
@Table(name = "ApplicationWorkFlow")
public class ApplicationWorkFlow implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private int id;

    @OneToOne
    @JoinColumn(name = "EMPLOYEE_ID")
    private Employee employee;

    @Column(name = "CREATED_DATE")
    private String createdDate;

    @Column(name = "MODIFICATION_DATE")
    private String modificationDate;

    @Column(name = "STATUS")
    private int status;

    @Column(name = "COMMENTS")
    private String comments;

    @Column(name = "TYPE")
    private String type;

}

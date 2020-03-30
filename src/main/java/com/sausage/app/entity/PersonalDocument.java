package com.sausage.app.entity;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;

import javax.persistence.*;
import java.io.Serializable;

@Getter
@Setter
@Entity
@Table(name = "PersonalDocument")
public class PersonalDocument implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private int id;

    @ManyToOne
    @JoinColumn(name = "EMPLOYEE_ID")
    private Employee employee;

    @Column(name = "PATH")
    private String path;

    @Column(name = "TITLE")
    private String title;

    @Column(name = "COMMENT")
    private String comment;

    @Column(name = "CREATED_DATE_TIME")
    private String createdDateTime;

    @Column(name = "CREATED_USER")
    private String createdUser;

}

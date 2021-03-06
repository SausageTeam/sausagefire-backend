package com.sausage.app.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;

@Getter
@Setter
@Entity
@Table(name = "DigitalDocument")
public class DigitalDocument implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private int id;

    @Column(name = "TYPE")
    private String type;

    @Column(name = "REQUIRED")
    private int required;

    @Column(name = "DESCRIPTION")
    private String description;

    @Column(name = "TEMPLATE_LOCATION")
    private String templateLocation;

}

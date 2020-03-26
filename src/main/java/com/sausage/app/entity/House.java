package com.sausage.app.entity;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;

import javax.persistence.*;
import java.io.Serializable;

@Getter
@Setter
@Entity
@Table(name = "House")
public class House implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Integer id;

    @Column(name = "CONTACT_ID")
    private Integer contactID;

    @Column(name = "ADDRESS")
    private String address;

    @Column(name = "NUMBER_OF_PERSON")
    private Integer numberOfPerson;

    @Column(name = "MAX_PERSON")
    private Integer maxPerson;
}

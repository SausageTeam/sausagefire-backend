package com.sausage.app.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;

@Getter
@Setter
@Entity
@Table(name = "Role")
public class Role implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private int id;

    @Column(name = "ROLE_NAME")
    private String roleName;

    @Column(name = "DESCRIPTION")
    private String description;

    @Column(name = "CREATED_DATE_TIME")
    private String createdDateTime;

    @Column(name = "MODIFICATION_DATE_TIME")
    private String modificationDateTime;

    @Column(name = "LAST_MODIFICATION_USER")
    private String lastModificationUser;

}

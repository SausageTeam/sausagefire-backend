package com.sausage.app.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;

@Getter
@Setter
@Entity
@Table(name = "RolePermission")
public class RolePermission implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private int id;

    @Column(name = "ROLE_ID")
    private int roleID;

    @Column(name = "PERMISSION_ID")
    private int permissionID;

    @Column(name = "ACTIVE_FLAG")
    private String activeFlag;

    @Column(name = "CREATE_DATE")
    private String createDate;

    @Column(name = "MODIFICATION_DATE")
    private String modificationDate;

    @Column(name = "LAST_MODIFICATION_USER")
    private String lastModificationUser;
}

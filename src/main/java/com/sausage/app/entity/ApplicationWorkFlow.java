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
@Table(name = "ApplicationWorkFlow")
public class ApplicationWorkFlow implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private int id;

    @OneToOne
    @JoinColumn(name = "EMPLOYEE_ID")
    private Employee employee;

    @Column(name = "STATUS")
    private int status;

    @Column(name = "COMMENTS")
    private String comments;

    @Column(name = "TYPE")
    private String type;

    @Column(name = "UPLOAD")
    private int upload;

    @Column(name = "NOTIFY")
    private int notify;

    @Column(name = "CREATED_DATE_TIME")
    private String createdDateTime;

    @Column(name = "MODIFICATION_DATE_TIME")
    private String modificationDateTime;

}

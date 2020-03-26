package com.sausage.app.domain.housing.maintenanceHistory;

import lombok.*;

import java.io.Serializable;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class MaintenanceHistory implements Serializable {
    private int id;
    private String title;
    private String description;
    private String status;
    private String submittedBy;
    private String reportDate;
}

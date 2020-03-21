package com.sausage.app.domain.housing.report;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ReportRequest {
    private String title;
    private String description;
    private int userID;
}

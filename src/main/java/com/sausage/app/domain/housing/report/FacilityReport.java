package com.sausage.app.domain.housing.report;

import lombok.*;

import java.io.Serializable;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class FacilityReport implements Serializable {
    private String title;
    private String description;
}

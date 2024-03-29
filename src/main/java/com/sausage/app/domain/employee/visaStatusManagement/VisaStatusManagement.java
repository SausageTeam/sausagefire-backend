package com.sausage.app.domain.employee.visaStatusManagement;

import lombok.*;

import java.io.File;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class VisaStatusManagement {

    Boolean ifNeedDownload;

    Integer status;

    String comments;

    String emptyForm;

    String sampleForm;

}

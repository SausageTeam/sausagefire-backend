package com.sausage.app.domain.visaStatusManagement;

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

    File emptyForm;

    File sampleForm;

}

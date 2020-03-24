package com.sausage.app.domain.hr.visaStatusManagement;

import lombok.*;

import java.io.File;
import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class VisaStatusRecord {

    String firstName;

    String middleName;

    String lastName;

    String workAuthorization;

    String expirationDate;

    int dayLeft;

    String visaStatus;

    String visaStartDate;

    String visaEndDate;

    List<String> documentTitleList;

    List<File> documentReceivedList;

    String fileName;

}

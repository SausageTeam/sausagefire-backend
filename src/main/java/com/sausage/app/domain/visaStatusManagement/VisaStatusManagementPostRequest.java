package com.sausage.app.domain.visaStatusManagement;

import lombok.*;

import java.io.File;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class VisaStatusManagementPostRequest {

    File file;

}

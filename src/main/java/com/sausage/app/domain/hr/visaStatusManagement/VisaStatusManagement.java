package com.sausage.app.domain.hr.visaStatusManagement;

import lombok.*;

import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class VisaStatusManagement {

    List<VisaStatusRecord> visaStatusRecordList;

}

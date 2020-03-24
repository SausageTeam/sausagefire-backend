package com.sausage.app.domain.profile.profileEmployment;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProfileEmployment {

    String title;

    String startDate;

    String endDate;

    String visaType;

    String visaStartDate;

    String visaEndDate;

}

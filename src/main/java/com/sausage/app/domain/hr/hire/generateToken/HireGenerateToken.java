package com.sausage.app.domain.hr.hire.generateToken;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class HireGenerateToken {

    String email;

    String title;

    String startDate;

    String endDate;

}

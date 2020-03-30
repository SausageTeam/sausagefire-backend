package com.sausage.app.domain.hr.hire.applicationReview;

import lombok.*;

import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class HireApplicationReview {

    List<Application> applicationList;

}

package com.sausage.app.domain.hr.hire.applicationReview;

import com.sausage.app.domain.common.GenericResponse;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class HireApplicationReviewGetResponse extends GenericResponse {

    HireApplicationReview hireApplicationReview;

}

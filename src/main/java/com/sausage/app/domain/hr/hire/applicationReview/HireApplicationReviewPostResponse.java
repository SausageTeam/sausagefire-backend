package com.sausage.app.domain.hr.hire.applicationReview;

import com.sausage.app.domain.common.GenericResponse;
import com.sausage.app.domain.common.ServiceStatus;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class HireApplicationReviewPostResponse extends GenericResponse {

    ServiceStatus serviceStatus;

}

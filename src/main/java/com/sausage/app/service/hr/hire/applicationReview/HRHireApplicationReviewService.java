package com.sausage.app.service.hr.hire.applicationReview;

import com.sausage.app.domain.hr.hire.applicationReview.ApplicationResult;
import com.sausage.app.domain.hr.hire.applicationReview.HireApplicationReview;

public interface HRHireApplicationReviewService {

    HireApplicationReview getHireApplicationReview();

    void setHireApplicationReview(ApplicationResult applicationResult);

}

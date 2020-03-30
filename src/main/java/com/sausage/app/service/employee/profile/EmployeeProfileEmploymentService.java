package com.sausage.app.service.employee.profile;

import com.sausage.app.domain.employee.profile.profileEmployment.ProfileEmployment;

public interface EmployeeProfileEmploymentService {

    ProfileEmployment getProfileEmployment(int userId);

    void setProfileEmployment(int userId, ProfileEmployment profileEmployment);

}

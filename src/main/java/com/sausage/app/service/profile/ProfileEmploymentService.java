package com.sausage.app.service.profile;

import com.sausage.app.domain.profile.profileEmployment.ProfileEmployment;

public interface ProfileEmploymentService {

    ProfileEmployment getProfileEmployment(int userId);

    void setProfileEmployment(int userId, ProfileEmployment profileEmployment);

}

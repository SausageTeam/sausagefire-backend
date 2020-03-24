package com.sausage.app.service.employee.profile;

import com.sausage.app.domain.employee.profile.profileName.ProfileName;

public interface EmployeeProfileNameService {

    ProfileName getProfileName(int userId);

    void setProfileName(int userId, ProfileName profileName);

}

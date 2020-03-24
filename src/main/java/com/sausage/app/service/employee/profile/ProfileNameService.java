package com.sausage.app.service.profile;

import com.sausage.app.domain.profile.profileName.ProfileName;

public interface ProfileNameService {

    ProfileName getProfileName(int userId);

    void setProfileName(int userId, ProfileName profileName);

}

package com.sausage.app.service.profile;

import com.sausage.app.domain.profile.profileContact.ProfileContact;

public interface ProfileContactService {

    ProfileContact getProfileContact(int userId);

    void setProfileContact(int userId, ProfileContact profileContact);

}

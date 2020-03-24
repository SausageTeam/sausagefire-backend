package com.sausage.app.service.employee.profile;

import com.sausage.app.domain.employee.profile.profileContact.ProfileContact;

public interface ProfileContactService {

    ProfileContact getProfileContact(int userId);

    void setProfileContact(int userId, ProfileContact profileContact);

}

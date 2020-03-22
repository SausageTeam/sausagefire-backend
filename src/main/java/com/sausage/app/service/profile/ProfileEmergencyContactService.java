package com.sausage.app.service.profile;

import com.sausage.app.domain.profile.profileEmergencyContact.ProfileEmergencyContact;

public interface ProfileEmergencyContactService {

    ProfileEmergencyContact getProfileEmergencyContact(int userId);

    void setProfileEmergencyContact(int userId, ProfileEmergencyContact profileEmergencyContact);

}

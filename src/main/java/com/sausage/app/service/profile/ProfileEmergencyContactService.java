package com.sausage.app.service.employee.profile;

import com.sausage.app.domain.employee.profile.profileEmergencyContact.ProfileEmergencyContact;

public interface ProfileEmergencyContactService {

    ProfileEmergencyContact getProfileEmergencyContact(int userId);

    void setProfileEmergencyContact(int userId, ProfileEmergencyContact profileEmergencyContact);

}

package com.sausage.app.service.employee.profile;

import com.sausage.app.domain.employee.profile.profileContact.ProfileContact;

public interface EmployeeProfileContactService {

    ProfileContact getProfileContact(int userId);

    void setProfileContact(int userId, ProfileContact profileContact);

}

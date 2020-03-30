package com.sausage.app.service.employee.profile;

import com.sausage.app.domain.employee.profile.profileAddress.ProfileAddress;

public interface EmployeeProfileAddressService {

    ProfileAddress getProfileAddress(int userId);

    void setProfileAddress(int userId, ProfileAddress profileAddress);

}

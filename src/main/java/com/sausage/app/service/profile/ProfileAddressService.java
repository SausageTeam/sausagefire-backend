package com.sausage.app.service.profile;

import com.sausage.app.domain.profile.profileAddress.ProfileAddress;

public interface ProfileAddressService {

    ProfileAddress getProfileAddress(int userId);

    void setProfileAddress(int userId, ProfileAddress profileAddress);

}

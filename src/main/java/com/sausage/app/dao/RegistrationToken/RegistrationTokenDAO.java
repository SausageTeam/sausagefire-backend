package com.sausage.app.dao.RegistrationToken;

import com.sausage.app.entity.RegistrationToken;

public interface RegistrationTokenDAO {

    RegistrationToken getRegistrationTokenByEmail(String email);

    void setRegistrationToken(RegistrationToken registrationToken);

}

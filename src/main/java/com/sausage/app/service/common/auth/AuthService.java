package com.sausage.app.service.common.auth;

import com.sausage.app.domain.common.auth.Auth;

public interface AuthService {

    Auth getAuth(int userId);

}

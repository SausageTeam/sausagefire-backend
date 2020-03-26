package com.sausage.app.service.hr.hire.generateToken;

import com.sausage.app.domain.hr.hire.generateToken.HireGenerateToken;

public interface HRHireGenerateTokenService {

    boolean setHireGenerateToken(int userId, HireGenerateToken hireGenerateToken);

}

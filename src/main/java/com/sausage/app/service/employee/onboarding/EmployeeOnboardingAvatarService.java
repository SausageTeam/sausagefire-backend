package com.sausage.app.service.employee.onboarding;

import com.sausage.app.domain.employee.onboarding.onboardingAvatar.OnboardingAvatar;
import org.springframework.web.multipart.MultipartFile;


public interface EmployeeOnboardingAvatarService {

    OnboardingAvatar getOnboardingAvatar(int userId);

    void setOnboardingAvatar(int userId, MultipartFile file);

}

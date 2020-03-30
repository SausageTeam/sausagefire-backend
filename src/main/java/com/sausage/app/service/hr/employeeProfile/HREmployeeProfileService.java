package com.sausage.app.service.hr.employeeProfile;

import com.sausage.app.domain.hr.employeeProfile.EmployeeProfile;
import com.sausage.app.domain.hr.employeeProfile.EmployeeRecord;

public interface HREmployeeProfileService {

    EmployeeProfile getEmployeeProfile();

    void setEmployeeProfile();
}

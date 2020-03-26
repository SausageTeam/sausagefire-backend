package com.sausage.app.service.employee.visaStatusManagement;

import com.sausage.app.domain.employee.visaStatusManagement.VisaStatusManagement;

import java.io.File;

public interface EmployeeVisaStatusManagementService {

    VisaStatusManagement getVisaStatusManagement(int userId);

    void setVisaStatusManagement(int userId, File file);

}

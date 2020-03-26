package com.sausage.app.service.hr.visaStatusManagement;

import com.sausage.app.domain.hr.visaStatusManagement.VisaStatusManagement;

import java.io.File;

public interface HRVisaStatusManagementService {

    VisaStatusManagement getVisaStatusManagement();

    void setVisaStatusManagement(int employeeId);

}

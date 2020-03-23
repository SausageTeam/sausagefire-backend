package com.sausage.app.service.visaStatusManagement;

import com.sausage.app.domain.visaStatusManagement.VisaStatusManagement;

import java.io.File;

public interface VisaStatusManagementService {

    VisaStatusManagement getVisaStatusManagement(int userId);

    void setVisaStatusManagement(int userId, File file);

}

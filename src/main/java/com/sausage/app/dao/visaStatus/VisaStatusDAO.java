package com.sausage.app.dao.visaStatus;

import com.sausage.app.entity.VisaStatus;

public interface VisaStatusDAO {

    VisaStatus getVisaStatusById(int id);

    VisaStatus setVisaStatus(VisaStatus visaStatus);

    VisaStatus setOtherVisaStatus(String visaType);

}

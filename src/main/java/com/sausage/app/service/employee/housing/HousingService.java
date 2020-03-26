package com.sausage.app.service.employee.housing;

import com.sausage.app.domain.housing.housingInfo.HousingInfo;

public interface HousingService {
    HousingInfo getHousingInfo(int userID);
}

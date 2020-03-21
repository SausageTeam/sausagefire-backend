package com.sausage.app.service.employee.housing;

import com.sausage.app.domain.housing.housingInfo.HousingInfo;
import com.sausage.app.domain.housing.housingInfo.HousingInfoRequest;

public interface HousingService {
    HousingInfo getHousingInfo(HousingInfoRequest housingInfoRequest);
}

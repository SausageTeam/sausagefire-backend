package com.sausage.app.service.employee.housing;

import com.sausage.app.domain.housing.housesDetails.AllHousesDetails;
import com.sausage.app.domain.housing.housesDetails.EmployeeInfo;

import java.util.List;

public interface AllHousesDetailsService {
    List<AllHousesDetails> getAllHousesDetailList();
    List<EmployeeInfo> getEmployeeInfoList();
}

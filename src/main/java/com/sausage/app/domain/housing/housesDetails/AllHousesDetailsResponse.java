package com.sausage.app.domain.housing.housesDetails;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.sausage.app.domain.common.ServiceStatus;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class AllHousesDetailsResponse {
    private ServiceStatus serviceStatus;
    private List<AllHousesDetails> allHousesDetailsList;
    private List<EmployeeInfo> employeeInfoList;
}

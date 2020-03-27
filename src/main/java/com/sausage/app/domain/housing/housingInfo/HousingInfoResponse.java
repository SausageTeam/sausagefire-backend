package com.sausage.app.domain.housing.housingInfo;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.sausage.app.domain.common.ServiceStatus;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class HousingInfoResponse {
    private ServiceStatus serviceStatus;
    private HousingInfo housingInfo;
}

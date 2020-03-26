package com.sausage.app.domain.housing.maintenanceHistory;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.sausage.app.domain.common.ServiceStatus;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class MaintenanceHistoryResponse {
    private ServiceStatus serviceStatus;
    private List<MaintenanceHistory> maintenanceHistoryList;
}

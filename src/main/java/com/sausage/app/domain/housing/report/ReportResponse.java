package com.sausage.app.domain.housing.report;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.sausage.app.domain.common.ServiceStatus;
import lombok.Getter;
import lombok.Setter;

import javax.xml.ws.Service;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ReportResponse {
    private ServiceStatus serviceStatus;
    private String redirectUrl;
}

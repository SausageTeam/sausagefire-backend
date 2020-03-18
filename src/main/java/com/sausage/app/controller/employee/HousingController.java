package com.sausage.app.controller.employee;

import com.sausage.app.domain.common.ServiceStatus;
import com.sausage.app.domain.house.HousingInfo;
import com.sausage.app.domain.house.HousingInfoResponse;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/employee/housing")
public class HousingController {

//    public HousingInfoResponse

    private void prepareResponse(HousingInfoResponse response, boolean success, String errorMessage) {
        response.setServiceStatus(new ServiceStatus(success ? "SUCCESS" : "FAILED", success, errorMessage));
    }
}

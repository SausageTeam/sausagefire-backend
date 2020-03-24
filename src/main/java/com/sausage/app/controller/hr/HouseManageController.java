package com.sausage.app.controller.hr;

import com.sausage.app.domain.common.ServiceStatus;
import com.sausage.app.domain.housing.housesDetails.AllHousesDetails;
import com.sausage.app.domain.housing.housesDetails.AllHousesDetailsResponse;
import com.sausage.app.domain.housing.housesDetails.EmployeeInfo;
import com.sausage.app.domain.housing.report.ReportResponse;
import com.sausage.app.service.employee.housing.AllHousesDetailsService;
import com.sausage.app.service.employee.housing.impl.AllHousesDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/hr/housing")
public class HouseManageController {

    private AllHousesDetailsService allHousesDetailsService;

    @Autowired
    public void setAllHousesDetailsService(AllHousesDetailsService allHousesDetailsService) {
        this.allHousesDetailsService = allHousesDetailsService;
    }

    @GetMapping("/allHousesDetails")
    public AllHousesDetailsResponse getAllHousesDetails() {
        AllHousesDetailsResponse response = new AllHousesDetailsResponse();
        List<AllHousesDetails> allHousesDetailsList = allHousesDetailsService.getAllHousesDetailList();
        List<EmployeeInfo> employeeInfoList = allHousesDetailsService.getEmployeeInfoList();
        response.setEmployeeInfoList(employeeInfoList);
        response.setAllHousesDetailsList(allHousesDetailsList);
        prepareFacilityReportResponse(response, true, "");
        return response;
    }

    private void prepareFacilityReportResponse(AllHousesDetailsResponse response, boolean success, String errorMessage) {
        response.setServiceStatus(new ServiceStatus(success ? "SUCCESS" : "FAILED", success, errorMessage));
    }
}

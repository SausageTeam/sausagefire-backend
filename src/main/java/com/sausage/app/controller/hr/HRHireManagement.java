package com.sausage.app.controller.hr;

import com.sausage.app.domain.common.GenericResponse;
import com.sausage.app.domain.common.ServiceStatus;
import com.sausage.app.domain.hr.hire.generateToken.HireGenerateToken;
import com.sausage.app.domain.hr.hire.generateToken.HireGenerateTokenGetResponse;
import com.sausage.app.domain.hr.hire.generateToken.HireGenerateTokenPostRequest;
import com.sausage.app.domain.hr.hire.generateToken.HireGenerateTokenPostResponse;
import com.sausage.app.service.hr.hire.applicationReview.HRHireApplicationReviewService;
import com.sausage.app.service.hr.hire.generateToken.HRHireGenerateTokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("hr/hire")
public class HRHireManagement {

    private HRHireGenerateTokenService hrHireGenerateTokenService;

    private HRHireApplicationReviewService hrHireApplicationReviewService;

    @Autowired
    public void setHrHireGenerateTokenService(HRHireGenerateTokenService hrHireGenerateTokenService) {
        this.hrHireGenerateTokenService = hrHireGenerateTokenService;
    }

    @Autowired
    public void setHrHireApplicationReviewService(HRHireApplicationReviewService hrHireApplicationReviewService) {
        this.hrHireApplicationReviewService = hrHireApplicationReviewService;
    }

    @GetMapping(value = "/generate-token")
    public @ResponseBody
    HireGenerateTokenGetResponse getHireGenerateToken(HttpServletRequest httpServletRequest) {
        HireGenerateTokenGetResponse hireGenerateTokenGetResponse = new HireGenerateTokenGetResponse();
        //        int userId = Integer.parseInt(JwtUtil.getSubject(httpServletRequest, Constant.JWT_TOKEN_COOKIE_NAME, Constant.SIGNING_KEY));
        int userId = 2;
        prepareResponse(hireGenerateTokenGetResponse, true, "");
        return hireGenerateTokenGetResponse;
    }

    @PostMapping(value = "/generate-token")
    public @ResponseBody
    HireGenerateTokenPostResponse postHireGenerateToken(@RequestBody HireGenerateTokenPostRequest hireGenerateTokenPostRequest) {
        HireGenerateTokenPostResponse hireGenerateTokenPostResponse = new HireGenerateTokenPostResponse();
        //        int userId = Integer.parseInt(JwtUtil.getSubject(httpServletRequest, Constant.JWT_TOKEN_COOKIE_NAME, Constant.SIGNING_KEY));
        int userId = 2;
        HireGenerateToken hireGenerateToken = hireGenerateTokenPostRequest.getHireGenerateToken();
        boolean success = hrHireGenerateTokenService.setHireGenerateToken(userId, hireGenerateToken);
        if (!success) {
            prepareResponse(hireGenerateTokenPostResponse, false, "Duplicate Email Address");
        } else {
            prepareResponse(hireGenerateTokenPostResponse, true, "");
        }
        return hireGenerateTokenPostResponse;
    }

    private void prepareResponse(GenericResponse response, boolean success, String errorMessage) {
        response.setServiceStatus(new ServiceStatus(success ? "SUCCESS" : "FAILED", success, errorMessage));
    }

}

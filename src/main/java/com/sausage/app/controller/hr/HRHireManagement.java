package com.sausage.app.controller.hr;

import com.sausage.app.domain.common.GenericResponse;
import com.sausage.app.domain.common.ServiceStatus;
import com.sausage.app.domain.hr.hire.applicationReview.*;
import com.sausage.app.domain.hr.hire.generateToken.HireGenerateToken;
import com.sausage.app.domain.hr.hire.generateToken.HireGenerateTokenGetResponse;
import com.sausage.app.domain.hr.hire.generateToken.HireGenerateTokenPostRequest;
import com.sausage.app.domain.hr.hire.generateToken.HireGenerateTokenPostResponse;
import com.sausage.app.security.util.JwtUtil;
import com.sausage.app.service.hr.hire.applicationReview.HRHireApplicationReviewService;
import com.sausage.app.service.hr.hire.generateToken.HRHireGenerateTokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

import java.util.List;

import static com.sausage.app.constant.Constant.JWT_TOKEN_COOKIE_NAME;
import static com.sausage.app.constant.Constant.SIGNING_KEY;

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
        String id = JwtUtil.getSubject(httpServletRequest, JWT_TOKEN_COOKIE_NAME, SIGNING_KEY);
        if (id == null) {
            prepareResponse(hireGenerateTokenGetResponse, "401", false, "User not Found");
        } else {
            int userId = Integer.parseInt(id);
            prepareResponse(hireGenerateTokenGetResponse, "200", true, "");
        }
        return hireGenerateTokenGetResponse;
    }

    @PostMapping(value = "/generate-token")
    public @ResponseBody
    HireGenerateTokenPostResponse postHireGenerateToken(HttpServletRequest httpServletRequest, @RequestBody HireGenerateTokenPostRequest hireGenerateTokenPostRequest) {
        HireGenerateTokenPostResponse hireGenerateTokenPostResponse = new HireGenerateTokenPostResponse();
        String id = JwtUtil.getSubject(httpServletRequest, JWT_TOKEN_COOKIE_NAME, SIGNING_KEY);
        if (id == null) {
            prepareResponse(hireGenerateTokenPostResponse, "401", false, "User not Found");
        } else {
            int userId = Integer.parseInt(id);
            HireGenerateToken hireGenerateToken = hireGenerateTokenPostRequest.getHireGenerateToken();
            boolean success = hrHireGenerateTokenService.setHireGenerateToken(userId, hireGenerateToken);
            if (!success) {
                prepareResponse(hireGenerateTokenPostResponse, "500", false, "Duplicate Email Address");
            } else {
                prepareResponse(hireGenerateTokenPostResponse, "200", true, "");
            }
        }
        return hireGenerateTokenPostResponse;
    }

    @GetMapping(value = "/application-review")
    public @ResponseBody
    HireApplicationReviewGetResponse getApplicationReview(HttpServletRequest httpServletRequest) {
        HireApplicationReviewGetResponse hireApplicationReviewGetResponse = new HireApplicationReviewGetResponse();
        String id = JwtUtil.getSubject(httpServletRequest, JWT_TOKEN_COOKIE_NAME, SIGNING_KEY);
        if (id == null) {
            prepareResponse(hireApplicationReviewGetResponse, "401", false, "User not Found");
        } else {
            HireApplicationReview hireApplicationReview = hrHireApplicationReviewService.getHireApplicationReview();
            if (hireApplicationReview == null) {
                prepareResponse(hireApplicationReviewGetResponse, "500", false, "Unexpected Error");
            } else {
                hireApplicationReviewGetResponse.setHireApplicationReview(hireApplicationReview);
                prepareResponse(hireApplicationReviewGetResponse, "200", true, "");
            }
        }
        return hireApplicationReviewGetResponse;
    }

    @PostMapping(value = "/application-review")
    public @ResponseBody
    HireApplicationReviewPostResponse postApplicationReview(HttpServletRequest httpServletRequest, @RequestBody HireApplicationReviewPostRequest hireApplicationReviewPostRequest) {
        HireApplicationReviewPostResponse hireApplicationReviewPostResponse = new HireApplicationReviewPostResponse();
        String id = JwtUtil.getSubject(httpServletRequest, JWT_TOKEN_COOKIE_NAME, SIGNING_KEY);
        if (id == null) {
            prepareResponse(hireApplicationReviewPostResponse, "401", false, "User not Found");
        } else {
            ApplicationResult applicationResult = hireApplicationReviewPostRequest.getApplicationResult();
            hrHireApplicationReviewService.setHireApplicationReview(applicationResult);
            prepareResponse(hireApplicationReviewPostResponse, "200", true, "");
        }
        return hireApplicationReviewPostResponse;
    }

    private void prepareResponse(GenericResponse response, String statusCode, boolean success, String errorMessage) {
        response.setServiceStatus(new ServiceStatus(statusCode, success, errorMessage));
    }

}

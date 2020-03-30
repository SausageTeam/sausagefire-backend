package com.sausage.app.controller.hr;

import com.sausage.app.domain.hr.hire.applicationReview.*;
import com.sausage.app.domain.hr.hire.generateToken.HireGenerateToken;
import com.sausage.app.domain.hr.hire.generateToken.HireGenerateTokenGetResponse;
import com.sausage.app.domain.hr.hire.generateToken.HireGenerateTokenPostRequest;
import com.sausage.app.domain.hr.hire.generateToken.HireGenerateTokenPostResponse;
import com.sausage.app.security.util.JwtUtil;
import com.sausage.app.service.hr.hire.applicationReview.HRHireApplicationReviewService;
import com.sausage.app.service.hr.hire.generateToken.HRHireGenerateTokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

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
    public ResponseEntity<Object> getHireGenerateToken(HttpServletRequest httpServletRequest) {
        ResponseEntity<Object> responseEntity;

        HireGenerateTokenGetResponse hireGenerateTokenGetResponse = new HireGenerateTokenGetResponse();
        String id = JwtUtil.getSubject(httpServletRequest, JWT_TOKEN_COOKIE_NAME, SIGNING_KEY);
        if (id == null) {
            responseEntity = ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body("Sorry, you are not authorized 😅");
        } else {
            int userId = Integer.parseInt(id);
            responseEntity = ResponseEntity.ok()
                    .body(hireGenerateTokenGetResponse);
        }
        return responseEntity;
    }

    @PostMapping(value = "/generate-token")
    public ResponseEntity<Object> postHireGenerateToken(HttpServletRequest httpServletRequest, @RequestBody HireGenerateTokenPostRequest hireGenerateTokenPostRequest) {
        ResponseEntity<Object> responseEntity;

        HireGenerateTokenPostResponse hireGenerateTokenPostResponse = new HireGenerateTokenPostResponse();
        String id = JwtUtil.getSubject(httpServletRequest, JWT_TOKEN_COOKIE_NAME, SIGNING_KEY);
        if (id == null) {
            responseEntity = ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body("Sorry, you are not authorized 😅");
        } else {
            int userId = Integer.parseInt(id);
            HireGenerateToken hireGenerateToken = hireGenerateTokenPostRequest.getHireGenerateToken();
            boolean success = hrHireGenerateTokenService.setHireGenerateToken(userId, hireGenerateToken);
            if (!success) {
                responseEntity = ResponseEntity.status(HttpStatus.CONFLICT)
                        .body("Sorry, email is already used 😅");
            } else {
                responseEntity = ResponseEntity.ok()
                        .body(hireGenerateTokenPostResponse);
            }
        }
        return responseEntity;
    }

    @GetMapping(value = "/application-review")
    public ResponseEntity<Object> getApplicationReview(HttpServletRequest httpServletRequest) {
        ResponseEntity<Object> responseEntity;

        HireApplicationReviewGetResponse hireApplicationReviewGetResponse = new HireApplicationReviewGetResponse();
        String id = JwtUtil.getSubject(httpServletRequest, JWT_TOKEN_COOKIE_NAME, SIGNING_KEY);
        if (id == null) {
            responseEntity = ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body("Sorry, you are not authorized 😅");
        } else {
            HireApplicationReview hireApplicationReview = hrHireApplicationReviewService.getHireApplicationReview();
            if (hireApplicationReview == null) {
                responseEntity = ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body("Sorry, no data found 😅");
            } else {
                hireApplicationReviewGetResponse.setHireApplicationReview(hireApplicationReview);
                responseEntity = ResponseEntity.ok()
                        .body(hireApplicationReviewGetResponse);
            }
        }
        return responseEntity;
    }

    @PostMapping(value = "/application-review")
    public ResponseEntity<Object> postApplicationReview(HttpServletRequest httpServletRequest, @RequestBody HireApplicationReviewPostRequest hireApplicationReviewPostRequest) {
        ResponseEntity<Object> responseEntity;

        HireApplicationReviewPostResponse hireApplicationReviewPostResponse = new HireApplicationReviewPostResponse();
        String id = JwtUtil.getSubject(httpServletRequest, JWT_TOKEN_COOKIE_NAME, SIGNING_KEY);
        if (id == null) {
            responseEntity = ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body("Sorry, you are not authorized 😅");
        } else {
            ApplicationResult applicationResult = hireApplicationReviewPostRequest.getApplicationResult();
            hrHireApplicationReviewService.setHireApplicationReview(applicationResult);
            responseEntity = ResponseEntity.ok()
                    .body(hireApplicationReviewPostResponse);
        }
        return responseEntity;
    }

}

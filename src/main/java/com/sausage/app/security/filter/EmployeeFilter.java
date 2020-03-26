package com.sausage.app.security.filter;

import com.sausage.app.constant.Constant;
import com.sausage.app.dao.role.enums.RoleEnums;
import com.sausage.app.security.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class EmployeeFilter {

//    private UserRoleService userRoleService;
//
//    @Autowired
//    public void setUserRoleService(UserRoleService userRoleService) {
//        this.userRoleService = userRoleService;
//    }
//
//    @Override
//    protected void doFilterInternal(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, FilterChain filterChain) throws ServletException, IOException {
//        String userId = JwtUtil.getSubject(httpServletRequest, Constant.JWT_TOKEN_COOKIE_NAME, Constant.SIGNING_KEY);
//        if(userId == null){
//            String authService = this.getFilterConfig().getInitParameter("services.auth");
//            httpServletResponse.sendRedirect(authService + "?redirect=" + httpServletRequest.getRequestURL());
//        } else{
//            if (userRoleService.getUserRoleId(Integer.parseInt(userId)) == RoleEnums.EMPLOYEE.getValue()){
//                filterChain.doFilter(httpServletRequest, httpServletResponse);
//            }
//            else {
//                String authService = this.getFilterConfig().getInitParameter("services.auth");
//                httpServletResponse.sendRedirect(authService + "?redirect=" + httpServletRequest.getRequestURL());
//            }
//        }
//    }
}

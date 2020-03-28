package com.sausage.app.security.filter;

import com.sausage.app.constant.Constant;
import com.sausage.app.security.util.JwtUtil;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class AuthFilter extends OncePerRequestFilter {

    @Override
    protected void doFilterInternal(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, FilterChain filterChain) throws ServletException, IOException {
        String userId = JwtUtil.getSubject(httpServletRequest, Constant.JWT_TOKEN_COOKIE_NAME, Constant.SIGNING_KEY);
        System.out.println("USER ID: " + userId);
        if (userId == null) {
            System.out.println("NULL, REDIRECT");
            String authService = this.getFilterConfig().getInitParameter("services.auth");
            System.out.println("FROM: " + authService);
            System.out.println("REDIRECT: " + httpServletRequest.getRequestURL());
            httpServletResponse.sendRedirect(authService + "?redirect=" + httpServletRequest.getRequestURL());
        } else {
            System.out.println("NOT NULL");
            filterChain.doFilter(httpServletRequest, httpServletResponse);
        }
    }

}

package com.sausage.app.config;


import com.sausage.app.security.filter.EmployeeFilter;
import com.sausage.app.security.filter.JwtFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Collections;

@Configuration
public class FilterConfig {

//    @Value("${services.auth}")
//    private String authService;
//
//    private EmployeeFilter employeeFilter;
//
//    @Autowired
//    public void setEmployeeFilter(EmployeeFilter employeeFilter) {
//        this.employeeFilter = employeeFilter;
//    }
//
//    @Bean
//    public FilterRegistrationBean<JwtFilter> jwtFilter() {
//        final FilterRegistrationBean<JwtFilter> registrationBean = new FilterRegistrationBean<>();
//        registrationBean.setFilter(new JwtFilter());
//        registrationBean.setInitParameters(Collections.singletonMap("services.auth", authService));
//        registrationBean.addUrlPatterns("/*");
//
//        return registrationBean;
//    }
//
//    @Bean
//    public FilterRegistrationBean<EmployeeFilter> employeeFilter() {
//        final FilterRegistrationBean<EmployeeFilter> registrationBean = new FilterRegistrationBean<>();
//        registrationBean.setFilter(employeeFilter);
//        registrationBean.setInitParameters(Collections.singletonMap("services.auth", authService));
//        registrationBean.addUrlPatterns("/Employee/*");
//        return registrationBean;
//    }

//    @Bean
//    public FilterRegistrationBean<JwtFilter> hrFilter() {
//        final FilterRegistrationBean<JwtFilter> registrationBean = new FilterRegistrationBean<>();
//        registrationBean.setFilter(new JwtFilter());
//        registrationBean.setInitParameters(Collections.singletonMap("services.auth", authService));
//        registrationBean.addUrlPatterns("/hr/*");
//
//        return registrationBean;
//    }
}

package com.sausage.app.config;


import com.sausage.app.security.filter.AuthFilter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Collections;

@Configuration
public class FilterConfig {

    @Value("${services.auth}")
    private String authService;

//    @Bean
//    public FilterRegistrationBean<AuthFilter> authFilterConfig() {
//        final FilterRegistrationBean<AuthFilter> registrationBean = new FilterRegistrationBean<>();
//        registrationBean.setFilter(new AuthFilter());
//        registrationBean.setInitParameters(Collections.singletonMap("services.auth", authService));
//        registrationBean.addUrlPatterns("/*");
//        return registrationBean;
//    }

}

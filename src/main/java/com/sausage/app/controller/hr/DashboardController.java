package com.sausage.app.controller.hr;

import com.sausage.app.domain.hr.home.HomeGetResponse;
import com.sausage.app.service.hr.home.HomeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/hr")
public class HomeController {

    private HomeService homeService;

    @Autowired
    public void setHomeService(HomeService homeService) {
        this.homeService = homeService;
    }

    @GetMapping(value = "/home")
    public @ResponseBody
    HomeGetResponse getHome(HttpServletRequest httpServletRequest){
        
    }
}

package com.sausage.app.service.onboarding.impl;

import com.sausage.app.dao.Person.PersonDAO;
import com.sausage.app.security.util.JwtUtil;
import com.sausage.app.service.onboarding.OnboardingPersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpServletRequest;

@Service
public class OnboardingPersonServiceImpl implements OnboardingPersonService {

    private PersonDAO personDAO;

    @Autowired
    public void setPersonDAO(PersonDAO personDAO) {
        this.personDAO = personDAO;
    }

}

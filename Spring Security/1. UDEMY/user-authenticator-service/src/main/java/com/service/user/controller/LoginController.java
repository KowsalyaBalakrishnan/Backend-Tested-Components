package com.service.user.controller;

import com.service.user.service.SecurityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class LoginController {

    /*@Autowired
    private SecurityService securityService;*/

    @GetMapping("/")
    public String loadLoginHtmlPage() {
        return "login";
    }

    @PostMapping("/login")
    public String userLogin(String email, String password) {
        /*boolean isValidUser = securityService.authenticateUserCredentials(email, password);
        if (isValidUser)
            return "Hello!";
        return "Go Back";*/
        return "exit";
    }

}

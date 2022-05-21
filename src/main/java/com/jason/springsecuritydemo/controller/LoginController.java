package com.jason.springsecuritydemo.controller;

import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @Description: com.jason.springsecuritydemo.controller
 */

@Controller
public class LoginController {
    @Secured("ROLE_admin")
    @RequestMapping("/main")
    public String showMainPage() {
        return "main";
    }

    @RequestMapping("/login")
    public String showLoginPage() {
        return "login";
    }

    @RequestMapping("fail")
    public String fail() {
        return "loginfail";
    }

    @RequestMapping("/accessdenied")
    public String accessDenied() {
        return "accessdenied";
    }
}

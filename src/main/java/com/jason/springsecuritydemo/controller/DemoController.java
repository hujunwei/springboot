package com.jason.springsecuritydemo.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Description: com.jason.springsecuritydemo.controller
 */


@RestController
public class DemoController {
    @RequestMapping("/demo")
    public String demo() {
        return "demo";
    }
}

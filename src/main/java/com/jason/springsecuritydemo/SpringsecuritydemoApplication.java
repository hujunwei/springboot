package com.jason.springsecuritydemo;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;

@SpringBootApplication
@MapperScan(basePackages = "com.jason.springsecuritydemo.mapper")
@EnableGlobalMethodSecurity(securedEnabled = true)
public class SpringsecuritydemoApplication {
    public static void main(String[] args) {
        SpringApplication.run(SpringsecuritydemoApplication.class, args);
    }
}

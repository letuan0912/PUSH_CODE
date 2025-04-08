package com.springboot.dev_spring_boot_demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LoginController {
    @GetMapping("/login")
    public String login(){
        return "login";
    }

    @GetMapping("/system")
    public String system(){
        return "system";
    }

    @GetMapping("/error")
    public String error(){
        return "error";
    }
}

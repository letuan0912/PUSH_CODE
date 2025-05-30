package com.gdu_springboot.spring_boot_demo.rest;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class HelloRestController {
    @GetMapping("/hello")
    public String hello() {
        return "Hello World";
    }
}

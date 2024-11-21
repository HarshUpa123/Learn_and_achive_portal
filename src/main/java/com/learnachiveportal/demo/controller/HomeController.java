package com.learnachiveportal.demo.controller;

import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
@RequestMapping("/auth")
public class HomeController {

	org.slf4j.Logger logger = LoggerFactory.getLogger(HomeController.class);

    @GetMapping("/test")
    public String test() {
        this.logger.warn("This is working message");
        return "Testing message";
    }
}

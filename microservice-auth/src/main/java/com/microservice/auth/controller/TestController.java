package com.microservice.auth.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/prueba")
@RequiredArgsConstructor
public class TestController {

    @PostMapping("/text")
    public String login() {
        return "entraste en protegido";
    }

}

package com.digitalworlds.api1.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class Api1Controller {

    @GetMapping("/api1")
    public String greeting() {
        return "Grupo 1";
    }

    @GetMapping("/greeting")
    public String greetingOtherGroups() {
        return "Otros grupos";
    }
}

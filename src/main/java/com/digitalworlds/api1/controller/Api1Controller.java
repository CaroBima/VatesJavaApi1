package com.digitalworlds.api1.controller;

import ch.qos.logback.classic.spi.TurboFilterList;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

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

    @GetMapping("/data")
    public String getExternalData(){
        RestTemplate client = new RestTemplate();
        String response = client.getForObject("https://www.google.com.ar", String.class);

        return response;
    }
}

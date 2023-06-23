package com.digitalworlds.api1.controller;

import com.digitalworlds.api1.model.Clima;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class ClimaController {

    @GetMapping("/clima")
    public Clima getExternalWeather( @RequestParam String ciudad) {


        return null; //retornar clima
    }
}

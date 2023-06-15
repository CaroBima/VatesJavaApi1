package com.digitalworlds.api1.controller;

import com.digitalworlds.api1.model.Programa;
import com.digitalworlds.api1.dto.ProgramaDTO;
import com.digitalworlds.api1.model.Programas;
import com.digitalworlds.api1.services.CulturaService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

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


    @GetMapping("/programas")
    public  List<ProgramaDTO> getExternalPrograms() {


        return null;


    }
}

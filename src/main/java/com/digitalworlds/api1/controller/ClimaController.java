package com.digitalworlds.api1.controller;

import com.digitalworlds.api1.dto.ClimaDTO;
import com.digitalworlds.api1.model.Clima;
import com.digitalworlds.api1.services.ClimaService;
import com.digitalworlds.api1.services.CulturaService;
import com.digitalworlds.api1.services.IClimaService;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class ClimaController {
    private IClimaService climaService;

    public ClimaController(IClimaService climaService){
        this.climaService = climaService;
    }
    @GetMapping("/clima")
    public ClimaDTO getExternalWeather(@RequestParam String ciudad) throws JsonProcessingException {
        ClimaDTO climaDto = climaService.getWeatherData(ciudad);


        return climaDto; //retornar clima
    }
}

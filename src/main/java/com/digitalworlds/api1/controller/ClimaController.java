package com.digitalworlds.api1.controller;

import com.digitalworlds.api1.dto.ClimaDTO;
import com.digitalworlds.api1.services.ClimaService;
import com.digitalworlds.api1.services.IClimaService;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class ClimaController {
    private IClimaService climaService;

    public ClimaController(IClimaService climaService){
        this.climaService = climaService;
    }

    //Devuelve el clima de la ciudad pasada por parámetro
    @GetMapping("/clima")
    public ResponseEntity<ClimaDTO> getExternalWeather(@RequestParam String ciudad) throws JsonProcessingException {
        ClimaDTO climaDto = climaService.getWeatherData(ciudad);
        return ResponseEntity.ok(climaDto); //retornar clima
    }

    @GetMapping("/clima/{id}")
    public ResponseEntity<ClimaDTO> getWeatherData(@PathVariable Long id){
        ClimaDTO climaDto = climaService.getWeatherDataById(id);
        return ResponseEntity.ok(climaDto);
    }

    @GetMapping("/clima/data")
    public ResponseEntity<ClimaDTO> getById (@RequestParam (defaultValue="1" , name="id") Long id){
        ClimaDTO climaDto = climaService.getWeatherDataById(id);
        return ResponseEntity.ok(climaDto);
    }

    @PostMapping("/clima/savedata")
    public ResponseEntity saveClima(@RequestBody ClimaDTO climadto){
        climaService.saveClima(climadto);
        return new ResponseEntity(HttpStatus.CREATED);
    }

    @DeleteMapping("/clima/borrardata")
    public ResponseEntity deleteWeatherData(@RequestParam Long id){
        climaService.deleteWeather(id);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

    @PutMapping("/clima/putdata")
    public ResponseEntity putWeatherData(@RequestParam Long id){
        return new ResponseEntity(HttpStatus.OK);
    }

    public void setClimaService(ClimaService climaService) {
        this.climaService = climaService;
    }
}

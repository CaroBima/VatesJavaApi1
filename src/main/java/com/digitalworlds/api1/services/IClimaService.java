package com.digitalworlds.api1.services;

import com.digitalworlds.api1.dto.ClimaDTO;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.http.ResponseEntity;

public interface IClimaService {
    public ClimaDTO getWeatherData(String city) throws JsonProcessingException;
    public ClimaDTO getWeatherDataById(Long id);
    public void saveClima(ClimaDTO climaDto);
}

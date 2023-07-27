package com.digitalworlds.api1.services;

import com.digitalworlds.api1.dto.ClimaDTO;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.http.ResponseEntity;

public interface IClimaService {
    ClimaDTO getWeatherData(String city);
    public ClimaDTO getWeatherDataById(Long id);
    public void saveClima(ClimaDTO climaDto);
    public void deleteWeather(Long id);
}

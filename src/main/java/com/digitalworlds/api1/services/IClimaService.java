package com.digitalworlds.api1.services;

import com.digitalworlds.api1.dto.ClimaDTO;
import com.fasterxml.jackson.core.JsonProcessingException;

public interface IClimaService {
    public ClimaDTO getWeatherData(String city) throws JsonProcessingException;
    public ClimaDTO getWeatherDataById(Long id);
}

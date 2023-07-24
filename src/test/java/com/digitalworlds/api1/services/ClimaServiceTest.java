package com.digitalworlds.api1.services;

import com.digitalworlds.api1.controller.ClimaController;
import com.digitalworlds.api1.dto.ClimaDTO;


import com.digitalworlds.api1.model.Clima;
import com.digitalworlds.api1.repository.IClimaRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.web.client.RestTemplate;

import static org.hamcrest.Matchers.any;
import static org.mockito.Mockito.when;


@SpringBootTest
//@WebMvcTest(ClimaService.class)
class ClimaServiceTest {

    @InjectMocks
    private ClimaService climaService;

    @Mock
    private IClimaRepository climaRepository;

    @Mock
    RestTemplate client;

    @Mock
    ModelMapper modelMapper;

    @Mock
    ObjectMapper objectMapper;

    @Mock
    Clima clima;
    public static ClimaDTO climaDto = ClimaDTO.builder()
            .name("Córdoba")
            .country("Argentine")
            .region("Córdoba")
            .tempC(14.0)
            .lastUpdated("2023-07-10 15:45")
            .humidity(94)
            .feelslikeC(13.7)
            .windKph(6.1)
            .build();

    @Value("${api.weather.url}")
    private String weatherUrl;

    @Value("${weather.api.key}")
    private String weatherKey;

    private static final String mockResponse = "{ \"name\": \"Córdoba\", \"country\": \"Argentine\", \"region\": \"Córdoba\", \"tempC\": 14.0, \"lastUpdated\": \"2023-07-10 15:45\", \"humidity\": 94, \"feelslikeC\": 13.7 , \"windKph\": 6.1 }";

    @Test
    @DisplayName("Cuando busco el clima por ciudad me devuelve los datos del clima y guarda la consulta en la BBDD.")
    void getWeatherData_givenValidCity_shouldSuccess() throws JsonProcessingException {
        String ciudad = "Córdoba";
        String url = weatherUrl+"/current.json?key=" + weatherKey +"&q="+ciudad; //url de la api externa

        when(client.getForObject(url, String.class)).thenReturn(mockResponse); //consulta en la api externa y trae la info de la api externa
        when(objectMapper.readValue(mockResponse, Clima.class)).thenReturn(clima); //mapea la info a una clase clima


        /*
        *

          mapea la info a una clase climaEntity y le agrega fecha y hora actual
          guarda el climaEntity en el repo
        * */

    }

    @Test
    void saveClima() {
    }

    @Test
    void deleteWeather() {
    }
}
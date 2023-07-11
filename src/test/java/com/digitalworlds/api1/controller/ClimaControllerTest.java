package com.digitalworlds.api1.controller;


import com.digitalworlds.api1.services.ClimaService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;



@WebMvcTest(ClimaController.class)
class ClimaControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ClimaService climaService;

    @Test
    void getExternalWeather_givenValidCity_shouldSuccess() throws Exception {
        String ciudad = "Córdoba"; // Ciudad válida para la prueba

        // Configurar el mock para que llame al método real del servicio
        when(climaService.getWeatherData(ciudad)).thenCallRealMethod();

        this.mockMvc.perform(get("/api/clima").param("ciudad", ciudad))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().json("{ \"name\": \"Córdoba\", \"country\": \"Argentine\", \"region\": \"Córdoba\", \"tempC\": 14.0, \"lastUpdated\": \"2023-07-10 15:45\", \"humidity\": 94, \"feelslikeC\": 13.7 , \"windKph\": 6.1 }"));
    }
}

package com.digitalworlds.api1.services;

import com.digitalworlds.api1.configuration.ConfigurationApi1;
import com.digitalworlds.api1.dto.ClimaDTO;
import com.digitalworlds.api1.model.Clima;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class ClimaService implements IClimaService{
    @Override
    public ClimaDTO getWeatherData(String city) throws JsonProcessingException {
        Clima clima;
        ClimaDTO climaDto = new ClimaDTO();
        ConfigurationApi1 config = new ConfigurationApi1();


        String url = "http://api.weatherapi.com/v1/current.json?key=c825c812079e41ca9b620745231206&q="+city;
        //String url = config.getWeatherUrl()+"/current.json?key="+config.getWeatherKey()+"&q="+city;

        RestTemplate client = new RestTemplate();

        String response = client.getForObject(url, String.class);

        ObjectMapper objectMapper = new ObjectMapper();

        clima = objectMapper.readValue(response, Clima.class);

        climaDto.setCiudad(clima.getLocation().name);
        climaDto.setPaís(clima.getLocation().country);
        climaDto.setHumedad(clima.getCurrent().humidity);
        climaDto.setTempCelsius(clima.getCurrent().tempC);
        climaDto.setSensacionTermica(clima.getCurrent().feelslikeC);
        climaDto.setVientoKmxH(clima.getCurrent().windKph);
        climaDto.setUltimaActualizacion(clima.getCurrent().lastUpdated);

        return climaDto;
    }
}
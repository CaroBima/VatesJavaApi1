package com.digitalworlds.api1.services;

import com.digitalworlds.api1.configuration.ConfigurationApi1;
import com.digitalworlds.api1.dto.ClimaDTO;
import com.digitalworlds.api1.entities.ClimaEntity;
import com.digitalworlds.api1.model.Clima;
import com.digitalworlds.api1.repository.IClimaRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Date;

@Service
public class ClimaService implements IClimaService{

    @Value("${api.weather.url}")
    private String weatherUrl;

    @Value("${weather.api.key}")
    private String weatherKey;

    @Autowired
    private IClimaRepository climaRepo;

    @Override
    public ClimaDTO getWeatherData(String city) throws JsonProcessingException {
        Clima clima;
        ClimaDTO climaDto = new ClimaDTO();
        ClimaEntity climaEntity = new ClimaEntity();
        ConfigurationApi1 config = new ConfigurationApi1();


        String url = weatherUrl+"/current.json?key=" + weatherKey +"&q="+city;
        //String url = config.getWeatherUrl()+"/current.json?key="+config.getWeatherKey()+"&q="+city;

        RestTemplate client = new RestTemplate();
        Date fechaHoraActual = new Date();

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
        //climaDto.setFechaHoraConsulta(fechaHoraActual); // no se pasa al front, lo dejo en el entity

        climaEntity.setCiudad(climaDto.getCiudad());
        climaEntity.setPais(climaDto.getPaís());
        climaEntity.setHumedad(climaDto.getHumedad());
        climaEntity.setTempCelsius(climaDto.getTempCelsius());
        climaEntity.setSensacionTermica(climaDto.getSensacionTermica());
        climaEntity.setVientoKmxH(climaDto.getVientoKmxH());
        climaEntity.setUltimaActualizacion(climaDto.getUltimaActualizacion());
        climaEntity.setFechaConsulta(fechaHoraActual);

        climaRepo.save(climaEntity);

        return climaDto;
    }
}

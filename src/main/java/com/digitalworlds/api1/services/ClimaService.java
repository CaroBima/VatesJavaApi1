package com.digitalworlds.api1.services;


import com.digitalworlds.api1.dto.ClimaDTO;
import com.digitalworlds.api1.entities.ClimaEntity;
import com.digitalworlds.api1.model.Clima;
import com.digitalworlds.api1.repository.IClimaRepository;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.ResourceAccessException;
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

    private ModelMapper modelMapper;

    public ClimaService(final IClimaRepository climaRepository, final ModelMapper modelMapper){
        this.climaRepo = climaRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public ClimaDTO getWeatherData(String city)  {
        Clima clima;
        ClimaDTO climaDto = new ClimaDTO();
        ClimaEntity climaEntity = new ClimaEntity();
        ObjectMapper objectMapper = new ObjectMapper();
        RestTemplate client = new RestTemplate();
        Date fechaHoraActual = new Date();

        String url = weatherUrl+"/current.json?key=" + weatherKey +"&q="+city; //url de la api externa

        try {
            String response = client.getForObject(url, String.class); //traigo la info de la api externa

            clima = objectMapper.readValue(response, Clima.class); //mapeo la info de la appi externa

            climaDto.setName(clima.getLocation().getName());
            climaDto.setCountry(clima.getLocation().getCountry());
            climaDto.setRegion(clima.getLocation().getRegion());
            climaDto.setHumidity(clima.getCurrent().getHumidity());
            climaDto.setTempC(clima.getCurrent().getTempC());
            climaDto.setFeelslikeC(clima.getCurrent().getFeelslikeC());
            climaDto.setWindKph(clima.getCurrent().getWindKph());
            climaDto.setLastUpdated(clima.getCurrent().getLastUpdated());
            //climaDto.setFechaHoraConsulta(fechaHoraActual); // no se pasa al front, lo dejo en el entity

            climaEntity = modelMapper.map(climaDto, ClimaEntity.class);
            climaEntity.setFechaConsulta(fechaHoraActual);

            climaRepo.save(climaEntity);
        } catch (JsonProcessingException j){
            System.out.println("Error en el mapeo del json");
            j.printStackTrace();
        } catch(ResourceAccessException e){
            System.out.println("Revisar conexión a internet o disponibilidad de la api externa");
            e.printStackTrace();
        }
        return climaDto;
    }

    @Override
    public ClimaDTO getWeatherDataById(Long id) {
        ClimaDTO climaDto = modelMapper.map(climaRepo.findById(id), ClimaDTO.class);
        return climaDto;
    }

    @Override
    public void saveClima(ClimaDTO climaDto) {
        ClimaEntity climaEntity = new ClimaEntity();
        Date fechaHoraActual = new Date();

        climaEntity = modelMapper.map(climaDto, ClimaEntity.class);
        climaEntity.setFechaConsulta(fechaHoraActual);

        climaRepo.save(climaEntity);
    }

    public void deleteWeather(Long id){
        climaRepo.deleteById(id);
    }

    public void setRestTemplate(RestTemplate restTemplate) {
    }
}

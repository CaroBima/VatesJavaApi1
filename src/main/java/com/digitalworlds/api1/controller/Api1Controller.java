package com.digitalworlds.api1.controller;

import ch.qos.logback.classic.spi.TurboFilterList;
import com.digitalworlds.api1.dto.ProgramaDTO;
import com.digitalworlds.api1.model.Programa;
import com.digitalworlds.api1.model.Programas;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api")
public class Api1Controller {

    @GetMapping("/api1")
    public String greeting() {
        return "Grupo 1";
    }

    @GetMapping("/greeting")
    public String greetingOtherGroups() {
        return "Otros grupos";
    }

    @GetMapping("/data")
    public String getExternalData(){
        RestTemplate client = new RestTemplate();
        String response = client.getForObject("https://www.google.com.ar", String.class);

        return response;
    }


    @GetMapping("/programas")
    public  List<ProgramaDTO> getExternalPrograms(){
        RestTemplate client = new RestTemplate();
        String response = client.getForObject("https://www.cultura.gob.ar/api/v2.0/programas", String.class);

        ObjectMapper objectMapper = new ObjectMapper();
        Programas prog = new Programas();
        List<ProgramaDTO> listaProgramas = new ArrayList<ProgramaDTO>();
        //pasa el json a objeto:
        try {
            prog = objectMapper.readValue(response, Programas.class);

            //recorro la lista de programas para obtener la informacion y guardarla en el ProgramaDTO
            for (Programa unPrograma : prog.getResults()) {
                ProgramaDTO programaDto = new ProgramaDTO();
                programaDto.setAutoridad(unPrograma.getAutoridad());
                programaDto.setLink(unPrograma.getLink());
                programaDto.setDescripcion(unPrograma.getDescripcion());
                programaDto.setEmail(unPrograma.getEmail());
                programaDto.setDireccion(unPrograma.getDireccion());
                programaDto.setNombre(unPrograma.getNombre());
                programaDto.setUrl(unPrograma.getUrl());
                programaDto.setTelefono(unPrograma.getTelefono());
                programaDto.setProvincia(unPrograma.getProvincia());
                programaDto.setDepende_de(unPrograma.getDepende_de());

                listaProgramas.add(programaDto);

            }

        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }


        return listaProgramas;
    }
}

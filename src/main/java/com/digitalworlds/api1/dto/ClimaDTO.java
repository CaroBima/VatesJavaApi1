package com.digitalworlds.api1.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ClimaDTO {
    //private Long id;
    private String name;
    private String country;
    private String region;
    private Double tempC;
    private String lastUpdated;
    private int humidity;
    private Double feelslikeC;
    private Double windKph;
    //private Date fechaHoraConsulta;
}

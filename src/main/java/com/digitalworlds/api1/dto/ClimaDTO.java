package com.digitalworlds.api1.dto;

import lombok.*;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ClimaDTO {
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

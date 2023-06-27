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
    private String ciudad;
    private String país;
    private Double tempCelsius;
    private String ultimaActualizacion;
    private int humedad;
    private Double sensacionTermica;
    private Double vientoKmxH;
    private Date fechaHoraConsulta;
}

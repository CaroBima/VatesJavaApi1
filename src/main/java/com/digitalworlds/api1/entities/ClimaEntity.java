package com.digitalworlds.api1.entities;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;


import javax.persistence.*;
import java.util.Date;

@Getter
@Setter
@RequiredArgsConstructor
@Entity (name="clima")
public class ClimaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String ciudad;

    private String region;

    private String pais;

    @Column(name = "tempcelsius")
    private Double tempCelsius;

    @Column(name = "ultimaactualizacion")
    private String ultimaActualizacion;

    private int humedad;

    @Column(name = "sensaciontermica")
    private Double sensacionTermica;

    @Column(name = "vientokmxh")
    private Double vientoKmxH;

    @Column(name = "fechaconsulta")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaConsulta;
}

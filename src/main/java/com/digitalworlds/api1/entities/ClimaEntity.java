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

    private String name;

    private String region;

    private String country;

    @Column(name = "tempcelsius")
    private Double tempC;

    @Column(name = "ultimaactualizacion")
    private String lastUpdated;

    private int humidity;

    @Column(name = "sensaciontermica")
    private Double feelslikeC;

    @Column(name = "vientokmxh")
    private Double windKph;

    @Column(name = "fechaconsulta")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaConsulta;
}

package com.digitalworlds.api1.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Programa {
    private int id;
    private String url;
    private String link;
    private String nombre;
    private String direccion;
    private String telefono;
    private String descripcion;
    private String email;
    private String provincia;
    private String depende_de;
    private String autoridad;

}

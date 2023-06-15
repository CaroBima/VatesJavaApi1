package com.digitalworlds.api1.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ProgramaDTO {
    private String url;
    private String link;
    private String nombre;
    private String descripcion;
    private String depende_de;
}

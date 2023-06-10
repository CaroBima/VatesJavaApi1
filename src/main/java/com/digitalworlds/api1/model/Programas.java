package com.digitalworlds.api1.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Programas {
    private int count;
    private String next;
    private String previous;
    private Programa results[];
}

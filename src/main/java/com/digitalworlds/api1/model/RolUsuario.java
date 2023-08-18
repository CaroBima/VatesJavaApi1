package com.digitalworlds.api1.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
public class RolUsuario {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private long idRolxUsuario;

    @Column(unique=true)
    private String nombreRol;
}
package com.digitalworlds.api1.services;

import com.digitalworlds.api1.model.Usuario;

public interface IUsuarioService {
    public int crearUsuario(Usuario usuario);

    public Usuario buscarUnUsuario(Long idUsuario);

    public boolean logueoUsuario(Usuario usuario);
}


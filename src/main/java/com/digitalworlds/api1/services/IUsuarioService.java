package com.digitalworlds.api1.services;

import com.digitalworlds.api1.dto.UsuarioLoginDto;
import com.digitalworlds.api1.model.Usuario;

public interface IUsuarioService {
    public Usuario crearUsuario(Usuario usuario);

    public Usuario buscarUnUsuario(Long idUsuario);

    public boolean logueoUsuario(Usuario usuario);


}


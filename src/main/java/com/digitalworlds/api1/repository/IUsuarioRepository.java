package com.digitalworlds.api1.repository;


import com.digitalworlds.api1.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface IUsuarioRepository extends JpaRepository<Usuario, Long> {


        Optional<Usuario> findByNombreUsuario(String nombreUsuario);
}
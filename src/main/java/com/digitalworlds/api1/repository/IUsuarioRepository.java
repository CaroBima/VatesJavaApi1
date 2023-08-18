package com.digitalworlds.api1.repository;


import com.digitalworlds.api1.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IUsuarioRepository extends JpaRepository<Usuario, Long> {

        Usuario findByNombreUsuario(String username);
        }
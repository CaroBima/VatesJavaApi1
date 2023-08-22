package com.digitalworlds.api1.controller;

import com.digitalworlds.api1.configuration.SecurityConfig;
import com.digitalworlds.api1.dto.UsuarioLoginDto;
import com.digitalworlds.api1.model.Usuario;
import com.digitalworlds.api1.services.IUsuarioService;
import com.digitalworlds.api1.services.UsuarioService;
import jdk.swing.interop.SwingInterOpUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UsuarioController {

    @Autowired
    private SecurityConfig securityConfig;

    @Autowired
    private UsuarioService usuarioService;

    @PostMapping("/login")
    public ResponseEntity<UsuarioLoginDto> login(@RequestParam("user") String username, @RequestParam("password") String pwd) {
        UsuarioLoginDto userLoginDto = new UsuarioLoginDto();
        Usuario usuario = new Usuario();

        usuario.setNombreUsuario(username);
        usuario.setContrasenia(pwd);
        try {
            if (usuarioService.logueoUsuario(usuario)) {
                String token = securityConfig.getJWTToken(username);
                userLoginDto.setNombreUsuario(username);
                userLoginDto.setToken(token);

            }

        }catch (Exception e) {
            e.printStackTrace();
            System.out.println("Error en login");
        }

        return ResponseEntity.ok(userLoginDto); //ver de retornar error cuando no este ok el login

    }


    @PostMapping("/register")

    public ResponseEntity<UsuarioLoginDto> register(@RequestBody Usuario usuario) {

        Usuario usuarioCreado = new Usuario(); // usuario creado
        UsuarioLoginDto usuarioLoginDtoCreado = new UsuarioLoginDto();

        try {
            usuarioCreado = usuarioService.crearUsuario(usuario);

            usuarioLoginDtoCreado = this.login(usuarioCreado.getNombreUsuario(), usuarioCreado.getContrasenia()).getBody();
            return ResponseEntity.status(HttpStatus.CREATED)
                    .body(usuarioLoginDtoCreado);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Error al guardar usuario");
            return ResponseEntity.status(HttpStatus.CONFLICT).body(usuarioLoginDtoCreado);
        }

    }
}


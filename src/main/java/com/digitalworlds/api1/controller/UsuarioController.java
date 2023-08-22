package com.digitalworlds.api1.controller;

import com.digitalworlds.api1.configuration.SecurityConfig;
import com.digitalworlds.api1.dto.UsuarioLoginDto;
import com.digitalworlds.api1.model.ResponseMessage;
import com.digitalworlds.api1.model.Usuario;
import com.digitalworlds.api1.services.IUsuarioService;
import com.digitalworlds.api1.services.UsuarioService;
import jdk.swing.interop.SwingInterOpUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
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
    public ResponseEntity<ResponseMessage<UsuarioLoginDto>> login(@RequestBody Usuario usuario) {
        UsuarioLoginDto userLoginDto = new UsuarioLoginDto();

        try {
            if (usuarioService.logueoUsuario(usuario)) {
                String token = securityConfig.getJWTToken(usuario.getNombreUsuario());
                userLoginDto.setNombreUsuario(usuario.getNombreUsuario());
                userLoginDto.setToken(token);

            }else if(!usuarioService.logueoUsuario(usuario)){
                userLoginDto.setNombreUsuario(usuario.getNombreUsuario());
                userLoginDto.setToken(null);
                return this.respuestaConflict(userLoginDto, "El nombre de usuario o la contraseña son incorrectos");
            }

        }catch (Exception e) {
            e.printStackTrace();
            return this.respuestaConflict(userLoginDto, "Error en el login");
        }

        return this.respuestaOk(userLoginDto, "Bienvenido"); //ver de retornar error cuando no este ok el login
    }

    @PostMapping(value="/register")
    public ResponseEntity<ResponseMessage<UsuarioLoginDto>> register(@RequestBody Usuario usuario) {

        Usuario usuarioCreado = new Usuario(); // usuario creado
        UsuarioLoginDto usuarioLoginDtoCreado = new UsuarioLoginDto();

        try {
            usuarioCreado = usuarioService.crearUsuario(usuario);

            if(usuarioCreado != null){
            usuarioLoginDtoCreado = this.login(usuarioCreado).getBody().getData();

            return this.respuestaOk(usuarioLoginDtoCreado, "Usuario creado correctamente");
            }else{
                String mensaje = "El nombre de usuario ya se encuentra en uso";
                usuarioLoginDtoCreado.setNombreUsuario(usuario.getNombreUsuario());

                return this.respuestaConflict(usuarioLoginDtoCreado, mensaje);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return this.respuestaConflict(usuarioLoginDtoCreado, "Se ha producido un error al guardar el usuario");
        }

    }

    //respuesta en caso de que se genere el usuario ok
    public ResponseEntity<ResponseMessage<UsuarioLoginDto>> respuestaOk(UsuarioLoginDto usuarioARetornar, String mensajeAdicional) {
        ResponseMessage<UsuarioLoginDto> responseMessage = new ResponseMessage<>(usuarioARetornar, mensajeAdicional);

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(responseMessage);
    }

    //respuesta en caso de que se produzca error en la creación del usario
    public ResponseEntity<ResponseMessage<UsuarioLoginDto>> respuestaConflict(UsuarioLoginDto usuarioARetornar, String mensajeAdicional) {
        ResponseMessage<UsuarioLoginDto> responseMessage = new ResponseMessage<>(usuarioARetornar, mensajeAdicional);

        return ResponseEntity.status(HttpStatus.CONFLICT)
                .body(responseMessage);
    }
}
package com.digitalworlds.api1.services;

import com.digitalworlds.api1.dto.UsuarioLoginDto;
import com.digitalworlds.api1.model.ResponseMessage;
import com.digitalworlds.api1.model.Usuario;
import com.digitalworlds.api1.repository.IUsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UsuarioService implements IUsuarioService{
    @Autowired
    IUsuarioRepository usuarioRepo;

    private final PasswordEncoder passwordEncoder;

    // Constructor-based dependency injection
    @Autowired
    public UsuarioService(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }


    @Override
    public Usuario crearUsuario(Usuario usuario) {
       Usuario usuarioSave = new Usuario();
       Usuario usuGuardado = new Usuario();

       //busco el usuario para ver si el nombre de usuario ya se encuentra registado
        try{
            usuGuardado = usuarioRepo.findByNombreUsuario(usuario.getNombreUsuario()).orElse(null);
        }catch (Exception e){
            e.printStackTrace();
            System.out.println("Error en la busqueda del nombre de usuario");
        }

        if(usuGuardado == null){ //si el usuario no esta en la bbdd lo guardo
            try{
                usuarioSave.setContrasenia(passwordEncoder.encode(usuario.getContrasenia()));
                usuarioSave.setNombreUsuario(usuario.getNombreUsuario());
                System.out.println("llega a la parte del guardado, usuario: " + usuarioSave.toString());
                usuarioRepo.save(usuarioSave);
                return usuario; //Devuelve el usuario registrado original para generar el token
                //ver de devolver el token directamente desde aca
            }catch (Exception e) {
                e.printStackTrace();
                System.out.println("El usuario no ha podido ser guardado");
            }
        }else{
            System.out.println("Nombre de usuario ya registrado: " + usuGuardado.toString());
            //return null; //ver esto
        }


        return null; //retorno null en caso de que el usuario ya se encuentre previamente registrado
        //return usuario; //devolviendo esto no valido si el usuario ya estaba registrado, devuelve los datos el usuario
   }




    //metodo que permite buscar un usuario por nombre y clave
    @Override
    public boolean logueoUsuario (Usuario usuario){
        boolean logueo = false;
        Usuario usuGuardado = new Usuario();

        try {
            usuGuardado = usuarioRepo.findByNombreUsuario(usuario.getNombreUsuario()).orElse(null); //busco el usuario por el nombre de usuario ingresado

            if (usuGuardado != null) {
                if (usuario.getNombreUsuario().equals(usuGuardado.getNombreUsuario()) && passwordEncoder.matches(usuario.getContrasenia(), usuGuardado.getContrasenia())) {
                    logueo = true;
                } else logueo = false;
            }
        }catch (Exception e) {
            e.printStackTrace();
            System.out.println("Error en el logueo del usuario");
        }

        return logueo;
    }


    @Override
    public Usuario buscarUnUsuario(Long idUsuario) {
        return usuarioRepo.findById(idUsuario).orElse(null);
    }



}

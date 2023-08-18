package com.digitalworlds.api1.services;

import com.digitalworlds.api1.model.Usuario;
import com.digitalworlds.api1.repository.IUsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

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
    public int crearUsuario(Usuario usuario) {
       /* Usuario usuarioSave = new Usuario();
        int estadoCreacion = 0; // 1 - se guardo ok, 2 - el nombre de usuario ya esta siendo utilizado, 0 - cualquier otro error

        //busco el usuario para ver si el nombre de usuario ya se encuentra registado
        Usuario usuGuardado = usuarioRepo.findByNombreUsuario(usuario.getNombreUsuario());

        if(usuGuardado.getNombreUsuario() == null){

            try{
                usuarioSave.setContrasenia(passwordEncoder.encode(usuario.getContrasenia()));
                usuarioSave.setNombreUsuario(usuario.getNombreUsuario());

                usuarioRepo.save(usuarioSave);
                estadoCreacion = 1;
            }catch (Exception e) {
                e.printStackTrace();
                estadoCreacion = 0;
                System.out.println("El usuario no ha podido ser guardado");
            }
        }else{
            estadoCreacion = 2;
        }

        return estadoCreacion; // 1 - se guardo ok, 2 - el nombre de usuario ya esta siendo utilizado, 0 - cualquier otro error
   */
        return 0;
    }



    //metodo que permite buscar un usuario por nombre y clave
    @Override
    public boolean logueoUsuario (Usuario usuario){
        boolean logueo = false;
        Usuario usuGuardado = new Usuario();

        try {
            usuGuardado = usuarioRepo.findByNombreUsuario(usuario.getNombreUsuario()); //busco el usuario por el nombre de usuario ingresado

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

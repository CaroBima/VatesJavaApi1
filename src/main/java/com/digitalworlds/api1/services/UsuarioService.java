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

    @Autowired
    public UsuarioService(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    } //inyección de dependencias por constructor


    @Override
    public Usuario crearUsuario(Usuario usuario) {
       Usuario usuarioSave = new Usuario();
       Usuario usuGuardado = new Usuario();
        try{
            usuGuardado = usuarioRepo.findByNombreUsuario(usuario.getNombreUsuario()).orElse(null);
        }catch (Exception e){
            e.printStackTrace();
        }
        if(usuGuardado == null){
            try{
                usuarioSave.setContrasenia(passwordEncoder.encode(usuario.getContrasenia()));
                usuarioSave.setNombreUsuario(usuario.getNombreUsuario());
                usuarioRepo.save(usuarioSave);
                return usuario; //Devuelve el usuario registrado original para generar el token
                //ver de devolver el token directamente desde aca
            }catch (Exception e) {
                e.printStackTrace();
            }
        }else{
            System.out.println("Nombre de usuario ya registrado: " + usuGuardado.toString());
            //return null; //ver esto
        }
        return null; //retorno null en caso de que el usuario ya se encuentre previamente registrado
    }

    /**
     * Metodo que permite buscar un usuario por nombre y clave y validar que los datos ingresados sean correctos.
     * @param usuario
     * @return boolean logueo
     */
    @Override
    public boolean logueoUsuario (Usuario usuario){
        boolean logueo = false;
        Usuario usuGuardado = new Usuario();
        try {
            usuGuardado = usuarioRepo.findByNombreUsuario(usuario.getNombreUsuario()).orElse(null);
            if (usuGuardado != null) {
                if (usuario.getNombreUsuario().equals(usuGuardado.getNombreUsuario()) && passwordEncoder.matches(usuario.getContrasenia(), usuGuardado.getContrasenia())) {
                    logueo = true;
                } else logueo = false;
            }
        }catch (Exception e) {
            e.printStackTrace();
        }
        return logueo;
    }

    /**
     * Busca un usuario por su id en la base de datos
     * @param idUsuario
     * @return
     */
    @Override
    public Usuario buscarUnUsuario(Long idUsuario) {
        return usuarioRepo.findById(idUsuario).orElse(null);
    }
}

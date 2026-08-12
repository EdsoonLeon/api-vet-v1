package com.testvet.services;

import com.testvet.model.Usuario;
import com.testvet.repository.IUsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UsuarioService {
    @Autowired
    private IUsuarioRepository repoUsu;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public Usuario registrarUsuario(Usuario nuevo){
        String claveCodificada = passwordEncoder.encode(nuevo.getClave());
        nuevo.setClave(claveCodificada);
        return repoUsu.save(nuevo);
    }

    public Usuario login(Usuario usuario){
        Usuario encontrado = repoUsu.findByCorreo(usuario.getCorreo())
                .orElseThrow( () ->  new RuntimeException("Usuario no Encontrado"));

        if(!passwordEncoder.matches(usuario.getClave(), encontrado.getClave())){
            throw new RuntimeException("Contraseña incorrecta");
        }
        return encontrado;
    }

    public List<Usuario> listarUsuarios(){
        return repoUsu.findAll();
    }

    public List<Usuario> listarUsuariosActivos(){
        return repoUsu.findByActivoTrue();
    }
    public Usuario obtnerPorid(Integer id){
        return repoUsu.findById(id).orElseThrow(() -> new RuntimeException("Usuario no encontrado"));
    }

    public Usuario editarUsuario(Integer id, Usuario nuevoDatos){
        Usuario existente = repoUsu.findById(id)
                .orElseThrow( () -> new RuntimeException("Usuario no encontrado."));

        existente.setNombre(nuevoDatos.getNombre());
        existente.setApellido(nuevoDatos.getApellido());
        existente.setCorreo(nuevoDatos.getCorreo());
        existente.setRol(nuevoDatos.getRol());

        if(nuevoDatos.getClave() != null && !nuevoDatos.getClave().isBlank()){
            existente.setClave(passwordEncoder.encode(nuevoDatos.getClave()));
        }

        return repoUsu.save(existente);

    }

    public void eliminarUsuario(Integer id){
        Usuario usuario = repoUsu.findById(id)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));
        usuario.setActivo(false);
        repoUsu.save(usuario);
    }

    public Usuario activarUsuario(Integer id){
        Usuario usuario = repoUsu.findById(id)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));
        usuario.setActivo(true);
        return repoUsu.save(usuario);
    }
}

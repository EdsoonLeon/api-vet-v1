package com.testvet.services;

import com.testvet.model.Usuario;
import com.testvet.repository.IUsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

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
}

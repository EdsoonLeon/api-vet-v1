package com.testvet.services;

import com.testvet.model.Rol;
import com.testvet.model.Usuario;
import com.testvet.model.Veterinario;
import com.testvet.repository.IRolRepository;
import com.testvet.repository.IUsuarioRepository;
import com.testvet.repository.IVeterinarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class VeterinarioService {

    @Autowired
    private IVeterinarioRepository repoVeterinario;

    @Autowired
    private IUsuarioRepository repoUsuario;

    @Autowired
    private IRolRepository repoRol;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Transactional
    public Veterinario registrarVeterinario(Veterinario datosVeterinario){
        Usuario datosUsuario = datosVeterinario.getUsuario();

        Rol rolVeterinario = repoRol.findByNombre("VETERINARIO")
                .orElseThrow(() -> new RuntimeException("Rol VETERINARIO no configurado"));

        datosUsuario.setRol(rolVeterinario);
        datosUsuario.setClave(passwordEncoder.encode(datosUsuario.getClave()));

        Usuario usuarioGuardado = repoUsuario.save(datosUsuario);
        datosVeterinario.setUsuario(usuarioGuardado);

        return repoVeterinario.save(datosVeterinario);
    }

    public List<Veterinario> listarVeterinarios(){
        return repoVeterinario.findByUsuario_ActivoTrue();
    }

    public Veterinario obtenerPorId(Integer id){
        return repoVeterinario.findById(id)
                .orElseThrow(() -> new RuntimeException("Veterinario no encontrado"));
    }

    public Veterinario editarVeterinario(Integer id, Veterinario datosNuevos){
        Veterinario existente = repoVeterinario.findById(id)
                .orElseThrow(() -> new RuntimeException("Veterinario no encontrado"));

        existente.setEspecialidad(datosNuevos.getEspecialidad());
        existente.setColegiatura(datosNuevos.getColegiatura());
        existente.setEdad(datosNuevos.getEdad());

        return repoVeterinario.save(existente);
    }

    public void eliminarVeterinario(Integer id){
        Veterinario veterinario = repoVeterinario.findById(id)
                .orElseThrow(() -> new RuntimeException("Veterinario no encontrado"));

        Usuario usuario = veterinario.getUsuario();
        usuario.setActivo(false);
        repoUsuario.save(usuario);
    }

    public Veterinario activarVeterinario(Integer id){
        Veterinario veterinario = repoVeterinario.findById(id)
                .orElseThrow(() -> new RuntimeException("Veterinario no encontrado"));

        Usuario usuario = veterinario.getUsuario();
        usuario.setActivo(true);
        repoUsuario.save(usuario);

        return veterinario;
    }
}
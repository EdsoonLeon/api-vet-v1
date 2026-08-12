package com.testvet.services;

import com.testvet.model.Cliente;
import com.testvet.model.Mascota;
import com.testvet.repository.IClienteRepository;
import com.testvet.repository.IMascotaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MascotaService {

    @Autowired
    private IMascotaRepository repoMascota;

    @Autowired
    private IClienteRepository repoCliente;

    public Mascota registrarMascota(Mascota nueva){
        Integer clienteId = nueva.getCliente().getId();
        Cliente cliente = repoCliente.findById(clienteId)
                .orElseThrow(() -> new RuntimeException("Cliente no encontrado"));

        nueva.setCliente(cliente);
        return repoMascota.save(nueva);
    }

    public List<Mascota> listarMascotas(){
        return repoMascota.findByActivoTrue();
    }

    public List<Mascota> listarPorCliente(Integer clienteId){
        return repoMascota.findByCliente_Id(clienteId);
    }

    public Mascota obtenerPorId(Integer id){
        return repoMascota.findById(id)
                .orElseThrow(() -> new RuntimeException("Mascota no encontrada"));
    }

    public Mascota editarMascota(Integer id, Mascota datosNuevos){
        Mascota existente = repoMascota.findById(id)
                .orElseThrow(() -> new RuntimeException("Mascota no encontrada"));

        existente.setNombre(datosNuevos.getNombre());
        existente.setEspecie(datosNuevos.getEspecie());
        existente.setRaza(datosNuevos.getRaza());
        existente.setEdad(datosNuevos.getEdad());

        return repoMascota.save(existente);
    }

    public void eliminarMascota(Integer id){
        Mascota mascota = repoMascota.findById(id)
                .orElseThrow(() -> new RuntimeException("Mascota no encontrada"));
        mascota.setActivo(false);
        repoMascota.save(mascota);
    }

    public Mascota activarMascota(Integer id){
        Mascota mascota = repoMascota.findById(id)
                .orElseThrow(() -> new RuntimeException("Mascota no encontrada"));
        mascota.setActivo(true);
        return repoMascota.save(mascota);
    }
}
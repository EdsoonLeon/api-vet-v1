package com.testvet.services;

import com.testvet.model.Cita;
import com.testvet.model.Mascota;
import com.testvet.model.Veterinario;
import com.testvet.repository.ICitaRepository;
import com.testvet.repository.IMascotaRepository;
import com.testvet.repository.IVeterinarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CitaService {

    @Autowired
    private ICitaRepository repoCita;

    @Autowired
    private IMascotaRepository repoMascota;

    @Autowired
    private IVeterinarioRepository repoVeterinario;

    public Cita registrarCita(Cita nueva){
        Integer mascotaId = nueva.getMascota().getId();
        Mascota mascota = repoMascota.findById(mascotaId)
                .orElseThrow(() -> new RuntimeException("Mascota no encontrada"));

        Integer veterinarioId = nueva.getVeterinario().getId();
        Veterinario veterinario = repoVeterinario.findById(veterinarioId)
                .orElseThrow(() -> new RuntimeException("Veterinario no encontrado"));

        nueva.setMascota(mascota);
        nueva.setVeterinario(veterinario);
        nueva.setEstado("PENDIENTE");

        return repoCita.save(nueva);
    }

    public List<Cita> listarCitas(){
        return repoCita.findAll();
    }

    public List<Cita> listarPorMascota(Integer mascotaId){
        return repoCita.findByMascota_Id(mascotaId);
    }

    public List<Cita> listarPorVeterinario(Integer veterinarioId){
        return repoCita.findByVeterinario_Id(veterinarioId);
    }

    public Cita obtenerPorId(Integer id){
        return repoCita.findById(id)
                .orElseThrow(() -> new RuntimeException("Cita no encontrada"));
    }

    public Cita editarCita(Integer id, Cita datosNuevos){
        Cita existente = repoCita.findById(id)
                .orElseThrow(() -> new RuntimeException("Cita no encontrada"));

        existente.setFecha(datosNuevos.getFecha());
        existente.setHora(datosNuevos.getHora());

        return repoCita.save(existente);
    }

    public Cita cambiarEstado(Integer id, String nuevoEstado){
        Cita cita = repoCita.findById(id)
                .orElseThrow(() -> new RuntimeException("Cita no encontrada"));
        cita.setEstado(nuevoEstado);
        return repoCita.save(cita);
    }

    public void cancelarCita(Integer id){
        cambiarEstado(id, "CANCELADA");
    }
}
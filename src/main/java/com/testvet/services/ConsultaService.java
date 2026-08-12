package com.testvet.services;

import com.testvet.model.Consulta;
import com.testvet.model.Mascota;
import com.testvet.model.Veterinario;
import com.testvet.repository.IConsultaRepository;
import com.testvet.repository.IMascotaRepository;
import com.testvet.repository.IVeterinarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ConsultaService {

    @Autowired
    private IConsultaRepository repoConsulta;

    @Autowired
    private IMascotaRepository repoMascota;

    @Autowired
    private IVeterinarioRepository repoVeterinario;

    public Consulta registrarConsulta(Consulta nueva){
        Integer mascotaId = nueva.getMascota().getId();
        Mascota mascota = repoMascota.findById(mascotaId)
                .orElseThrow(() -> new RuntimeException("Mascota no encontrada"));

        Integer veterinarioId = nueva.getVeterinario().getId();
        Veterinario veterinario = repoVeterinario.findById(veterinarioId)
                .orElseThrow(() -> new RuntimeException("Veterinario no encontrado"));

        nueva.setMascota(mascota);
        nueva.setVeterinario(veterinario);

        return repoConsulta.save(nueva);
    }

    public List<Consulta> listarConsultas(){
        return repoConsulta.findAll();
    }

    public List<Consulta> listarPorMascota(Integer mascotaId){
        return repoConsulta.findByMascota_Id(mascotaId);
    }

    public List<Consulta> listarPorVeterinario(Integer veterinarioId){
        return repoConsulta.findByVeterinario_Id(veterinarioId);
    }

    public Consulta obtenerPorId(Integer id){
        return repoConsulta.findById(id)
                .orElseThrow(() -> new RuntimeException("Consulta no encontrada"));
    }

    public Consulta editarConsulta(Integer id, Consulta datosNuevos){
        Consulta existente = repoConsulta.findById(id)
                .orElseThrow(() -> new RuntimeException("Consulta no encontrada"));

        existente.setFecha(datosNuevos.getFecha());
        existente.setDiagnostico(datosNuevos.getDiagnostico());
        existente.setTratamiento(datosNuevos.getTratamiento());
        existente.setObservacion(datosNuevos.getObservacion());

        return repoConsulta.save(existente);
    }

    public void eliminarConsulta(Integer id){
        if (!repoConsulta.existsById(id)) {
            throw new RuntimeException("Consulta no encontrada");
        }
        repoConsulta.deleteById(id);
    }
}
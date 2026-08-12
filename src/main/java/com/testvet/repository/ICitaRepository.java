package com.testvet.repository;

import com.testvet.model.Cita;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ICitaRepository extends JpaRepository<Cita, Integer> {

    List<Cita> findByMascota_Id(Integer mascotaId);
    List<Cita> findByVeterinario_Id(Integer veterinarioId);
    
}
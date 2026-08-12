package com.testvet.repository;

import com.testvet.model.Consulta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IConsultaRepository extends JpaRepository<Consulta, Integer> {

    List<Consulta> findByMascota_Id(Integer mascotaId);
    List<Consulta> findByVeterinario_Id(Integer veterinarioId);
}
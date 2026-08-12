package com.testvet.repository;

import com.testvet.model.Mascota;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IMascotaRepository extends JpaRepository<Mascota, Integer> {
    List<Mascota> findByActivoTrue();
    List<Mascota> findByCliente_Id(Integer clienteId);
}
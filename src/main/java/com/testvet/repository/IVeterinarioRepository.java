package com.testvet.repository;

import com.testvet.model.Veterinario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IVeterinarioRepository extends JpaRepository<Veterinario, Integer> {
    List<Veterinario> findByUsuario_ActivoTrue();
}
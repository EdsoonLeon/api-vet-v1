package com.testvet.repository;

import com.testvet.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface IUsuarioRepository extends JpaRepository<Usuario,Integer> {

    //BUSCARDOR POR CORREO
    Optional<Usuario> findByCorreo(String correo);

    //para listar solo los usuarios activos
    List<Usuario> findByActivoTrue();
}

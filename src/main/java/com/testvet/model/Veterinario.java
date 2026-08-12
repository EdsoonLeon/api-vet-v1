package com.testvet.model;


import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "veterinarios")
@Data
public class Veterinario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String especialidad;
    private String colegiatura;
    private Integer edad;

    @OneToOne
    @JoinColumn(name = "usuario_id", nullable = false, unique = true)
    private Usuario usuario;
}
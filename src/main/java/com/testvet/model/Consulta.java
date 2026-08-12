package com.testvet.model;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;

@Entity
@Table(name = "consultas")
@Data
public class Consulta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private LocalDate fecha;
    private String diagnostico;
    private String tratamiento;
    private String observacion;

    @ManyToOne
    @JoinColumn(name = "mascota_id", nullable = false)
    private Mascota mascota;

    @ManyToOne
    @JoinColumn(name = "veterinario_id", nullable = false)
    private Veterinario veterinario;
}
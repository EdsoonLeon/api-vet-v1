package com.testvet.model;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalTime;

@Entity
@Table(name = "citas")
@Data
public class Cita {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private LocalDate fecha;
    private LocalTime hora;

    @Column(nullable = false)
    private String estado = "PENDIENTE";

    @ManyToOne
    @JoinColumn(name = "mascota_id", nullable = false)
    private Mascota mascota;

    @ManyToOne
    @JoinColumn(name = "veterinario_id", nullable = false)
    private Veterinario veterinario;
}
package com.testvet.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "mascotas")
@Data
public class Mascota {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String nombre;
    private String especie;
    private String raza;
    private Integer edad;

    @Column(nullable = false)
    private Boolean activo = true;

    @ManyToOne
    @JoinColumn(name = "cliente_id", nullable = false)
    private Cliente cliente;
}
package com.testvet.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity//ESTA ANOTACION ME INDICA QUE ES UNA ENTIDAD DE LA BASE DE DATOS
@Table(name = "usuarios") //ESTA ANOTACION ME INDICA QUE ES UNA TABLA CON SU NOMBRE DE LA BASE DE DATOS
@Data// ESTA ANOTACION ES LOMBOOK QUE ME GENERA LOS GETTERS AND SETTERS
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String nombre;
    private String apellido;

    @Column(nullable = false,unique = true)
    private String correo;

    @Column(nullable = false)
    private String clave;

    @ManyToOne
    @JoinColumn(name = "rol_id",nullable = false)
    private Rol rol;

    @Column(nullable = false)
    private Boolean activo = true;
}

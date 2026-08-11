package com.testvet.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity//ESTA ANOTACION ME INDICA QUE ES UNA ENTIDAD DE LA BASE DE DATOS
@Table(name = "roles") //ESTA ANOTACION ME INDICA QUE ES UNA TABLA CON SU NOMBRE DE LA BASE DE DATOS
@Data// ESTA ANOTACION ES LOMBOOK QUE ME GENERA LOS GETTERS AND SETTERS
public class Rol {

    @Id//ANOTACION QUE ES UN PRIMARY KEY
    @GeneratedValue(strategy = GenerationType.IDENTITY)//ANOTACION QUE ES AUINCREMENTABLE QUE ES DE UNO A UNO
    private Integer id;

    @Column(nullable = false,unique = true)// ANOTACION QUE ES UNA TABLA
    private String nombre;
}

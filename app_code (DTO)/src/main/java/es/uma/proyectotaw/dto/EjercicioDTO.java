//Autor: Álvaro Valencia Villalón
package es.uma.proyectotaw.dto;

import es.uma.proyectotaw.entity.Entrenamiento;
import jakarta.persistence.Id;
import lombok.Data;

import java.util.Set;

@Data
public class EjercicioDTO {


    private Integer id;
    private String nombre;
    private TipoEjercicioDTO tipo;
    private String descripcion;
    private String urlVideo;
    private GrupoMuscularDTO grupoMuscular;
    private Set<EjercicioEntrenamientoDTO> entrenamientos;


}

//Autor: Álvaro Valencia Villalón
package es.uma.proyectotaw.dto;

import es.uma.proyectotaw.entity.Entrenamiento;
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

    //Es posible que por ejemplo tipo de ejercicio tengamos que cambiarlo
    //de Integer (ID) a TipoEjercicioDTO, igual con grupomuscular etc


}

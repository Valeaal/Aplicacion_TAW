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
    private Set<Integer> entrenamientos;

    //En entrenamientos, no podemos referenciar a ejercicioEntrenamientoDTO porque se produciría desbordamiento en la pila de llamadas (StackOverflow).
    //En este caso referenciamos a los id porque desde un Ejercicio no necesitamos acceder al EjercicioEntrenamiento en los JSP, lo cual si debemos de hacer
    //al revés es decir, acceder a EjercicioDTO desde EjercicioEntrenamiento en los JSP.



}

//Autor: Álvaro Valencia Villalón

package es.uma.proyectotaw.dto;

import es.uma.proyectotaw.entity.Ejercicio;
import es.uma.proyectotaw.entity.EjercicioEntrenamiento;
import lombok.Data;

@Data
public class EjercicioEntrenamientoDTO {

    private Integer id;
    private EjercicioDTO ejercicio;
    private EntrenamientoDTO entrenamiento;
    private Integer desempeno;
    private Integer series;
    private Integer repeticiones;
    private Float peso;
    private Integer tiempo;
    private Float distancia;
    private Integer orden;
}

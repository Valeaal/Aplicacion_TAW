package es.uma.proyectotaw.dto;

import es.uma.proyectotaw.entity.Entrenamiento;
import lombok.Data;

import java.util.Set;

@Data
public class EjercicioDTO {

    private Integer id;
    private String nombre;
    private Integer tipo;
    private String descripcion;
    private String urlVideo;
    private Integer grupoMuscular;
    private Set<EjercicioEntrenamientoDTO> entrenamientos;

    //Es posible que por ejemplo tipo de ejercicio tengamos que cambiarlo
    //de Integer (ID) a TipoEjercicioDTO, igual con grupomuscular etc


}

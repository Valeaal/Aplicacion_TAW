package es.uma.proyectotaw.dto;

import es.uma.proyectotaw.entity.EjercicioEntrenamiento;
import es.uma.proyectotaw.entity.EntrenamientoRutina;
import jakarta.persistence.Column;
import jakarta.persistence.Lob;
import jakarta.persistence.OneToMany;
import lombok.Data;

import java.util.HashSet;
import java.util.Set;

@Data
public class EntrenamientoDTO {
    private Integer id;
    private String nombre;
    private String descripcion;
    private Set<EntrenamientoRutinaDTO> rutinas = new HashSet<>();
    private Set<EjercicioEntrenamientoDTO> ejercicios = new HashSet<>();
}

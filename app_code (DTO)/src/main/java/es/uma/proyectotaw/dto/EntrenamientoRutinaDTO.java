package es.uma.proyectotaw.dto;

import es.uma.proyectotaw.entity.Entrenamiento;
import es.uma.proyectotaw.entity.Rutina;
import jakarta.persistence.Column;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;
// autor: Alba de la Torre

@Data
public class EntrenamientoRutinaDTO {
    private Integer id;
    private Integer entrenamiento;
    private Integer rutina;
    private Integer diaSemana;
}

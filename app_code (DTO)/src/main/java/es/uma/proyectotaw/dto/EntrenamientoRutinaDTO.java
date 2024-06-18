package es.uma.proyectotaw.dto;

import es.uma.proyectotaw.entity.Entrenamiento;
import es.uma.proyectotaw.entity.Rutina;
import jakarta.persistence.Column;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

public class EntrenamientoRutinaDTO {
    private Integer id;
    private EntrenamientoDTO entrenamiento;
    private Rutina rutina;
    private Integer diaSemana;
}

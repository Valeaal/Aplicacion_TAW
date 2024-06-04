package es.uma.proyectotaw.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "entrenamiento_rutina")
public class EntrenamientoRutina {
    @EmbeddedId
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private EntrenamientoRutinaId id;

    @MapsId("entrenamientoId")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "entrenamiento_id", nullable = false)
    private Entrenamiento entrenamiento;

    @MapsId("rutinaId")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "rutina_id", nullable = false)
    private Rutina rutina;

    @Column(name = "dia_semana", nullable = false)
    private Integer diaSemana;

}
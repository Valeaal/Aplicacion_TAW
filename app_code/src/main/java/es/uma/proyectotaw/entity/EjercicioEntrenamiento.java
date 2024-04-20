package es.uma.proyectotaw.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "ejercicio_entrenamiento")
public class EjercicioEntrenamiento {
    @EmbeddedId
    private EjercicioEntrenamientoId id;

    @MapsId("ejercicioId")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "ejercicio_id", nullable = false)
    private Ejercicio ejercicio;

    @MapsId("entrenamientoId")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "entrenamiento_id", nullable = false)
    private Entrenamiento entrenamiento;

    @MapsId("desempenoId")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "desempeno_id", nullable = false)
    private Cliente desempeno;

    @Column(name = "series", nullable = false)
    private Integer series;

    @Column(name = "repeticiones", nullable = false)
    private Integer repeticiones;

    @Column(name = "peso", nullable = false)
    private Float peso;

    @Column(name = "tiempo", nullable = false)
    private Integer tiempo;

    @Column(name = "distancia", nullable = false)
    private Float distancia;

    @Column(name = "orden", nullable = false)
    private Integer orden;

}
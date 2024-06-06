package es.uma.proyectotaw.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "ejercicio_entrenamiento")
public class EjercicioEntrenamiento {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "ejercicio_id", nullable = false)
    private Ejercicio ejercicio;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "entrenamiento_id", nullable = false)
    private Entrenamiento entrenamiento;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "desempeno_id")
    private Desempeno desempeno;

    @Column(name = "series")
    private Integer series;

    @Column(name = "repeticiones")
    private Integer repeticiones;

    @Column(name = "peso")
    private Float peso;

    @Column(name = "tiempo")
    private Integer tiempo;

    @Column(name = "distancia")
    private Float distancia;

    @Column(name = "orden", nullable = false)
    private Integer orden;

}
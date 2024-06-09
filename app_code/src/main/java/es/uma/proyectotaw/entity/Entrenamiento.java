package es.uma.proyectotaw.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "entrenamiento")
public class Entrenamiento {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "nombre", nullable = false)
    private String nombre;

    @Lob
    @Column(name = "descripcion")
    private String descripcion;

    @OneToMany(mappedBy = "entrenamiento")
    private Set<EntrenamientoRutina> rutinas = new HashSet<>();

    @OneToMany(mappedBy = "entrenamiento")
    private Set<EjercicioEntrenamiento> ejercicios = new HashSet<>();

}
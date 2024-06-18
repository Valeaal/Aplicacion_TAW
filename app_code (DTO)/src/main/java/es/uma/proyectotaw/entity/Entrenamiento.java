package es.uma.proyectotaw.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
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

    public Entrenamiento toDTO () {
        Entrenamiento Entrenamiento = new Entrenamiento();
        Entrenamiento.setId(this.id);
        Entrenamiento.setNombre(this.nombre);
        Entrenamiento.setDescripcion(this.descripcion);

        Set<EntrenamientoRutina> rutinas = new HashSet<>(this.rutinas);
        Entrenamiento.setRutinas(rutinas);

        Set<EjercicioEntrenamiento> ejercicios = new HashSet<>(this.ejercicios);
        Entrenamiento.setEjercicios(ejercicios);

        return Entrenamiento;



    }
}
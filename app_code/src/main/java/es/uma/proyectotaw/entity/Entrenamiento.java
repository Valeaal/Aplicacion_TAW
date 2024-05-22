package es.uma.proyectotaw.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

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
    private List<EjercicioEntrenamiento> ejercicioEntrenamientos = new ArrayList<>();

    @OneToMany(mappedBy = "entrenamiento")
    private List<EntrenamientoRutina> entrenamientoRutinas = new ArrayList<>();

}
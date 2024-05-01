package es.uma.proyectotaw.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import java.util.Set;
import java.util.HashSet;

@Getter
@Setter
@Entity
@Table(name = "entrenamiento")
public class Entrenamiento {
    @Id
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "nombre", nullable = false)
    private String nombre;

    @Lob
    @Column(name = "descripcion")
    private String descripcion;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "tipo", nullable = false)
    private TipoEjercicio tipo;

    @OneToMany(mappedBy = "entrenamiento")
    private Set<EntrenamientoRutina> rutinas = new HashSet<>();

    @OneToMany(mappedBy = "entrenamiento")
    private Set<EjercicioEntrenamiento> ejercicios = new HashSet<>();
}
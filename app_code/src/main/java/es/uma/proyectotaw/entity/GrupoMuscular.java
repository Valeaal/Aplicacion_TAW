package es.uma.proyectotaw.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "grupo_muscular")
public class GrupoMuscular {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Lob
    @Column(name = "grupo", nullable = false)
    private String grupo;

    @OneToMany(mappedBy = "grupoMuscular")
    private List<Ejercicio> ejercicios = new ArrayList<>();

}
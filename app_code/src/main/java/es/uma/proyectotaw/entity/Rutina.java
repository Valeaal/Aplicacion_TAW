package es.uma.proyectotaw.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.Set;
import java.util.HashSet;


@Getter
@Setter
@Entity
@Table(name = "rutina")
public class Rutina {
    @Id
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "nombre", nullable = false)
    private String nombre;

    @Lob
    @Column(name = "descripcion")
    private String descripcion;

    @Column(name = "fecha_creacion", nullable = false)
    private LocalDate fechaCreacion;

    @OneToMany(mappedBy = "rutina")
    private Set<ClienteRutina> clientes = new HashSet<>();

    @OneToMany(mappedBy = "rutina")
    private Set<EntrenamientoRutina> entrenamientos = new HashSet<>();
}
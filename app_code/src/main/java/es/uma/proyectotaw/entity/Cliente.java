package es.uma.proyectotaw.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "cliente")
public class Cliente {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "usuario_id", nullable = false)
    private Usuario usuario;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "dieta_id")
    private Dieta dieta;

    @Column(name = "entrenador_id")
    private Integer entrenadorId;

    @Column(name = "peso", nullable = false)
    private Float peso;

    @Column(name = "altura", nullable = false)
    private Integer altura;

    @Column(name = "edad", nullable = false)
    private Integer edad;

    @Column(name = "dietista_id")
    private Integer dietistaId;

    @OneToMany(mappedBy = "cliente")
    private List<ClienteRutina> clienteRutinas = new ArrayList<>();

    @OneToMany(mappedBy = "cliente")
    private List<Desempeno> desempenos = new ArrayList<>();

}
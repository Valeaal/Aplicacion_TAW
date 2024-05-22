package es.uma.proyectotaw.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "usuario")
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "email", nullable = false)
    private String email;

    @Column(name = "password", nullable = false)
    private String password;

    @Column(name = "nombre", nullable = false)
    private String nombre;

    @Column(name = "apellidos", nullable = false)
    private String apellidos;

    @Column(name = "fecha_nacimiento", nullable = false)
    private LocalDate fechaNacimiento;

    @Column(name = "pertenece_desde", nullable = false)
    private LocalDate perteneceDesde;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "tipo_usuario_id")
    private TipoUsuario tipoUsuario;

    @OneToMany(mappedBy = "usuario")
    private List<Cliente> clientes = new ArrayList<>();

    @OneToMany(mappedBy = "dietista")
    private List<Dieta> dietas = new ArrayList<>();

    @OneToMany(mappedBy = "entrenador")
    private List<Rutina> rutinas = new ArrayList<>();

}
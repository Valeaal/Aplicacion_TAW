//Autor: Álvaro Valencia Villalón
package es.uma.proyectotaw.entity;

import es.uma.proyectotaw.dto.DTO;
import es.uma.proyectotaw.dto.UsuarioDTO;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@Entity
@Table(name = "usuario")
public class Usuario implements DTO<UsuarioDTO> {
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

    public UsuarioDTO toDTO(){
        UsuarioDTO dto = new UsuarioDTO();
        dto.setId(id);
        dto.setEmail(email);
        dto.setPassword(password);
        dto.setNombre(nombre);
        dto.setApellidos(apellidos);
        dto.setFechaNacimiento(fechaNacimiento);
        dto.setPerteneceDesde(perteneceDesde);
        dto.setTipoUsuario(tipoUsuario.toDTO());
        return dto;
    }

}
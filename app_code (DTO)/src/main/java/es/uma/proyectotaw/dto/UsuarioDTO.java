//Autor: Álvaro Valencia Villalón
package es.uma.proyectotaw.dto;

import lombok.Data;

import java.time.LocalDate;

@Data
public class UsuarioDTO {

    private Integer id;
    private String email;
    private String password;
    private String nombre;
    private String apellidos;
    private LocalDate fechaNacimiento;
    private LocalDate perteneceDesde;
    private TipoUsuarioDTO tipoUsuario;

}

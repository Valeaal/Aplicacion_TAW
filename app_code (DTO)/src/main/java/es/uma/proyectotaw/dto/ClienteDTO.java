//Autor: Álvaro Valencia Villalón
package es.uma.proyectotaw.dto;

import lombok.Data;

@Data
public class ClienteDTO {

    private Integer id;
    private UsuarioDTO usuario;
    private UsuarioDTO entrenador;
    //private DietaDTO dieta;
    private Float peso;
    private Integer altura;
    private Integer edad;
    private UsuarioDTO dietista;

}

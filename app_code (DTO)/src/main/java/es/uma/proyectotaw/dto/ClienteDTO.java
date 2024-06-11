//Autor: Álvaro Valencia Villalón
package es.uma.proyectotaw.dto;

import lombok.Data;

@Data
public class ClienteDTO {

    private Integer id;
    private Integer usuario;
    private Integer entrenador;
    private Integer dieta;
    private Float peso;
    private Integer altura;
    private Integer edad;
    private Integer dietista;

}

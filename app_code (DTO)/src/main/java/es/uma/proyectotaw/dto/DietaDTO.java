//Autor: Álvaro Valencia Villalón
package es.uma.proyectotaw.dto;

import es.uma.proyectotaw.entity.DietaComida;
import es.uma.proyectotaw.entity.Usuario;
import lombok.Data;

import java.time.LocalDate;
import java.util.Set;

@Data
public class DietaDTO {

    private Integer id;
    private UsuarioDTO dietista;
    private String nombre;
    private String descripcion;
    private LocalDate fecha;
    private Set<DietaComidaDTO> dietas;
    private int calorias;

}

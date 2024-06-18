//Autor: Álvaro Valencia Villalón
package es.uma.proyectotaw.dto;

import lombok.Data;

import java.util.Set;

@Data
public class MenuDTO {

    public Integer id;
    public String nombre;
    public String descripcion;
    public String alergenos;
    public Set<ComidaMenuDTO> comidas;

}

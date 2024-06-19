package es.uma.proyectotaw.dto;

import es.uma.proyectotaw.entity.ComidaMenu;
import jakarta.persistence.Column;
import jakarta.persistence.Lob;
import jakarta.persistence.OneToMany;
import lombok.Data;

import java.util.HashSet;
import java.util.Set;
//autor: Alba de la Torre
@Data
public class ComidaDTO {
    private Integer id;
    private String nombre;
    private String descripcion;
    private Set<ComidaMenuDTO> menus = new HashSet<>();
}

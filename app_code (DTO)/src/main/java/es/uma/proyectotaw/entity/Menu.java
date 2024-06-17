//Autor: Álvaro Valencia Villalón
package es.uma.proyectotaw.entity;

import es.uma.proyectotaw.dto.ComidaMenuDTO;
import es.uma.proyectotaw.dto.DTO;
import es.uma.proyectotaw.dto.EjercicioEntrenamientoDTO;
import es.uma.proyectotaw.dto.MenuDTO;
import es.uma.proyectotaw.service.ComidaMenuService;
import es.uma.proyectotaw.service.EjercicioEntrenamientoService;
import es.uma.proyectotaw.service.MenuService;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "menu")
public class Menu implements DTO<MenuDTO> {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "nombre", nullable = false)
    private String nombre;

    @Lob
    @Column(name = "descripcion")
    private String descripcion;

    @Lob
    @Column(name = "alergenos")
    private String alergenos;

    @OneToMany(mappedBy = "menu")
    private Set<ComidaMenu> comidas = new HashSet<>();

    public MenuDTO toDTO(){
        MenuDTO menuDTO = new MenuDTO();
        menuDTO.setId(id);
        menuDTO.setNombre(nombre);
        if (this.descripcion != null){
            menuDTO.setDescripcion(descripcion);
        } else{
            menuDTO.setDescripcion(null);
        }
        if (this.alergenos != null){
            menuDTO.setAlergenos(alergenos);
        } else{
            menuDTO.setAlergenos(null);
        }

        // Instancia de DTOService, que nos proporciona la posibilidad convertir el conjunto a dto
        ComidaMenuService dtoService = new ComidaMenuService();

        // Convertir set a DTO con el servicio
        Set<ComidaMenuDTO> comidasMenuDTO = dtoService.entidadesADTO(comidas);
        menuDTO.setComidas(comidasMenuDTO);

        return menuDTO;
    }

}
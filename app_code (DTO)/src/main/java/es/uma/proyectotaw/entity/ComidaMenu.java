//Autor: Álvaro Valencia Villalón
package es.uma.proyectotaw.entity;

import es.uma.proyectotaw.dto.ComidaMenuDTO;
import es.uma.proyectotaw.dto.DTO;
import es.uma.proyectotaw.dto.EjercicioDTO;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "comida_menu")
public class ComidaMenu implements DTO<ComidaMenuDTO> {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "comida_id", nullable = false)
    private Comida comida;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "menu_id", nullable = false)
    private Menu menu;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "desempeno_id", nullable = false)
    private Desempeno desempeno;

    public ComidaMenuDTO toDTO(){
        ComidaMenuDTO dto = new ComidaMenuDTO();
        dto.setId(id);
        dto.setMenu(menu.getId());
        //TODO: Faltan setear el resto de los campos, que no he rellenado para que lo haga a su gusto al que le toque, yo necesitaba usar el menu.
        return dto;
    }

}
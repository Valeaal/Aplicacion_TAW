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

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "desempeno_id")
    private Desempeno desempeno;

    public ComidaMenuDTO toDTO(){
        ComidaMenuDTO dto = new ComidaMenuDTO();
        dto.setId(id);
        dto.setComida(comida.getId());
        dto.setMenu(menu.getId());
        if(desempeno != null){
            dto.setDesempeno(desempeno.getId());
        }
        return dto;
    }

}
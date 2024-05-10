package es.uma.proyectotaw.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "comida_menu")
public class ComidaMenu {
    @EmbeddedId
    private ComidaMenuId id;

    @MapsId("comidaId")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "comida_id", nullable = false)
    private Comida comida;

    @MapsId("menuId")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "menu_id", nullable = false)
    private Menu menu;

}
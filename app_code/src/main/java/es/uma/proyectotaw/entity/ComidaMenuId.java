package es.uma.proyectotaw.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.Hibernate;

import java.util.Objects;

@Getter
@Setter
@Embeddable
public class ComidaMenuId implements java.io.Serializable {
    private static final long serialVersionUID = 5227554477766075725L;
    @Column(name = "comida_id", nullable = false)
    private Integer comidaId;

    @Column(name = "menu_id", nullable = false)
    private Integer menuId;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        ComidaMenuId entity = (ComidaMenuId) o;
        return Objects.equals(this.menuId, entity.menuId) &&
                Objects.equals(this.comidaId, entity.comidaId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(menuId, comidaId);
    }

}
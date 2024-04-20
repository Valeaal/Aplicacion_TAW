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
public class DietaComidaId implements java.io.Serializable {
    private static final long serialVersionUID = 4675154880141611067L;
    @Column(name = "dieta_id", nullable = false)
    private Integer dietaId;

    @Column(name = "comida_id", nullable = false)
    private Integer comidaId;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        DietaComidaId entity = (DietaComidaId) o;
        return Objects.equals(this.dietaId, entity.dietaId) &&
                Objects.equals(this.comidaId, entity.comidaId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(dietaId, comidaId);
    }

}
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
public class EntrenamientoRutinaId implements java.io.Serializable {
    private static final long serialVersionUID = 4302673870850557979L;
    @Column(name = "entrenamiento_id", nullable = false)
    private Integer entrenamientoId;

    @Column(name = "rutina_id", nullable = false)
    private Integer rutinaId;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        EntrenamientoRutinaId entity = (EntrenamientoRutinaId) o;
        return Objects.equals(this.entrenamientoId, entity.entrenamientoId) &&
                Objects.equals(this.rutinaId, entity.rutinaId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(entrenamientoId, rutinaId);
    }

}
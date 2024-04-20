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
public class EjercicioEntrenamientoId implements java.io.Serializable {
    private static final long serialVersionUID = -3050344982154891382L;
    @Column(name = "ejercicio_id", nullable = false)
    private Integer ejercicioId;

    @Column(name = "entrenamiento_id", nullable = false)
    private Integer entrenamientoId;

    @Column(name = "desempeno_id", nullable = false)
    private Integer desempenoId;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        EjercicioEntrenamientoId entity = (EjercicioEntrenamientoId) o;
        return Objects.equals(this.entrenamientoId, entity.entrenamientoId) &&
                Objects.equals(this.desempenoId, entity.desempenoId) &&
                Objects.equals(this.ejercicioId, entity.ejercicioId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(entrenamientoId, desempenoId, ejercicioId);
    }

}
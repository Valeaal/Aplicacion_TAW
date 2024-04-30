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
public class ClienteRutinaId implements java.io.Serializable {
    private static final long serialVersionUID = -1667294334194492914L;
    @Column(name = "cliente_id", nullable = false)
    private Integer clienteId;

    @Column(name = "rutina_id", nullable = false)
    private Integer rutinaId;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        ClienteRutinaId entity = (ClienteRutinaId) o;
        return Objects.equals(this.clienteId, entity.clienteId) &&
                Objects.equals(this.rutinaId, entity.rutinaId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(clienteId, rutinaId);
    }

}
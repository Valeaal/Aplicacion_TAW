package es.uma.proyectotaw.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "dieta_comida")
public class DietaComida {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "dieta_id", nullable = false)
    private Dieta dieta;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "comida_id", nullable = false)
    private Comida comida;

    @Column(name = "momento_dia", nullable = false)
    private Integer momentoDia;

}
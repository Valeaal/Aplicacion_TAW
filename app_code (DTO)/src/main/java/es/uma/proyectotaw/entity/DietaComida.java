package es.uma.proyectotaw.entity;

import es.uma.proyectotaw.dto.DTO;
import es.uma.proyectotaw.dto.DietaComidaDTO;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "dieta_comida")
public class DietaComida implements DTO<DietaComidaDTO> {
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

    @Column(name = "dia", nullable = false)
    private Integer dia;

    public DietaComidaDTO toDTO(){
        DietaComidaDTO comida = new DietaComidaDTO();
        comida.setId(this.id);
        comida.setDieta(this.dieta.getId());
        comida.setComida(this.comida.getId());
        comida.setMomentoDia(this.momentoDia);
        return comida;
    }
}
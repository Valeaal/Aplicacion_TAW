package es.uma.proyectotaw.entity;

import es.uma.proyectotaw.dto.DTO;
import es.uma.proyectotaw.dto.EntrenamientoRutinaDTO;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "entrenamiento_rutina")
public class EntrenamientoRutina implements DTO<EntrenamientoRutinaDTO> {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "entrenamiento_id", nullable = false)
    private Entrenamiento entrenamiento;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "rutina_id", nullable = false)
    private Rutina rutina;

    @Column(name = "dia_semana", nullable = false)
    private Integer diaSemana;

    public EntrenamientoRutinaDTO toDTO(){
        EntrenamientoRutinaDTO entrenamientoRutinaDTO = new EntrenamientoRutinaDTO();
        entrenamientoRutinaDTO.setId(this.id);
        entrenamientoRutinaDTO.setEntrenamiento(this.entrenamiento.getId());
        entrenamientoRutinaDTO.setRutina(this.rutina.getId());
        entrenamientoRutinaDTO.setDiaSemana(this.diaSemana);
        return entrenamientoRutinaDTO;
    }
}
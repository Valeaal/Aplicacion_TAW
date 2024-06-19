//Autor: Álvaro Valencia Villalón
package es.uma.proyectotaw.entity;

import es.uma.proyectotaw.dto.DTO;
import es.uma.proyectotaw.dto.EjercicioEntrenamientoDTO;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "ejercicio_entrenamiento")
public class EjercicioEntrenamiento implements DTO<EjercicioEntrenamientoDTO>{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "ejercicio_id", nullable = false)
    private Ejercicio ejercicio;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "entrenamiento_id", nullable = false)
    private Entrenamiento entrenamiento;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "desempeno_id")
    private Desempeno desempeno;

    @Column(name = "series", nullable = true)
    private Integer series;

    @Column(name = "repeticiones", nullable = true)
    private Integer repeticiones;

    @Column(name = "peso", nullable = true)
    private Float peso;

    @Column(name = "tiempo")
    private Integer tiempo;

    @Column(name = "distancia")
    private Float distancia;

    @Column(name = "orden", nullable = false)
    private Integer orden;

    public EjercicioEntrenamientoDTO toDTO() {
        EjercicioEntrenamientoDTO dto = new EjercicioEntrenamientoDTO();
        dto.setId(id);
        dto.setEjercicio(ejercicio.toDTO());
        dto.setEntrenamiento(entrenamiento.toDTO());
        if(desempeno != null) {
            dto.setDesempeno(desempeno.getId());
        }
        dto.setSeries(series);
        dto.setRepeticiones(repeticiones);
        dto.setPeso(peso);
        dto.setTiempo(tiempo);
        dto.setDistancia(distancia);
        dto.setOrden(orden);
        return dto;
    }

}
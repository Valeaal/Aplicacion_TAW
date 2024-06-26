package es.uma.proyectotaw.entity;

import es.uma.proyectotaw.dto.DTO;
import es.uma.proyectotaw.dto.EjercicioDTO;
import es.uma.proyectotaw.dto.EjercicioEntrenamientoDTO;
import es.uma.proyectotaw.service.EjercicioEntrenamientoService;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;
import java.util.HashSet;

@Getter
@Setter
@Entity
@Table(name = "ejercicio")
public class Ejercicio implements DTO<EjercicioDTO> {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "nombre", nullable = false)
    private String nombre;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "tipo", nullable = false)
    private TipoEjercicio tipo;

    @Lob
    @Column(name = "descripcion")
    private String descripcion;

    @Lob
    @Column(name = "url_video")
    private String urlVideo;

    @ManyToOne(fetch = FetchType.LAZY, optional = true)
    @JoinColumn(name = "grupo_muscular_id", nullable = true)
    private GrupoMuscular grupoMuscular;

    @OneToMany(mappedBy = "ejercicio")
    private Set<EjercicioEntrenamiento> entrenamientos = new HashSet<>();

    public EjercicioDTO toDTO() {
        EjercicioDTO ejercicioDTO = new EjercicioDTO();
        ejercicioDTO.setId(id);
        ejercicioDTO.setNombre(nombre);
        ejercicioDTO.setTipo(this.tipo.toDTO());
        if (descripcion != null) {
            ejercicioDTO.setDescripcion(descripcion);
        }
        if (urlVideo != null) {
            ejercicioDTO.setUrlVideo(urlVideo);
        }
        if (grupoMuscular != null) {
            ejercicioDTO.setGrupoMuscular(grupoMuscular.toDTO());
        }

        Set EjerciciosEntrenamientoIds = new HashSet();
        for (EjercicioEntrenamiento e : this.getEntrenamientos()){
            EjerciciosEntrenamientoIds.add(e.getId());
        }
        ejercicioDTO.setEntrenamientos(EjerciciosEntrenamientoIds);

        return ejercicioDTO;
    }
}

package es.uma.proyectotaw.entity;

import es.uma.proyectotaw.dto.ClienteRutinaDTO;
import es.uma.proyectotaw.dto.DTO;
import es.uma.proyectotaw.dto.RutinaDTO;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "rutina")
public class Rutina implements DTO<RutinaDTO> {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "nombre", nullable = false)
    private String nombre;

    @Lob
    @Column(name = "descripcion")
    private String descripcion;

    @Column(name = "fecha_creacion", nullable = false)
    private LocalDate fechaCreacion;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "entrenador_id", nullable = false)
    private Usuario entrenador;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "tipo_rutina")
    private TipoRutina tipoRutina;

    @OneToMany(mappedBy = "rutina")
    private Set<ClienteRutina> clientes = new HashSet<>();

    @OneToMany(mappedBy = "rutina")
    private Set<EntrenamientoRutina> entrenamientos = new HashSet<>();

    public RutinaDTO toDTO(){
        RutinaDTO rutinaDTO = new RutinaDTO();
        rutinaDTO.setId(this.id);
        rutinaDTO.setNombre(this.nombre);
        rutinaDTO.setDescripcion(this.descripcion);
        rutinaDTO.setFechaCreacion(this.fechaCreacion);

        if (this.entrenador != null) {  //Podría ser null
            rutinaDTO.setEntrenador(this.entrenador.toDTO());
        }
        if (this.tipoRutina != null) {
            rutinaDTO.setTipoRutina(this.tipoRutina.toDTO());
        }
        Set<Integer> lista = new HashSet<>();
        for (ClienteRutina cliente : clientes) {
            lista.add(cliente.getId());
        }
        rutinaDTO.setClientes(lista);
        lista.clear();
        for (EntrenamientoRutina er : entrenamientos) {
            lista.add(er.getId());
        }
        rutinaDTO.setEntrenamientos(lista);
        return rutinaDTO;
    }

}
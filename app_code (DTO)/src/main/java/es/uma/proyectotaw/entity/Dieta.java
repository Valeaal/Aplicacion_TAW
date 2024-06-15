//Autor: Álvaro Valencia Villalón
package es.uma.proyectotaw.entity;

import es.uma.proyectotaw.dto.DTO;
import es.uma.proyectotaw.dto.DietaDTO;
import es.uma.proyectotaw.dto.UsuarioDTO;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "dieta")
public class Dieta implements DTO<DietaDTO> {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "dietista_id", nullable = false)
    private Usuario dietista;

    @Column(name = "nombre", nullable = false)
    private String nombre;

    @Lob
    @Column(name = "descripcion")
    private String descripcion;

    @Column(name = "fecha", nullable = false)
    private LocalDate fecha;

    @OneToMany(mappedBy = "dieta")
    private Set<DietaComida> dietas = new HashSet<>();

    @Column(name = "calorias", nullable = false)
    private int calorias;

    public DietaDTO toDTO(){
        DietaDTO dto = new DietaDTO();
        dto.setId(id);
        dto.setDietista(dietista.toDTO());
        dto.setNombre(nombre);
        dto.setDescripcion(descripcion);
        dto.setFecha(fecha);
        //TODO: setDietas, depende de la implemtación de DietaComidaDTO, para más info ver DietaDTO
        dto.setCalorias(calorias);
        return dto;
    }



}
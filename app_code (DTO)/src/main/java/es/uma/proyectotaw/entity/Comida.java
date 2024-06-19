package es.uma.proyectotaw.entity;

import es.uma.proyectotaw.dto.ComidaDTO;
import es.uma.proyectotaw.dto.ComidaMenuDTO;
import es.uma.proyectotaw.dto.EjercicioEntrenamientoDTO;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "comida")
public class Comida {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "nombre", nullable = false)
    private String nombre;

    @Lob
    @Column(name = "descripcion")
    private String descripcion;

    @OneToMany(mappedBy = "comida")
    private Set<ComidaMenu> menus = new HashSet<>();

    public ComidaDTO toDTO() {
        ComidaDTO comidaDTO = new ComidaDTO();
        comidaDTO.setId(id);
        comidaDTO.setNombre(nombre);
        comidaDTO.setDescripcion(descripcion);
        Set<ComidaMenuDTO> lista = new HashSet<>();
        for (ComidaMenu comida : menus) {
            lista.add(comida.toDTO());
        }
        comidaDTO.setMenus(lista);
        return comidaDTO;
    }

}
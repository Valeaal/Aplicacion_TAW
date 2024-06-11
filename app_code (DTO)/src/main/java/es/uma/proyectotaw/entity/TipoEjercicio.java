//Autor: Álvaro Valencia Villalón
package es.uma.proyectotaw.entity;

import es.uma.proyectotaw.dao.TipoEjercicioRepository;
import es.uma.proyectotaw.dto.DTO;
import es.uma.proyectotaw.dto.TipoEjercicioDTO;
import es.uma.proyectotaw.dto.TipoUsuarioDTO;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "tipo_ejercicio")
public class TipoEjercicio implements DTO<TipoEjercicioDTO> {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Lob
    @Column(name = "tipo", nullable = false)
    private String tipo;

    public TipoEjercicioDTO toDTO(){
        TipoEjercicioDTO dto = new TipoEjercicioDTO();
        dto.setId(id);
        dto.setTipo(tipo);
        return dto;
    }

}
//Autor: Álvaro Valencia Villalón
package es.uma.proyectotaw.entity;

import es.uma.proyectotaw.dto.DTO;
import es.uma.proyectotaw.dto.TipoUsuarioDTO;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@Entity
@Table(name = "tipo_usuario")
public class TipoUsuario implements DTO<TipoUsuarioDTO> {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Lob
    @Column(name = "tipo", nullable = false)
    private String tipo;

    public TipoUsuarioDTO toDTO(){
        TipoUsuarioDTO dto = new TipoUsuarioDTO();
        dto.setId(id);
        dto.setTipo(tipo);
        return dto;
    }

}
//Autor: Álvaro Valencia Villalón
package es.uma.proyectotaw.entity;

import es.uma.proyectotaw.dto.DTO;
import es.uma.proyectotaw.dto.GrupoMuscularDTO;
import es.uma.proyectotaw.dto.TipoEjercicioDTO;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "grupo_muscular")
public class GrupoMuscular implements DTO<GrupoMuscularDTO> {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Lob
    @Column(name = "grupo", nullable = false)
    private String grupo;

    public GrupoMuscularDTO toDTO(){
        GrupoMuscularDTO dto = new GrupoMuscularDTO();
        dto.setId(id);
        dto.setGrupo(grupo);
        return dto;
    }

}
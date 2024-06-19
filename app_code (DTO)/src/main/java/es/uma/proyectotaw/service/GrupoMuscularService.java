//Autor: Álvaro Valencia Vilallón
package es.uma.proyectotaw.service;

import es.uma.proyectotaw.dao.GrupoMuscularRepository;
import es.uma.proyectotaw.dto.GrupoMuscularDTO;
import es.uma.proyectotaw.entity.GrupoMuscular;
import es.uma.proyectotaw.entity.TipoEjercicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GrupoMuscularService extends DTOService<GrupoMuscularDTO, GrupoMuscular> {

    @Autowired
    GrupoMuscularRepository grupoMuscularRepository;

    public List<GrupoMuscularDTO> findAll(){
        return this.entidadesADTO(grupoMuscularRepository.findAll());
    }

    public Integer buscarPorString (String grupo){
        return grupoMuscularRepository.buscarPorString(grupo);
    }

}

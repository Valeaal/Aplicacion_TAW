//Autor: Álvaro Valencia Villalón
package es.uma.proyectotaw.service;

import es.uma.proyectotaw.dao.EjercicioRepository;
import es.uma.proyectotaw.dto.EjercicioDTO;
import es.uma.proyectotaw.entity.Ejercicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EjercicioService extends DTOService<EjercicioDTO, Ejercicio>{

    @Autowired
    EjercicioRepository ejercicioRepository;

    public List<EjercicioDTO> findAll() {
        return this.entidadesADTO(ejercicioRepository.findAll());
    }

    public List<EjercicioDTO> filtrarEjercicios(String inputNombre, Integer inputTipo, Integer inputGrupo){
        return this.entidadesADTO(ejercicioRepository.filtrarEjercicios(inputNombre, inputTipo, inputGrupo));
    }

}

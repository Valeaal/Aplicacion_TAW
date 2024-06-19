//Autor: Álvaro Valencia Vilallón
package es.uma.proyectotaw.service;

import es.uma.proyectotaw.dao.EjercicioEntrenamientoRepository;
import es.uma.proyectotaw.dto.EjercicioEntrenamientoDTO;
import es.uma.proyectotaw.entity.EjercicioEntrenamiento;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EjercicioEntrenamientoService extends DTOService<EjercicioEntrenamientoDTO, EjercicioEntrenamiento> {

    @Autowired
    private EjercicioEntrenamientoRepository ejercicioEntrenamientoRepository;

    public EjercicioEntrenamientoDTO getReferenceById(Integer id) {
        return ejercicioEntrenamientoRepository.getReferenceById(id).toDTO();
    }

    public EjercicioEntrenamiento getEjercicioEntrenamientoFromEjAndEntrenamientoId(Integer ejId, Integer entrenamientoId) {
        return ejercicioEntrenamientoRepository.getEjercicioEntrenamientoFromEjAndEntrenamientoId(ejId, entrenamientoId);
    }

    public List<EjercicioEntrenamientoDTO> findAll(){
        return this.entidadesADTO(ejercicioEntrenamientoRepository.findAll());
    }

}

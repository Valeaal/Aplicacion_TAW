package es.uma.proyectotaw.service;

import es.uma.proyectotaw.dao.EntrenamientoRepository;
import es.uma.proyectotaw.dto.EntrenamientoDTO;
import es.uma.proyectotaw.entity.Entrenamiento;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

//autor : Alba de la Torre
@Service
public class EntrenamientoService extends DTOService<EntrenamientoDTO, Entrenamiento>{

    @Autowired
    private EntrenamientoRepository entrenamientoRepository;

    public List<EntrenamientoDTO> findByRutinaId(Integer rutinaId) {
        List<Entrenamiento> er = entrenamientoRepository.findByRutinaId(rutinaId);
        return this.entidadesADTO(er);
    }

    public Entrenamiento getReferenceById(Integer id) {
        return entrenamientoRepository.getReferenceById(id);
    }
}

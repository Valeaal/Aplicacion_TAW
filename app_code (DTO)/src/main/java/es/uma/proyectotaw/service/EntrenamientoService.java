package es.uma.proyectotaw.service;

import es.uma.proyectotaw.dao.EntrenamientoRepository;
import es.uma.proyectotaw.entity.Entrenamiento;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

//autor : Alba de la Torre
@Service
public class EntrenamientoService {

    @Autowired
    private EntrenamientoRepository entrenamientoRepository;

    public List<Entrenamiento> findByRutinaId(Integer rutinaId) {
        return entrenamientoRepository.findByRutinaId(rutinaId);
    }

    public Entrenamiento getReferenceById(Integer id) {
        return entrenamientoRepository.getReferenceById(id);
    }
}

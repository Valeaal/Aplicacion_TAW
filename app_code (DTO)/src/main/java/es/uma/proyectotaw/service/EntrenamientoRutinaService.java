package es.uma.proyectotaw.service;

import es.uma.proyectotaw.dao.EntrenamientoRutinaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EntrenamientoRutinaService {

    @Autowired
    private EntrenamientoRutinaRepository entrenamientoRutinaRepository;

    public int getdiaSemanaFromRutinaAndEntrenamientoId(int rutinaId, int entrenamientoId) {
        return entrenamientoRutinaRepository.getdiaSemanaFromRutinaAndEntrenamientoId(rutinaId, entrenamientoId);
    }
}

package es.uma.proyectotaw.service;

import es.uma.proyectotaw.dao.EntrenamientoRepository;
import es.uma.proyectotaw.dao.EntrenamientoRutinaRepository;
import es.uma.proyectotaw.dao.RutinaRepository;
import es.uma.proyectotaw.dto.ClienteDTO;
import es.uma.proyectotaw.dto.EntrenamientoRutinaDTO;
import es.uma.proyectotaw.dto.RutinaDTO;
import es.uma.proyectotaw.entity.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
// autor: Alba de la Torre

@Service
public class EntrenamientoRutinaService extends DTOService<EntrenamientoRutinaDTO, EntrenamientoRutina> {

    @Autowired
    private EntrenamientoRutinaRepository entrenamientoRutinaRepository;
    @Autowired
    private EntrenamientoRepository entrenamientoRepository;
    @Autowired
    private RutinaRepository rutinaRepository;

    public int getdiaSemanaFromRutinaAndEntrenamientoId(int rutinaId, int entrenamientoId) {
        return entrenamientoRutinaRepository.getdiaSemanaFromRutinaAndEntrenamientoId(rutinaId, entrenamientoId);
    }

    public List<EntrenamientoRutinaDTO> buscarEntrenamientosdeRutina(Integer n) { // pablo
        List<EntrenamientoRutina> rutinas = entrenamientoRutinaRepository.buscarEntrenamientosdeRutina(n);
        return this.entidadesADTO(rutinas);
    }

    public EntrenamientoRutinaDTO getEntrenamientoRutina(Integer id) { //pablo
        EntrenamientoRutina er = this.entrenamientoRutinaRepository.findById(id).orElse(null);
        return er.toDTO();
    }

    public void guardarEntrenamientodeRutina(EntrenamientoRutinaDTO ent) { //pablo
        EntrenamientoRutina entrenamiento = entrenamientoRutinaRepository.findById(ent.getId()).orElse(new EntrenamientoRutina());
        Entrenamiento e = entrenamientoRepository.getReferenceById(ent.getEntrenamiento());
        entrenamiento.setEntrenamiento(e);
        Rutina r = rutinaRepository.findById(ent.getRutina()).orElse(null);
        entrenamiento.setRutina(r);
        entrenamiento.setDiaSemana(ent.getDiaSemana());
        this.entrenamientoRutinaRepository.save(entrenamiento);
    }

    public void borrarEntrenamientodeRutina(EntrenamientoRutinaDTO rutina) { //pablo
        EntrenamientoRutina EntrenamientoBorrar = new EntrenamientoRutina();
        EntrenamientoBorrar.setId(rutina.getId());

        this.entrenamientoRutinaRepository.delete(EntrenamientoBorrar);
    }
}

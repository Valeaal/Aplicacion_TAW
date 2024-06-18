package es.uma.proyectotaw.service;

import es.uma.proyectotaw.dao.EntrenamientoRepository;
import es.uma.proyectotaw.dao.EntrenamientoRutinaRepository;
import es.uma.proyectotaw.dto.EntrenamientoDTO;
import es.uma.proyectotaw.entity.Entrenamiento;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

//autor : Alba de la Torre
//autor:  Miguel Galdeano Rodríguez
@Service
public class EntrenamientoService {

    @Autowired
    private EntrenamientoRepository entrenamientoRepository;

    @Autowired
    private EntrenamientoRutinaRepository entrenamientoRutinaRepository;

    public List<Entrenamiento> findAll(){
        List<Entrenamiento> entrenamientos = this.entrenamientoRepository.findAll();
        return this.entidadesADTO(entrenamientos);
    }

    public List<Entrenamiento> findByRutinaId(Integer rutinaId) {
        return entrenamientoRepository.findByRutinaId(rutinaId);
    }

    public Entrenamiento getReferenceById(Integer id) {
        return entrenamientoRepository.getReferenceById(id);
    }

    public void guardar(EntrenamientoDTO entrenamientoDTO) {
        Entrenamiento entrenamiento = this.entrenamientoRepository.findById(entrenamientoDTO.getId()).orElse(new Entrenamiento());
        entrenamiento.setNombre(entrenamientoDTO.getNombre());
        entrenamiento.setDescripcion(entrenamientoDTO.getDescripcion());
        //entrenamiento.setRutinas(this.entrenamientoRutinaRepository.findAllById(entrenamientoDTO.getRutinas()));



        this.entrenamientoRepository.save(entrenamiento);
    }

    protected List<Entrenamiento> entidadesADTO (List<Entrenamiento> entrenamientos) {
        List<Entrenamiento> lista = new ArrayList<>();
        for (Entrenamiento entrenamiento : entrenamientos) {
            lista.add(entrenamiento.toDTO());
        }
        return lista;
    }
}

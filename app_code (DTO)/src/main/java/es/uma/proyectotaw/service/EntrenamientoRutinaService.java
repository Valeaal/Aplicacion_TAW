package es.uma.proyectotaw.service;

import es.uma.proyectotaw.dao.EntrenamientoRepository;
import es.uma.proyectotaw.dao.EntrenamientoRutinaRepository;
import es.uma.proyectotaw.dao.RutinaRepository;
import es.uma.proyectotaw.dto.EntrenamientoRutinaDTO;
import es.uma.proyectotaw.entity.EntrenamientoRutina;
import org.hibernate.type.descriptor.java.JdbcTimeJavaType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
// autor: Alba de la Torre
//autor: Miguel Galdeano Rodríguez

@Service
public class EntrenamientoRutinaService extends DTOService<EntrenamientoRutinaDTO, EntrenamientoRutina> {

    @Autowired
    private EntrenamientoRutinaRepository entrenamientoRutinaRepository;
    @Autowired
    EntrenamientoRepository entrenamientoRepository;
    @Autowired
    RutinaRepository rutinaRepository;

    public int getdiaSemanaFromRutinaAndEntrenamientoId(int rutinaId, int entrenamientoId) {
        return entrenamientoRutinaRepository.getdiaSemanaFromRutinaAndEntrenamientoId(rutinaId, entrenamientoId);
    }

    public List<Integer> findByRutinaId(int rutinaId) {
        List<Integer> entrenamientos = this.entrenamientoRutinaRepository.findByRutinaId(rutinaId);
        return entrenamientos;
    }

    public EntrenamientoRutinaDTO findById(int erID) {
        return this.entrenamientoRutinaRepository.findById(erID).orElse(null).toDTO();
    }

    public List<EntrenamientoRutinaDTO> findAll(){
        return this.entidadesADTO(this.entrenamientoRutinaRepository.findAll());
    }

    public void delete(Integer id){
        this.entrenamientoRutinaRepository.deleteById(id);
    }

    public void save(EntrenamientoRutinaDTO dto) {
        EntrenamientoRutina entrenamientoRutina = new EntrenamientoRutina();
        entrenamientoRutina.setEntrenamiento(this.entrenamientoRepository.findById(dto.getEntrenamiento()).orElse(null));
        entrenamientoRutina.setRutina(this.rutinaRepository.findById(dto.getRutina()).orElse(null));
        entrenamientoRutina.setDiaSemana(dto.getDiaSemana());
        entrenamientoRutinaRepository.save(entrenamientoRutina);
    }

    public void deleteAllById(List<Integer> id){
        this.entrenamientoRutinaRepository.deleteAllById(id);
    }
}

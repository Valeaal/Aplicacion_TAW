package es.uma.proyectotaw.service;

import es.uma.proyectotaw.dao.Tipo_RutinaRepository;
import es.uma.proyectotaw.dto.TipoRutinaDTO;
import es.uma.proyectotaw.entity.TipoRutina;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class TipoRutinaService extends DTOService<TipoRutinaDTO, TipoRutina>{

    @Autowired
    Tipo_RutinaRepository tipoRutinaRepository;

    public List<TipoRutinaDTO> findAll(){
        return this.entidadesADTO(tipoRutinaRepository.findAll());
    }

    public TipoRutinaDTO findById(Integer id){
        return this.tipoRutinaRepository.findById(id).orElse(null).toDTO();
    }

}

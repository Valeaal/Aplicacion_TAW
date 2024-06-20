//pablo alonso burgos
package es.uma.proyectotaw.service;

import es.uma.proyectotaw.dao.Tipo_RutinaRepository;
import es.uma.proyectotaw.dto.RutinaDTO;
import es.uma.proyectotaw.dto.TipoRutinaDTO;
import es.uma.proyectotaw.entity.Rutina;
import es.uma.proyectotaw.entity.TipoRutina;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TipoRutinaService extends DTOService<TipoRutinaDTO, TipoRutina> {
    @Autowired
    private Tipo_RutinaRepository tipo_RutinaRepository;
    public TipoRutinaDTO getTipoCrossfit() {
        TipoRutina tr = this.tipo_RutinaRepository.findById(2).orElse(null);
        return tr.toDTO();
    }
}

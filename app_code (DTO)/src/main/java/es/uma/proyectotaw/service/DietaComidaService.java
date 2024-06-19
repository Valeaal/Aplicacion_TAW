package es.uma.proyectotaw.service;

import es.uma.proyectotaw.dao.DietaRepository;
import es.uma.proyectotaw.dto.DietaComidaDTO;
import es.uma.proyectotaw.entity.DietaComida;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;

// autor: Alba de la Torre
@Service
public class DietaComidaService extends DTOService<DietaComidaDTO, DietaComida> {
    @Autowired
    private DietaRepository dietaRepository;

    public Set<DietaComidaDTO> getComidaDietaByDietaId(Integer id){
        Set<DietaComida> comida= dietaRepository.getComidaDietaByDietaId(id);
        return this.entidadesADTO(comida);
    }
}

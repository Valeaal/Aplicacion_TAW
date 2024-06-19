package es.uma.proyectotaw.service;

import es.uma.proyectotaw.dao.DietaRepository;
import es.uma.proyectotaw.dto.ComidaDTO;
import es.uma.proyectotaw.dto.DTO;
import es.uma.proyectotaw.dto.DietaComidaDTO;
import es.uma.proyectotaw.dto.DietaDTO;
import es.uma.proyectotaw.entity.Comida;
import es.uma.proyectotaw.entity.Dieta;
import es.uma.proyectotaw.entity.DietaComida;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class DietaService extends DTOService<DietaDTO, Dieta> {
    @Autowired
    private DietaRepository dietaRepository;

//    public Set<DietaComidaDTO> getComidaDietaByDietaId(Integer id){
//        Set<DietaComida> comida= dietaRepository.getComidaDietaByDietaId(id);
//        return this.entidadesADTO(comida);
//    }

//    public List<ComidaDTO> findComidasByDietaId(Integer id){
//        return this.entidadesADTO(dietaRepository.findComidasByDietaId(id));
//    }

    public List<Dieta> getDietaByClientId(Integer id){
        return dietaRepository.getDietaByClientId(id);
    }
}

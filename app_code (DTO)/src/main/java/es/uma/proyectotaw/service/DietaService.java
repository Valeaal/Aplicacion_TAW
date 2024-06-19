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
// autor: Alba de la Torre
@Service
public class DietaService extends DTOService<DietaDTO, Dieta> {
    @Autowired
    private DietaRepository dietaRepository;

    public List<DietaDTO> getDietaByClientId(Integer id){
        return this.entidadesADTO(dietaRepository.getDietaByClientId(id));
    }
}

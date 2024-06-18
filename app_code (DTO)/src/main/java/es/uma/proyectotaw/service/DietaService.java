package es.uma.proyectotaw.service;

import es.uma.proyectotaw.dao.DietaRepository;
import es.uma.proyectotaw.entity.Comida;
import es.uma.proyectotaw.entity.Dieta;
import es.uma.proyectotaw.entity.DietaComida;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
public class DietaService {
    @Autowired
    private DietaRepository dietaRepository;

    public Set<DietaComida> getComidaDietaByDietaId(Integer id){
        return dietaRepository.getComidaDietaByDietaId(id);
    }

    public List<Comida> findComidasByDietaId(Integer id){
        return dietaRepository.findComidasByDietaId(id);
    }

    public List<Dieta> getDietaByClientId(Integer id){
        return dietaRepository.getDietaByClientId(id);
    }
}

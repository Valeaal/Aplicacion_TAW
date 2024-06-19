package es.uma.proyectotaw.service;

import es.uma.proyectotaw.dao.ComidaRepository;
import es.uma.proyectotaw.dao.DietaRepository;
import es.uma.proyectotaw.dto.ComidaDTO;
import es.uma.proyectotaw.entity.Comida;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
//autor: Alba de la Torre

@Service
public class ComidaService extends DTOService<ComidaDTO, Comida>{

    @Autowired
    private ComidaRepository comidaRepository;

    @Autowired
    private DietaRepository dietaRepository;

    public ComidaDTO getReferenceById(Integer id){
        return comidaRepository.getReferenceById(id).toDTO();
    }

    public Comida getEntityById(Integer id){
        return comidaRepository.getReferenceById(id);
    }

    public List<Comida> findByDietaId(Integer id){
        return comidaRepository.findByDietaId(id);
    }

    public List<ComidaDTO> findComidasByDietaId(Integer id){
        List<Comida> comidas = dietaRepository.findComidasByDietaId(id);
        return this.entidadesADTO(comidas);
    }
}

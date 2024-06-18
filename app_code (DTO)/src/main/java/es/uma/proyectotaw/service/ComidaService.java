package es.uma.proyectotaw.service;

import es.uma.proyectotaw.dao.ComidaRepository;
import es.uma.proyectotaw.entity.Comida;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
//autor: Alba de la Torre

@Service
public class ComidaService {

    @Autowired
    private ComidaRepository comidaRepository;

    public Comida getReferenceById(Integer id){
        return comidaRepository.getReferenceById(id);
    }

    public List<Comida> findByDietaId(Integer id){
        return comidaRepository.findByDietaId(id);
    }
}

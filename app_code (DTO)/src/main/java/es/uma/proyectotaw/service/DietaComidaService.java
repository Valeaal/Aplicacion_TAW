package es.uma.proyectotaw.service;

import es.uma.proyectotaw.dao.ComidaRepository;
import es.uma.proyectotaw.dao.DietaRepository;
import es.uma.proyectotaw.dao.Dieta_ComidaRepository;
import es.uma.proyectotaw.dto.ClienteDTO;
import es.uma.proyectotaw.dto.DietaComidaDTO;
import es.uma.proyectotaw.dto.DietaDTO;
import es.uma.proyectotaw.entity.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

// autor: Alba de la Torre y Javier Torrecilla
@Service
public class DietaComidaService extends DTOService<DietaComidaDTO, DietaComida> {
    @Autowired
    private DietaRepository dietaRepository;

    @Autowired
    private Dieta_ComidaRepository dietaComidaRepository;

    @Autowired
    private ComidaRepository comidaRepository;

    public Set<DietaComidaDTO> getComidaDietaByDietaId(Integer id){
        Set<DietaComida> comida= dietaRepository.getComidaDietaByDietaId(id);
        return this.entidadesADTO(comida);
    }

    public List<DietaComidaDTO> getAll(){
        return this.entidadesADTO(dietaComidaRepository.findAll());
    }

    public void deleteDietaComida(Integer dieta){
        DietaComida dc = dietaComidaRepository.getReferenceById(dieta);
        dietaComidaRepository.delete(dc);
    }

    public void guardarDietaComida(Integer idDieta, Integer idComida, Integer dia, Integer momentoDia) {
        Dieta dieta = dietaRepository.getReferenceById(idDieta);
        Comida comida = comidaRepository.getReferenceById(idComida);

        DietaComida dietaComida = new DietaComida();
        dietaComida.setDieta(dieta);
        dietaComida.setComida(comida);
        dietaComida.setDia(dia);
        dietaComida.setMomentoDia(momentoDia);

        dietaComidaRepository.save(dietaComida);
    }

}

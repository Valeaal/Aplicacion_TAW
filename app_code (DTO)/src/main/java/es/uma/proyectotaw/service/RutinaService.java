package es.uma.proyectotaw.service;

import es.uma.proyectotaw.dao.RutinaRepository;
import es.uma.proyectotaw.dto.RutinaDTO;
import es.uma.proyectotaw.entity.Rutina;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

// autor: Alba de la Torre

@Service
public class RutinaService extends DTOService<RutinaDTO, Rutina>{
    @Autowired
    RutinaRepository rutinaRepository;

    public RutinaDTO getActiveRutinasByClienteId(Integer idCliente) {
        Rutina rutina = rutinaRepository.getActiveRutinasByClienteId(idCliente).get(0);
        return rutina.toDTO();
    }

    public List<RutinaDTO> getRutinasByNameAndClientId(Integer clientId, String name){
        List<Rutina> lista = rutinaRepository.getRutinasByNameAndClientId(clientId, name);
        return this.entidadesADTO(lista);
    }

    public List<Rutina> getAllRutinasByClienteId(Integer clientId){
        return rutinaRepository.getAllRutinasByClienteId(clientId);
    }
}
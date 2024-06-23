//Autor: Álvaro Valencia Villalón
// Alba de la Torre
package es.uma.proyectotaw.service;

import es.uma.proyectotaw.dao.ComidaMenuRepository;
import es.uma.proyectotaw.dto.ComidaMenuDTO;
import es.uma.proyectotaw.entity.ComidaMenu;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ComidaMenuService extends DTOService<ComidaMenuDTO, ComidaMenu>{
    @Autowired
    private ComidaMenuRepository comidaMenuRepository;
    public ComidaMenuDTO getComidaMenuDTO(Integer idComidaMenu) {
        return comidaMenuRepository.getReferenceById(idComidaMenu).toDTO();
    }

    public ComidaMenuDTO getByDesempeno(Integer id){
        return comidaMenuRepository.getComidaMenuPorDesempeno(id).toDTO();
    }
}

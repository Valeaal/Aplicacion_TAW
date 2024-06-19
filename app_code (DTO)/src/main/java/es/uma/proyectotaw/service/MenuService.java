//Autor: Álvaro Valencia Vilallón
//Quien sea que se añada al porcentaje de realización

package es.uma.proyectotaw.service;

import es.uma.proyectotaw.dao.MenuRepository;
import es.uma.proyectotaw.dto.MenuDTO;
import es.uma.proyectotaw.entity.Menu;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MenuService extends DTOService<MenuDTO, Menu>{

    @Autowired
    MenuRepository menuRepository;

    public List<MenuDTO> findAll(){
        return this.entidadesADTO(menuRepository.findAll());
    }

    public List<MenuDTO> filtrarMenus(String inputNombre, String inputAlergenos){
        return this.entidadesADTO(menuRepository.filtrarMenus(inputNombre, inputAlergenos));
    }

    public MenuDTO getReferenceById(Integer id){
        return menuRepository.getReferenceById(id).toDTO();
    }


}

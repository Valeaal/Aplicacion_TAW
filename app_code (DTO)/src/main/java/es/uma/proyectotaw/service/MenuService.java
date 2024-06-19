//Autor: Álvaro Valencia Vilallón
//Quien sea que se añada al porcentaje de realización

package es.uma.proyectotaw.service;

import es.uma.proyectotaw.dao.MenuRepository;
import es.uma.proyectotaw.dto.ComidaMenuDTO;
import es.uma.proyectotaw.dto.MenuDTO;
import es.uma.proyectotaw.entity.ComidaMenu;
import es.uma.proyectotaw.entity.Ejercicio;
import es.uma.proyectotaw.entity.Menu;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

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

    public void save (MenuDTO menuDTO){
        Menu menu;
        if(menuDTO.getId() == null){                                                        //Permitimos crear una entidad o actualizarla con el mismo método
            menu = new Menu();
        } else{
            menu = menuRepository.findById(menuDTO.getId()).orElse(null);
        }

        if (menuDTO.getDescripcion() != null){                                              //Puede ser nulo. Para establecerlo como nulo en la bdd dejamos en blanco el campo
            if (menuDTO.getDescripcion().equals("")){
                menu.setDescripcion(null);
            } else {
                menu.setDescripcion(menuDTO.getDescripcion());
            }
        } else {
            menu.setDescripcion(null);
        }

        if (menuDTO.getAlergenos() != null){                                                //Puede ser nulo. Para establecerlo como nulo en la bdd dejamos en blanco el campo
            if (menuDTO.getAlergenos().equals("")){
                menu.setAlergenos(null);
            } else {
                menu.setAlergenos(menuDTO.getAlergenos());
            }
        } else {
            menu.setAlergenos(null);
        }
        menu.setNombre(menuDTO.getNombre());

        menuRepository.save(menu);

    }


}

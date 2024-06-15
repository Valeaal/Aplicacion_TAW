//Autor: Álvaro Valencia Vilallón
package es.uma.proyectotaw.service;

import es.uma.proyectotaw.dao.TipoEjercicioRepository;
import es.uma.proyectotaw.dto.TipoEjercicioDTO;
import es.uma.proyectotaw.entity.TipoEjercicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class TipoEjercicioService extends DTOService<TipoEjercicioDTO, TipoEjercicio>{

    @Autowired
    TipoEjercicioRepository tipoEjercicioRepository;

    public List<TipoEjercicioDTO> findAll(){
        return this.entidadesADTO(tipoEjercicioRepository.findAll());
    }

    public Integer buscarPorString (String tipo){
        return tipoEjercicioRepository.buscarPorString(tipo);
    }

}

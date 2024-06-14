//Autor: Álvaro Valencia Vilallón
package es.uma.proyectotaw.service;

import es.uma.proyectotaw.dao.TipoUsuarioRepository;
import es.uma.proyectotaw.dto.EjercicioDTO;
import es.uma.proyectotaw.dto.TipoUsuarioDTO;
import es.uma.proyectotaw.entity.Ejercicio;
import es.uma.proyectotaw.entity.TipoUsuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TipoUsuarioService extends DTOService<TipoUsuarioDTO, TipoUsuario>{

    @Autowired
    private TipoUsuarioRepository tipoUsuarioRepository;

    public TipoUsuarioDTO buscarRolPorId(Integer id){
        TipoUsuario rol = tipoUsuarioRepository.buscarPorID(id);
        return rol.toDTO();
    }

}

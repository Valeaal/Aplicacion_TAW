//Autor: Álvaro Valencia Vilallón
package es.uma.proyectotaw.service;

import es.uma.proyectotaw.dao.TipoUsuarioRepository;
import es.uma.proyectotaw.dao.UsuarioRepository;
import es.uma.proyectotaw.dto.EjercicioDTO;
import es.uma.proyectotaw.dto.TipoUsuarioDTO;
import es.uma.proyectotaw.entity.Ejercicio;
import es.uma.proyectotaw.entity.TipoUsuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TipoUsuarioService extends DTOService<TipoUsuarioDTO, TipoUsuario>{

    @Autowired
    private TipoUsuarioRepository tipoUsuarioRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    public TipoUsuarioDTO buscarRolPorId(Integer id){
        TipoUsuario rol = tipoUsuarioRepository.buscarPorID(id);
        return rol.toDTO();
    }

    public List<TipoUsuarioDTO> sacarRoles(){
        List<TipoUsuario> roles = usuarioRepository.sacarRoles();
        TipoUsuarioService tipoService = new TipoUsuarioService(); // Instancia de DTOService que nos proporciona la posibilidad convertir la lista a dto
        List<TipoUsuarioDTO> rolesDTO = tipoService.entidadesADTO(roles);
        return rolesDTO;
    }

    public List<TipoUsuarioDTO> sacarRolesComleto(){
        List<TipoUsuario> roles = tipoUsuarioRepository.findAll();
        TipoUsuarioService tipoService = new TipoUsuarioService();
        List<TipoUsuarioDTO> rolesDTO = tipoService.entidadesADTO(roles);
        return rolesDTO;
    }

    public int buscarRolPorString(String rol){
        return tipoUsuarioRepository.buscarPorString(rol);
    }



}

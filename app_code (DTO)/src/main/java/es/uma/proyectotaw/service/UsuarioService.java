//Autor: Álvaro Valencia Vilallón
package es.uma.proyectotaw.service;

import es.uma.proyectotaw.dao.TipoUsuarioRepository;
import es.uma.proyectotaw.dao.UsuarioRepository;
import es.uma.proyectotaw.dto.TipoUsuarioDTO;
import es.uma.proyectotaw.dto.UsuarioDTO;
import es.uma.proyectotaw.entity.TipoUsuario;
import es.uma.proyectotaw.entity.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UsuarioService extends DTOService<UsuarioDTO, Usuario>{

    @Autowired
    private UsuarioRepository usuarioRepository;
    @Autowired
    private TipoUsuarioRepository tipoUsuarioRepository;

    public List<UsuarioDTO> sacarUsuariosPorTipo(TipoUsuarioDTO tipoUsuario) {
        TipoUsuario tipoEntity = tipoUsuarioRepository.findById(tipoUsuario.getId()).orElse(null);
        if (tipoEntity == null) {
            return null;
        } else{
            List<Usuario> usuarioEntity = usuarioRepository.buscarPorTipo(tipoEntity);
            UsuarioService usuarioService = new UsuarioService(); // Instancia de DTOService que nos proporciona la posibilidad convertir el conjunto a dto
            List<UsuarioDTO> usuariosDTO = usuarioService.entidadesADTO(usuarioEntity);
            return usuariosDTO;
        }

    };

    public UsuarioDTO buscarUsuarioPorId(Integer id){
        if (id == null) {
            return null;
        } else{
            Usuario usr = usuarioRepository.findById(id).orElse(null);
            return usr.toDTO();
        }
    }

}

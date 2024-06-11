package es.uma.proyectotaw.service;

import es.uma.proyectotaw.dao.*;
import es.uma.proyectotaw.dto.TipoUsuarioDTO;
import es.uma.proyectotaw.dto.UsuarioDTO;
import es.uma.proyectotaw.entity.TipoUsuario;
import es.uma.proyectotaw.entity.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AdminService {

    @Autowired
    private UsuarioRepository usuarioRepository;
    @Autowired
    private TipoUsuarioRepository tipoUsuarioRepository;
    @Autowired
    private ClienteRepository clienteRepository;
    @Autowired
    private EjercicioRepository ejercicioRepository;
    @Autowired
    private TipoEjercicioRepository tipoEjercicioRepository;
    @Autowired
    private GrupoMuscularRepository grupoMuscularRepository;
    @Autowired
    private MenuRepository menuRepository;
    @Autowired
    private EjercicioEntrenamientoRepository ejercicioEntrenamientoRepository;
    @Autowired
    private EntrenamientoRepository entrenamientoRepository;

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    //GESTIÓN DE LOS USUARIOS
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    public List<Integer> sacarEdades(){
        List<Integer> edades = usuarioRepository.sacarEdades();
        return edades;
    }

    public List<Integer> sacarIngresos(){
        List<Integer> ingresos = usuarioRepository.sacarIngresos();
        return ingresos;
    }

    public List<TipoUsuarioDTO> sacarRoles(){
        List<TipoUsuario> roles = usuarioRepository.sacarRoles();
        TipoUsuarioService tipoService = new TipoUsuarioService(); // Instancia de DTOService que nos proporciona la posibilidad convertir la lista a dto
        List<TipoUsuarioDTO> rolesDTO = tipoService.entidadesADTO(roles);
        return rolesDTO;
    }

    public List<UsuarioDTO> sacarUsuarios(){
        List <Usuario> usuarios = usuarioRepository.sacarUsuarios();
        UsuarioService usuarioService = new UsuarioService(); // Instancia de DTOService que nos proporciona la posibilidad convertir el conjunto a dto
        List<UsuarioDTO> usuariosDTO = usuarioService.entidadesADTO(usuarios);
        return usuariosDTO;
    }

}

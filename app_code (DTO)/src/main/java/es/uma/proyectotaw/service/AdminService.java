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

    public int buscarRolPorString(String rol){
        return tipoUsuarioRepository.buscarPorString(rol);
    }

    public List<UsuarioDTO> filtrarUsuarios(String nombre, String apellidos, Integer edad, Integer ingreso, Integer rol){
        List <Usuario> usuarios = usuarioRepository.filtrarUsuarios(nombre, apellidos, edad, ingreso, rol);
        UsuarioService usuarioService = new UsuarioService(); // Instancia de DTOService que nos proporciona la posibilidad convertir el conjunto a dto
        List<UsuarioDTO> usuariosDTO = usuarioService.entidadesADTO(usuarios);
        return usuariosDTO;
    }

    public List<TipoUsuarioDTO> sacarRolesComleto(){
        List<TipoUsuario> roles = tipoUsuarioRepository.findAll();
        TipoUsuarioService tipoService = new TipoUsuarioService();
        List<TipoUsuarioDTO> rolesDTO = tipoService.entidadesADTO(roles);
        return rolesDTO;
    }

    public UsuarioDTO buscarUsuarioPorId(Integer id){
        if (id == null) {
            return null;
        } else{
            Usuario usr = usuarioRepository.findById(id).orElse(null);
            return usr.toDTO();
        }
    }

    public void eliminarUsuario(Integer id){
        usuarioRepository.deleteById(id);
    }



}

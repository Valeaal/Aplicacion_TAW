//Autor: Álvaro Valencia Vilallón
package es.uma.proyectotaw.service;

import es.uma.proyectotaw.dao.*;
import es.uma.proyectotaw.dto.TipoUsuarioDTO;
import es.uma.proyectotaw.dto.UsuarioDTO;
import es.uma.proyectotaw.entity.*;
import es.uma.proyectotaw.ui.ComidasDieta;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UsuarioService extends DTOService<UsuarioDTO, Usuario>{

    @Autowired
    private UsuarioRepository usuarioRepository;
    @Autowired
    private TipoUsuarioRepository tipoUsuarioRepository;
    @Autowired
    private ClienteRepository clienteRepository;
    @Autowired
    private RutinaRepository rutinaRepository;
    @Autowired
    private DietaRepository dietaRepository;
    @Autowired
    private RutinaService rutinaService;
    @Autowired
    private ComidaDietaRepository comidaDietaRepository;
    @Autowired
    private Cliente_RutinaRepository cliente_RutinaRepository;
    @Autowired
    private DesempenoRepository desempenoRepository;
    @Autowired
    private EjercicioEntrenamientoRepository ejercicioEntrenamientoRepository;

    public List<UsuarioDTO> sacarUsuariosPorTipo(TipoUsuarioDTO tipoUsuario) {
        TipoUsuario tipoEntity = tipoUsuarioRepository.findById(tipoUsuario.getId()).orElse(null);
        if (tipoEntity == null) {
            return null;
        } else{
            List<Usuario> usuarioEntity = usuarioRepository.buscarPorTipo(tipoEntity);
            List<UsuarioDTO> usuariosDTO = this.entidadesADTO(usuarioEntity);
            return usuariosDTO;
        }

    };

    public List<UsuarioDTO> buscarPorTipoUsuario(Integer tipoUsuario) {
        TipoUsuario tipoEntity = tipoUsuarioRepository.findById(tipoUsuario).orElse(null);
        return this.entidadesADTO(this.usuarioRepository.buscarPorTipo(tipoEntity));
    }

    public UsuarioDTO buscarUsuarioPorId(Integer id){
        if (id == null) {
            return null;
        } else{
            Usuario usr = usuarioRepository.findById(id).orElse(null);
            return usr.toDTO();
        }
    }

    public void guardarUsuario(UsuarioDTO usuario){
        Usuario usr = new Usuario();
        if (usuario.getId() != null){
            usr = usuarioRepository.buscarPorID(usuario.getId());
        }
        usr.setNombre(usuario.getNombre());
        usr.setApellidos(usuario.getApellidos());
        usr.setEmail(usuario.getEmail());
        usr.setFechaNacimiento(usuario.getFechaNacimiento());
        usr.setPerteneceDesde(usuario.getPerteneceDesde());
        TipoUsuario tipoEntity = tipoUsuarioRepository.buscarPorID(usuario.getTipoUsuario().getId());
        usr.setTipoUsuario(tipoEntity);
        usr.setPassword(usuario.getPassword());
        usuarioRepository.save(usr);
    }

    public void eliminarUsuario(Integer id){
        Usuario usrABorrar = usuarioRepository.findById(id).orElse(null);
        List<Cliente> clientes;
        List<Rutina> rutinas;
        List<Dieta> dietas;
        List<DietaComida> dietasComidas;
        if (usrABorrar.getTipoUsuario().getId() == 2 || usrABorrar.getTipoUsuario().getId() == 3 ){             //Es entrenador, hay que borrar la relación con sus clientes
            clientes = clienteRepository.getClienteByIdEntrenador(id);
            rutinas = rutinaRepository.getRutinaByEnternadorId(id);
            for(Cliente c : clientes){
                c.setEntrenador(null);
                clienteRepository.save(c);
            }
            for(Rutina r : rutinas){
                rutinaService.borrarRutina(r.toDTO());                                                          //Hecho por alonso
            }
        }
        if (usrABorrar.getTipoUsuario().getId() == 4 ){                                                         //Es dietista, hay que borrar la relación con sus clientes
            clientes = clienteRepository.getClienteByIdEntrenador(id);
            dietas = dietaRepository.getDietaByDietistaId(id);
            for(Cliente c : clientes){
                c.setDietista(null);
                c.setDieta(null);
                clienteRepository.save(c);
            }
            for(Dieta d : dietas){                                                                              //Igualmente, realizamos el borrado en cascada
                dietasComidas = comidaDietaRepository.getDietaComidasPorDietaId(d.getId());
                for(DietaComida dc : dietasComidas){
                    comidaDietaRepository.delete(dc);
                }
                dietaRepository.delete(d);
            }
        }
        if (usrABorrar.getTipoUsuario().getId() == 5 ){                                                         //Si es cliente, hay que borarlo también de cliente repository
            Cliente c = clienteRepository.getClienteByUserId(id);
            List<ClienteRutina> clientesRutina = cliente_RutinaRepository.historialRutinasCliente(c.getId());
            List<Desempeno> desempenos = desempenoRepository.getDesempenoPorCliente(c.getId());
            List<EjercicioEntrenamiento> ejercicioEntrenamientos;
            for(ClienteRutina cr : clientesRutina){                                                             //Borrado en cascada de las tablas pertinentes
                cliente_RutinaRepository.delete(cr);
            }
            for(Desempeno d : desempenos){                                                                      //Borrado en cascada de las tablas pertinentes
                ejercicioEntrenamientos = ejercicioEntrenamientoRepository.getEjercicioEntrenamientoPorDesempenoId(d.getId());
            for(EjercicioEntrenamiento ee : ejercicioEntrenamientos){                                           //Borrado en cascada de las tablas pertinentes
                    ejercicioEntrenamientoRepository.delete(ee);
                }
                desempenoRepository.delete(d);
            }
            clienteRepository.delete(c);
        }
        usuarioRepository.deleteById(id);
    }

    public List<Integer> sacarEdades(){
        List<Integer> edades = usuarioRepository.sacarEdades();
        return edades;
    }

    public List<Integer> sacarIngresos(){
        List<Integer> ingresos = usuarioRepository.sacarIngresos();
        return ingresos;
    }

    public List<UsuarioDTO> sacarUsuarios(){
        List <Usuario> usuarios = usuarioRepository.findAll();
        UsuarioService usuarioService = new UsuarioService(); // Instancia de DTOService que nos proporciona la posibilidad convertir el conjunto a dto
        List<UsuarioDTO> usuariosDTO = usuarioService.entidadesADTO(usuarios);
        return usuariosDTO;
    }

    public List<UsuarioDTO> filtrarUsuarios(String nombre, String apellidos, Integer edad, Integer ingreso, Integer rol){
        List <Usuario> usuarios = usuarioRepository.filtrarUsuarios(nombre, apellidos, edad, ingreso, rol);
        UsuarioService usuarioService = new UsuarioService(); // Instancia de DTOService que nos proporciona la posibilidad convertir el conjunto a dto
        List<UsuarioDTO> usuariosDTO = usuarioService.entidadesADTO(usuarios);
        return usuariosDTO;
    }

}

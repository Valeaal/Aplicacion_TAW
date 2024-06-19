/*
@author: Miguel Galdeano Rodríguez
 */
package es.uma.proyectotaw.controller;

import ch.qos.logback.core.net.server.Client;
import es.uma.proyectotaw.dao.*;
import es.uma.proyectotaw.dto.*;
import es.uma.proyectotaw.entity.*;
import es.uma.proyectotaw.service.*;
import es.uma.proyectotaw.ui.FiltroBodyBuilder;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cglib.core.Local;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.chrono.ChronoLocalDate;
import java.util.*;

@Controller
@RequestMapping("/bodybuilding")
public class BodybuildingController {
    @Autowired
    EntrenamientoService entrenamientoService;
    @Autowired
    RutinaService rutinaService;
    @Autowired
    TipoEjercicioRepository tipoEjercicioRepository;
    @Autowired
    GrupoMuscularRepository grupoMuscularRepository;
    @Autowired
    EntrenamientoRutinaService entrenamientoRutinaService;
    @Autowired
    Tipo_RutinaRepository tipoRutinaRepository;
    @Autowired
    UsuarioRepository usuarioRepository;
    @Autowired
    ClienteService clienteService;
    @Autowired
    TipoUsuarioRepository tipoUsuarioRepository;
    @Autowired
    ClienteRutinaService clienteRutinaService;
    @Autowired
    DesempenoRepository desempenoRepository;
    @Autowired
    EjercicioEntrenamientoRepository ejercicioEntrenamientoRepository;
    private int tipo_rutina = 1;

    @GetMapping("/")
    public String doListar(Model model, HttpSession session){
        List<RutinaDTO> rutinas = this.rutinaService.findByTipo(tipo_rutina);
        FiltroBodyBuilder filtro = new FiltroBodyBuilder();
        TipoUsuario usuarioBody = this.tipoUsuarioRepository.buscarPorID(2);
        List<Usuario> entrenadores = this.usuarioRepository.buscarPorTipo(usuarioBody);
        model.addAttribute("filtro", filtro);
        model.addAttribute("entrenadores",entrenadores);
        model.addAttribute("rutinas", rutinas);
        return "bodybuilding/listadoRutinas";
    }

    public void inicializacion(Model model){
        List<EntrenamientoDTO> entrenamientos = this.entrenamientoService.findAll();
        model.addAttribute("entrenamientos", entrenamientos);
    }

    @GetMapping("/editarRutina")
    public String doEditarEjercicio(@RequestParam("id")Integer id, Model model, HttpSession session){
        RutinaDTO rutina = this.rutinaService.findById(id);
        List<GrupoMuscular> gruposMusculares = this.grupoMuscularRepository.findAll();
        List<TipoEjercicio> tiposEjercicios = this.tipoEjercicioRepository.findAll();
        List<EntrenamientoDTO> entrenamientos = this.entrenamientoService.findAll();
        List<EntrenamientoRutinaDTO> entrenamientoRutinas = this.entrenamientoRutinaService.findAll();
        model.addAttribute("entrenamientoRutinas", entrenamientoRutinas);
        model.addAttribute("entrenamientos", entrenamientos);
        model.addAttribute("gruposMusculares", gruposMusculares);
        model.addAttribute("tiposEjercicios", tiposEjercicios);
        model.addAttribute("rutina", rutina);
        this.inicializacion(model);
        return "bodybuilding/editarRutina";
    }

    @GetMapping("/crearRutina")
    public String doCrearRutina(Model model, HttpSession session){
        Rutina rutina = new Rutina();
        rutina.setTipoRutina(this.tipoRutinaRepository.findById(tipo_rutina).orElse(null));
        rutina.setEntrenador((Usuario) session.getAttribute("usuario"));
        rutina.setFechaCreacion(LocalDate.parse(new java.sql.Date(System.currentTimeMillis()).toString()));
        model.addAttribute("rutina", rutina);
        return "bodybuilding/editarRutina";
    }


    @GetMapping("/eliminarRutina")
    public String doEliminarRutina(@RequestParam("id")Integer id,Model model, HttpSession session){
        List<Integer> entrenamientos = this.entrenamientoRutinaService.findByRutinaId(id);
        this.entrenamientoRutinaService.deleteAllById(entrenamientos);
        RutinaDTO rutina = this.rutinaService.findById(id);
        this.rutinaService.delete(rutina.getId());
        return "redirect:/bodybuilding/";
    }


    @PostMapping("/filtrarRutinas")
    public String doFiltrar(@ModelAttribute("filtro")FiltroBodyBuilder filtro, Model model, HttpSession session){
        List<RutinaDTO> rutinas;
        LocalDate fecha = filtro.getFecha();
        if(fecha == null){
             fecha = LocalDate.parse(new java.sql.Date(System.currentTimeMillis()).toString()).minusYears(200);
        }

        if(filtro.getNumEntrenamientos().equals("-") && filtro.getEntrenadorCreador()==null){
            //Filtro solo por nombre
            rutinas = this.rutinaService.findByNombre(filtro.getNombre(),tipo_rutina,fecha);
        }else if(filtro.getEntrenadorCreador()==null){
            //Filtro por nombre y numero de entrenos
            rutinas = this.rutinaService.findByNombreAndEntrenos(filtro.getNombre(),Integer.parseInt(filtro.getNumEntrenamientos()), tipo_rutina,fecha);
        }else if(filtro.getNumEntrenamientos().equals("-")){
            //Filtro por nombre y creador
            rutinas = this.rutinaService.findByNombreAndCreador(filtro.getNombre(),filtro.getEntrenadorCreador().getId(), tipo_rutina,fecha);
        }else{
            //Filtro por nombre, numero de entrenos y creador
            rutinas = this.rutinaService.findByNombreEntrenosAndCreador(filtro.getNombre(),Integer.parseInt(filtro.getNumEntrenamientos()),filtro.getEntrenadorCreador().getId(), tipo_rutina,fecha);
        }
        TipoUsuario usuarioBody = this.tipoUsuarioRepository.buscarPorID(2);
        List<Usuario> entrenadores = this.usuarioRepository.buscarPorTipo(usuarioBody);
        model.addAttribute("entrenadores", entrenadores);
        model.addAttribute("filtro", filtro);
        model.addAttribute("rutinas", rutinas);
        return "bodybuilding/listadoRutinas";
    }

    @GetMapping("/eliminarEntrenamientoRutina")
    public String doEliminarEntrenamientoRutina(@RequestParam("id")Integer id,Model model, HttpSession session){
        EntrenamientoRutinaDTO ER = this.entrenamientoRutinaService.findById(id);
        RutinaDTO rutina = this.rutinaService.findById(ER.getRutina());
        EntrenamientoDTO entrenamiento = this.entrenamientoService.findByRutinaId(ER.getRutina()).get(0);
        rutina.getEntrenamientos().remove(ER);
        entrenamiento.getRutinas().remove(ER.getId());
        this.entrenamientoRutinaService.delete(ER.getId());
        this.rutinaService.guardar(rutina);
        this.entrenamientoService.guardar(entrenamiento);
        return "redirect:/bodybuilding/editarRutina?id=" + rutina.getId();
    }

    @PostMapping("/guardarCambiosEdicion")
    public String doGuardarCambios (@ModelAttribute("rutina")RutinaDTO rutina, Model model, HttpSession session){
        if(rutina.getId()==null){
            this.rutinaService.guardar(rutina);
            model.addAttribute("rutina",rutina);
            return "bodybuilding/editarRutina";
        }else{
            this.rutinaService.guardar(rutina);
            return "redirect:/bodybuilding/";
        }
    }

    @GetMapping("/anyadirEntrenamientoRutina")
    public String doAnyadirEntrenamientoRutina(@RequestParam("id")Integer id,@RequestParam("dia")Integer dia,Model model, HttpSession session){
        EntrenamientoRutinaDTO ER = new EntrenamientoRutinaDTO();
        RutinaDTO rutina = this.rutinaService.findById(id);
        ER.setRutina(rutina.getId());
        ER.setDiaSemana(dia);
        model.addAttribute("ER",ER);
        List<EntrenamientoDTO> entrenamientos = this.entrenamientoService.findAll();
        model.addAttribute("entrenamientos", entrenamientos);
        return "bodybuilding/anyadirEntrenoRutina";
    }

    @GetMapping("/asignarRutinas")
    public String asignarRutinaCliente(Model model, HttpSession session){
        List<RutinaDTO> rutinas = this.rutinaService.findByTipo(tipo_rutina);
        List<ClienteDTO> clientes = this.clienteService.findByEntrenador((UsuarioDTO) session.getAttribute("usuario"));
        model.addAttribute("rutinas",rutinas);
        model.addAttribute("clientes",clientes);
        return "bodybuilding/asignacionRutinas";
    }

    @PostMapping("/asignarRutinaCliente")
    public String asignarRutinaCliente(@RequestParam("cliente")Integer idCliente,@RequestParam("rutina")Integer idRutina,Model model, HttpSession session){
        if(idRutina!=null && idCliente!=null) {
            ClienteDTO cliente = this.clienteService.getClienteById(idCliente);
            ClienteRutinaDTO activa = this.clienteRutinaService.findByActiveRoutines(cliente.getId());
            RutinaDTO rutina = this.rutinaService.findById(idRutina);
            ClienteRutinaDTO cr = new ClienteRutinaDTO();
            //cr.setCliente(cliente);
            //cr.setRutina(rutina);
            cr.setVigente(true);
            if (activa != null ) {
                activa.setVigente(false);
                this.clienteRutinaService.guardar(activa);
            }
            this.clienteRutinaService.guardar(cr);
        }
        return "redirect:/bodybuilding/asignarRutinas";
    }

    @PostMapping("/guardarEntrenamientoRutina")
    public String doAnyadirEntrenamientoRutina(@ModelAttribute("ER")EntrenamientoRutinaDTO ER,Model model, HttpSession session){
        this.entrenamientoRutinaService.save(ER);
        return "redirect:/bodybuilding/editarRutina?id=" + ER.getRutina();
    }

    @GetMapping("/seguimientoClientes")
    public String seguimientoClientes(Model model, HttpSession session){
        UsuarioDTO usuario = (UsuarioDTO) session.getAttribute("usuario");
        List<ClienteDTO> clientes = this.clienteService.findByEntrenador(usuario);
        Map<ClienteDTO,RutinaDTO> rutinas = new HashMap<>();
        for(ClienteDTO cliente : clientes){
            ClienteRutinaDTO crs = this.clienteRutinaService.findByActiveRoutines(cliente.getId());
            if(crs != null){
                RutinaDTO rutina = new RutinaDTO();
                //rutinas.put(cliente,crs.getRutina());
            }else{
                rutinas.put(cliente,null);
            }
        }
        model.addAttribute("clientes",clientes);
        model.addAttribute("rutinas",rutinas);
        return"bodybuilding/seguimientoClientes";
    }

    @GetMapping("/verComentarios")
    public String verComentarios(@RequestParam("id")Integer idCliente,Model model, HttpSession session){
        ClienteDTO cliente = this.clienteService.getClienteById(idCliente);
        List<Desempeno> desempenos = this.desempenoRepository.desempenoDelCliente(idCliente);
        List<EjercicioEntrenamiento> ENR = new ArrayList<>();
        for(Desempeno desempeno : desempenos){
            ENR.add(this.ejercicioEntrenamientoRepository.findByDesempeno(desempeno.getId()));
        }
        model.addAttribute("cliente",cliente);
        model.addAttribute("ejercicios",ENR);
        return"bodybuilding/verComentarios";
    }

    public RutinaDTO inicializarRutina(){
        RutinaDTO rutina = new RutinaDTO();
        this.rutinaService.guardar(rutina);
        return rutina;
    }
}

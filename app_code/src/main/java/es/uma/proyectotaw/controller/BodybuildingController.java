/*
@author: Miguel Galdeano Rodríguez
 */
package es.uma.proyectotaw.controller;

import ch.qos.logback.core.net.server.Client;
import es.uma.proyectotaw.dao.*;
import es.uma.proyectotaw.entity.*;
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
    EntrenamientoRepository entrenamientoRepository;
    @Autowired
    RutinaRepository rutinaRepository;
    @Autowired
    TipoEjercicioRepository tipoEjercicioRepository;
    @Autowired
    GrupoMuscularRepository grupoMuscularRepository;
    @Autowired
    EntrenamientoRutinaRepository entrenamientoRutinaRepository;
    @Autowired
    Tipo_RutinaRepository tipoRutinaRepository;
    @Autowired
    UsuarioRepository usuarioRepository;
    @Autowired
    ClienteRepository clienteRepository;
    @Autowired
    TipoUsuarioRepository tipoUsuarioRepository;
    @Autowired
    Cliente_RutinaRepository clienteRutinaRepository;
    @Autowired
    DesempenoRepository desempenoRepository;
    @Autowired
    EjercicioEntrenamientoRepository ejercicioEntrenamientoRepository;
    private int tipo_rutina = 1;

    @GetMapping("/")
    public String doListar(Model model, HttpSession session){
        List<Rutina> rutinas = this.rutinaRepository.findByTipo(tipo_rutina);
        FiltroBodyBuilder filtro = new FiltroBodyBuilder();
        TipoUsuario usuarioBody = this.tipoUsuarioRepository.buscarPorID(2);
        List<Usuario> entrenadores = this.usuarioRepository.buscarPorTipo(usuarioBody);
        model.addAttribute("filtro", filtro);
        model.addAttribute("entrenadores",entrenadores);
        model.addAttribute("rutinas", rutinas);
        return "bodybuilding/listadoRutinas";
    }

    public void inicializacion(Model model){
        List<Entrenamiento> entrenamientos = this.entrenamientoRepository.findAll();
        model.addAttribute("entrenamientos", entrenamientos);
    }

    @GetMapping("/editarRutina")
    public String doEditarEjercicio(@RequestParam("id")Integer id, Model model, HttpSession session){
        Rutina rutina = this.rutinaRepository.findById(id).orElse(null);
        List<GrupoMuscular> gruposMusculares = this.grupoMuscularRepository.findAll();
        List<TipoEjercicio> tiposEjercicios = this.tipoEjercicioRepository.findAll();
        List<Entrenamiento> entrenamientos = this.entrenamientoRepository.findAll();
        List<EntrenamientoRutina> entrenamientoRutinas = this.entrenamientoRutinaRepository.findAll();
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
        List<Integer> entrenamientos = this.entrenamientoRutinaRepository.findByRutinaId(id);
        this.entrenamientoRutinaRepository.deleteAllById(entrenamientos);
        Rutina rutina = this.rutinaRepository.findById(id).orElse(null);
        this.rutinaRepository.delete(rutina);
        return "redirect:/bodybuilding/";
    }


    @PostMapping("/filtrarRutinas")
    public String doFiltrar(@ModelAttribute("filtro")FiltroBodyBuilder filtro, Model model, HttpSession session){
        List<Rutina> rutinas;
        LocalDate fecha = filtro.getFecha();
        if(fecha == null){
             fecha = LocalDate.parse(new java.sql.Date(System.currentTimeMillis()).toString()).minusYears(200);
        }

        if(filtro.getNumEntrenamientos().equals("-") && filtro.getEntrenadorCreador()==null){
            //Filtro solo por nombre
            rutinas = this.rutinaRepository.findByNombre(filtro.getNombre(),tipo_rutina,fecha);
        }else if(filtro.getEntrenadorCreador()==null){
            //Filtro por nombre y numero de entrenos
            rutinas = this.rutinaRepository.findByNombreAndEntrenos(filtro.getNombre(),Integer.parseInt(filtro.getNumEntrenamientos()), tipo_rutina,fecha);
        }else if(filtro.getNumEntrenamientos().equals("-")){
            //Filtro por nombre y creador
            rutinas = this.rutinaRepository.findByNombreAndCreador(filtro.getNombre(),filtro.getEntrenadorCreador(), tipo_rutina,fecha);
        }else{
            //Filtro por nombre, numero de entrenos y creador
            rutinas = this.rutinaRepository.findByNombreEntrenosAndCreador(filtro.getNombre(),Integer.parseInt(filtro.getNumEntrenamientos()),filtro.getEntrenadorCreador(), tipo_rutina,fecha);
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
        EntrenamientoRutina ER = this.entrenamientoRutinaRepository.findById(id).orElse(null);
        Rutina rutina = ER.getRutina();
        Entrenamiento entrenamiento = ER.getEntrenamiento();
        rutina.getEntrenamientos().remove(ER);
        entrenamiento.getRutinas().remove(ER);
        Integer idRutina = ER.getRutina().getId();
        this.entrenamientoRutinaRepository.delete(ER);
        this.rutinaRepository.save(rutina);
        this.entrenamientoRepository.save(entrenamiento);
        return "redirect:/bodybuilding/editarRutina?id=" + idRutina;
    }

    @PostMapping("/guardarCambiosEdicion")
    public String doGuardarCambios (@ModelAttribute("rutina")Rutina rutina, Model model, HttpSession session){
        if(rutina.getId()==null){
            this.rutinaRepository.save(rutina);
            model.addAttribute("rutina",rutina);
            return "bodybuilding/editarRutina";
        }else{
            this.rutinaRepository.save(rutina);
            return "redirect:/bodybuilding/";
        }
    }

    @GetMapping("/anyadirEntrenamientoRutina")
    public String doAnyadirEntrenamientoRutina(@RequestParam("id")Integer id,@RequestParam("dia")Integer dia,Model model, HttpSession session){
        EntrenamientoRutina ER = new EntrenamientoRutina();
        Rutina rutina = this.rutinaRepository.findById(id).orElse(null);
        ER.setRutina(rutina);
        ER.setDiaSemana(dia);
        model.addAttribute("ER",ER);
        List<Entrenamiento> entrenamientos = this.entrenamientoRepository.findAll();
        model.addAttribute("entrenamientos", entrenamientos);
        return "bodybuilding/anyadirEntrenoRutina";
    }

    @GetMapping("/asignarRutinas")
    public String asignarRutinaCliente(Model model, HttpSession session){
        List<Rutina> rutinas = this.rutinaRepository.findByTipo(tipo_rutina);
        List<Cliente> clientes = this.clienteRepository.findByEntrenador((Usuario) session.getAttribute("usuario"));
        model.addAttribute("rutinas",rutinas);
        model.addAttribute("clientes",clientes);
        return "bodybuilding/asignacionRutinas";
    }

    @PostMapping("/asignarRutinaCliente")
    public String asignarRutinaCliente(@RequestParam("cliente")Integer idCliente,@RequestParam("rutina")Integer idRutina,Model model, HttpSession session){
        if(idRutina!=null && idCliente!=null) {
            Cliente cliente = this.clienteRepository.getClienteById(idCliente);
            List<ClienteRutina> activas = this.clienteRutinaRepository.findActiveRoutines(cliente.getId());
            Rutina rutina = this.rutinaRepository.findById(idRutina).orElse(null);
            ClienteRutina cr = new ClienteRutina();
            cr.setCliente(cliente);
            cr.setRutina(rutina);
            cr.setVigente(true);
            if (!activas.isEmpty()) {
                ClienteRutina activa = activas.get(0);
                activa.setVigente(false);
                this.clienteRutinaRepository.save(activa);
            }
            this.clienteRutinaRepository.save(cr);
        }
        return "redirect:/bodybuilding/asignarRutinas";
    }

    @PostMapping("/guardarEntrenamientoRutina")
    public String doAnyadirEntrenamientoRutina(@ModelAttribute("ER")EntrenamientoRutina ER,Model model, HttpSession session){
        this.entrenamientoRutinaRepository.save(ER);
        return "redirect:/bodybuilding/editarRutina?id=" + ER.getRutina().getId();
    }

    @GetMapping("/seguimientoClientes")
    public String seguimientoClientes(Model model, HttpSession session){
        Usuario usuario = (Usuario) session.getAttribute("usuario");
        List<Cliente> clientes = this.clienteRepository.findByEntrenador(usuario);
        Map<Cliente,Rutina> rutinas = new HashMap<>();
        for(Cliente cliente : clientes){
            List<ClienteRutina> CRS = this.clienteRutinaRepository.findActiveRoutines(cliente.getId());
            if(!CRS.isEmpty()){
                ClienteRutina CR = this.clienteRutinaRepository.findActiveRoutines(cliente.getId()).get(0);
                rutinas.put(cliente,CR.getRutina());
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
        Cliente cliente = this.clienteRepository.findById(idCliente).orElse(null);
        List<Desempeno> desempenos = this.desempenoRepository.desempenoDelCliente(idCliente);
        List<EjercicioEntrenamiento> ENR = new ArrayList<>();
        for(Desempeno desempeno : desempenos){
            ENR.add(this.ejercicioEntrenamientoRepository.findByDesempeno(desempeno.getId()));
        }
        model.addAttribute("cliente",cliente);
        model.addAttribute("ejercicios",ENR);
        return"bodybuilding/verComentarios";
    }

    public Rutina inicializarRutina(){
        Rutina rutina = new Rutina();
        this.rutinaRepository.save(rutina);
        return rutina;
    }
}

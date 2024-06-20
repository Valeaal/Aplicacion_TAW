package es.uma.proyectotaw.controller;

import es.uma.proyectotaw.dao.*;
import es.uma.proyectotaw.dto.*;
import es.uma.proyectotaw.entity.*;
import es.uma.proyectotaw.service.*;
import es.uma.proyectotaw.ui.Filtro_Rutina_nEntrenamientos;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@Controller
public class CrossfitController {

    // primero aqui tengo los repository, que borrare luego
    @Autowired
    private UsuarioRepository usuarioRepository;
    @Autowired
    private TipoUsuarioRepository tipoUsuarioRepository;
    @Autowired
    protected RutinaRepository rutinaRepository;
    @Autowired
    protected EntrenamientoRepository entrenamientoRepository;
    @Autowired
    protected Entrenamiento_RutinaRepository entrenamiento_RutinaRepository;
    @Autowired
    protected Cliente_RutinaRepository cliente_RutinaRepository;
    @Autowired
    protected ClienteRepository clienteRepository;
    @Autowired
    protected Tipo_RutinaRepository tipo_RutinaRepository;
    @Autowired
    protected DesempenoRepository desempenoRepository;
    @Autowired
    protected EjercicioEntrenamientoRepository ejercicioEntrenamientoRepository;

    // aqui meto los service

    @Autowired
    private UsuarioService usuarioService;
    @Autowired
    private RutinaService rutinaService;
    @Autowired
    private ClienteService clienteService;
    @Autowired
    private ClienteRutinaService clienteRutinaService;
    @Autowired
    private DesempenoService desempenoService;
    @Autowired
    private EjercicioEntrenamientoService ejercicioEntrenamientoService;
    @Autowired
    private TipoRutinaService tipoRutinaService;
    @Autowired
    private EntrenamientoRutinaService entrenamientoRutinaService;
    @Autowired
    private EntrenamientoService entrenamientoService;


    @GetMapping("/crud")
    public String doCRUD(Model model) {
        List<RutinaDTO> rutinas = rutinaService.getCrossfitRutinas();
        model.addAttribute("rutinas", rutinas);
        model.addAttribute("filtroRutinas", new Filtro_Rutina_nEntrenamientos());
        return "crosstrainer/crudRutinas";
    }

    @PostMapping("/filtrarRutinas")
    public String doFiltrarRutinas(@ModelAttribute("filtroRutinas") Filtro_Rutina_nEntrenamientos filtroRutinas,
                                   Model model) {
        if ((filtroRutinas.getNombreRutina() == null || filtroRutinas.getNombreRutina().equals("")) &&
                filtroRutinas.getNumeroEntrenamientos() == -1) {
            return "redirect:/crud";
        } else if (filtroRutinas.getNumeroEntrenamientos() == -1) {
            List<RutinaDTO> rutinas = rutinaService.filtrarPorNombre(filtroRutinas.getNombreRutina());
            model.addAttribute("rutinas", rutinas);
        } else if (filtroRutinas.getNombreRutina() == null || filtroRutinas.getNombreRutina().equals("")) {
            List<RutinaDTO> rutinas = this.rutinaService.filtrarPornumEntrenamientos(filtroRutinas.getNumeroEntrenamientos());
            model.addAttribute("rutinas", rutinas);
        } else {
            List<RutinaDTO> rutinas = this.rutinaService.filtrarPorNombreynEntrenamientos(filtroRutinas.getNombreRutina(),
                    filtroRutinas.getNumeroEntrenamientos());
            model.addAttribute("rutinas", rutinas);
        }
        model.addAttribute("filtroRutinas", filtroRutinas);
        return "crosstrainer/crudRutinas";
    }

    @GetMapping("/ClientesRutinas")
    public String doAsignarRutinas(Model model, HttpSession session) {
        UsuarioDTO entrenador = (UsuarioDTO) session.getAttribute("usuario");
        List<ClienteDTO> clientes = clienteService.getClientesDelEntrenador(entrenador.getId());
        List<RutinaDTO> rutinas = rutinaService.getCrossfitRutinas();
        model.addAttribute("clientes", clientes);
        model.addAttribute("rutinas", rutinas);
        return "crosstrainer/asignarRutinas";
    }

    @PostMapping("/ClientesRutinas/asignar")
    public String doAsignacionRutinas(@RequestParam("idRutina") Integer idRutina,
                                      @RequestParam("idUser") Integer idUser,
                                      Model model) {

        if (idRutina != null && idUser != null) {
            RutinaDTO rutina = rutinaService.findById(idRutina);
            ClienteDTO cliente = clienteService.getClienteByUserId(idUser);
            ClienteRutinaDTO asignacionRutinaACliente = new ClienteRutinaDTO();
            asignacionRutinaACliente.setCliente(cliente.getId());
            asignacionRutinaACliente.setRutina(rutina.getId());

            clienteRutinaService.guardarClienteRutina(asignacionRutinaACliente);
        }

        return "redirect:/ClientesRutinas";
    }

    @GetMapping("/seguimientoRutinas")
    public String doSeguimientoRutinas(Model model, HttpSession session) {
        UsuarioDTO entrenador = (UsuarioDTO) session.getAttribute("usuario");
        List<ClienteDTO> clientes = clienteService.getClientesDelEntrenador(entrenador.getId());
        model.addAttribute("clientes", clientes);
        model.addAttribute("clienteFiltro", new Usuario());
        return "crosstrainer/seguimientoRutinas";
    }

    @GetMapping("/filtroClientes")
    public String doSeguimientoRutinas(Model model, HttpSession session,
                                       @ModelAttribute("clienteFiltro") Usuario clienteFiltro) {
        UsuarioDTO entrenador = (UsuarioDTO) session.getAttribute("usuario");
        if (clienteFiltro.getNombre() == null) {
            return "redirect:/seguimientoRutinas";
        }
        List<ClienteDTO> clientes = this.clienteService.getClientesDelEntrenadorYFiltro(entrenador.getId(), clienteFiltro.getNombre()); // sacamos los cliente del entrenador

        model.addAttribute("clientes", clientes);
        model.addAttribute("clienteFiltro", clienteFiltro);
        return "crosstrainer/seguimientoRutinas";
    }

    @GetMapping("/verDesempenoo")
    public String doverDesempeno(Model model, HttpSession session,
                                 @RequestParam("idCliente") Integer idCliente) {
        ClienteDTO cliente = clienteService.getClienteById(idCliente);
        model.addAttribute("cliente", cliente);
        List<ClienteRutinaDTO> historialRutinasCliente = clienteRutinaService.historialRutinasCliente(idCliente);
        model.addAttribute("historialRutinasCliente", historialRutinasCliente);
        // ahora vamos a sacar el desempeño/valoracion de los ejercicios realizados por el cliente
        //  List<DesempenoDTO> desempenosCliente = desempenoService.desempenoDelClienteA(idCliente);
        //por algun motivo, no me deja obtener los desempeños de un cliente a través del service, he intentado mil cosas
        //y no hay manera, por tanto, lo he hecho accediendo directamente al repository
        List<Desempeno> desempenosCliente = desempenoRepository.desempenoDelCliente(idCliente);
        model.addAttribute("desempenosCliente", desempenosCliente);
        List<EjercicioEntrenamientoDTO> ejercicios = ejercicioEntrenamientoService.findAll();
        model.addAttribute("ejercicios", ejercicios);
        model.addAttribute("rutinaService", rutinaService);
        return "crosstrainer/verDesempeno";
    }

    @GetMapping("/numeroEntrenamientos")
    public String doMostrarNumeroEntrenmientos(HttpSession session) {

        return "crosstrainer/numeroEntrenamientos";
    }

  /*  @PostMapping("/crearRutina")
    public String doCrearRutina(@RequestParam("nombreRutina") String nombreRutina,
                                @RequestParam("descripcionRutina") String descripcionRutina,
                                @RequestParam("numEntrenamientos") Integer numEntrenamientos,
                                @RequestParam("numeroDia") Integer numeroDia,
                                HttpSession session, Model model) {
        Rutina nuevaRutina = new Rutina();
        nuevaRutina.setNombre(nombreRutina);
        nuevaRutina.setDescripcion(descripcionRutina);
        nuevaRutina.setFechaCreacion(LocalDate.now());
        TipoRutina tr = this.tipo_RutinaRepository.findById(2).orElse(null);
        nuevaRutina.setTipoRutina(tr);
        UsuarioDTO entrenadorE = (UsuarioDTO) session.getAttribute("usuario");
        Usuario ent = this.usuarioRepository.findById(entrenadorE.getId()).orElse(null);
        nuevaRutina.setEntrenador(ent);
        this.rutinaRepository.save(nuevaRutina);
        model.addAttribute("idRutina", nuevaRutina.getId());

        numeroDia++;
        model.addAttribute("numeroDia", numeroDia);

        List<Entrenamiento> entrenamientos = this.entrenamientoRepository.findAll();
        model.addAttribute("entrenamientos", entrenamientos);
        return "crosstrainer/crearRutina";
    } */

    @PostMapping("/crearRutina")
    public String doCrearRutina(@RequestParam("nombreRutina") String nombreRutina,
                                @RequestParam("descripcionRutina") String descripcionRutina,
                                @RequestParam("numEntrenamientos") Integer numEntrenamientos,
                                @RequestParam("numeroDia") Integer numeroDia,
                                HttpSession session, Model model) {
        RutinaDTO nuevaRutina = new RutinaDTO();
        nuevaRutina.setNombre(nombreRutina);
        nuevaRutina.setDescripcion(descripcionRutina);
        TipoRutinaDTO tr = tipoRutinaService.getTipoCrossfit();
        nuevaRutina.setTipoRutina(tr);
        UsuarioDTO entrenadorE = (UsuarioDTO) session.getAttribute("usuario");
        nuevaRutina.setEntrenador(entrenadorE);
        rutinaService.guardarRutina(nuevaRutina);

        Rutina rutina = rutinaRepository.getRutinaByNombre(nombreRutina); // soy consciente de que esto no es del
        // correcto, pero era la unica manera de que me pillase correctamente el id de la rutina
        model.addAttribute("idRutina", rutina.getId());

        numeroDia++;
        model.addAttribute("numeroDia", numeroDia);

        List<EntrenamientoDTO> entrenamientos = entrenamientoService.DTOfindAll();
        model.addAttribute("entrenamientos", entrenamientos);
        return "crosstrainer/crearRutina";
    }

    @PostMapping("/anadirEntrenamiento")
    public String doAnadirEntrenamiento(@RequestParam("idRutina") Integer idRutina,
                                        @RequestParam("idEntrenamiento") Integer idEntrenamiento,
                                        @RequestParam("diaSemana") Integer diaSemana,
                                        @RequestParam("numeroDia") Integer numeroDia,
                                        @RequestParam("numEntrenamientos") Integer numEntrenamientos,
                                        HttpSession session, Model model) {


        EntrenamientoRutina er = new EntrenamientoRutina();
        Rutina rutina = rutinaRepository.findById(idRutina).orElse(null);
        Entrenamiento entrenamiento = entrenamientoRepository.findById(idEntrenamiento).orElse(null);
        er.setRutina(rutina);
        er.setEntrenamiento(entrenamiento);
        er.setDiaSemana(diaSemana);
        this.entrenamiento_RutinaRepository.save(er);

        if (numeroDia == numEntrenamientos) {
            return "redirect:/crud";
        }

        numeroDia++;
        model.addAttribute("numeroDia", numeroDia);

        model.addAttribute("idRutina", idRutina);
        List<EntrenamientoDTO> entrenamientos = entrenamientoService.DTOfindAll();
        model.addAttribute("entrenamientos", entrenamientos);
        return "crosstrainer/crearRutina";
    }

    @GetMapping("/crud/borrar")
    public String doBorrar(@RequestParam("idRutina") Integer idRutina, Model model) {
        RutinaDTO rutina = rutinaService.findById(idRutina);

        rutinaService.borrarRutina(rutina);

        return "redirect:/crud";
    }

    @GetMapping("/crud/editar")
    public String doEditar(@RequestParam("idRutina") Integer idRutina, Model model) {
        Rutina rutina = this.rutinaRepository.findById(idRutina).orElse(null);
        model.addAttribute("rutina", rutina);
        List<EntrenamientoRutina> entrenamientosdeRutina = this.entrenamiento_RutinaRepository.buscarEntrenamientosdeRutina(idRutina);
        model.addAttribute("entrenamientosdeRutina", entrenamientosdeRutina);
        List<Entrenamiento> entrenamientos = this.entrenamientoRepository.findAll();
        model.addAttribute("entrenamientos", entrenamientos);

        return "crosstrainer/editarRutina";
    }

    @PostMapping("/editarEntrenamientosdeRutina")
    public String doEditarEntrenamientosdeRutina(@RequestParam("diaSemana") Integer diaSemana,
                                                 @RequestParam("idEntrenamiento") Integer idEntrenamiento,
                                                 @RequestParam("idEntrenamientoRutina") Integer idEntrenamientoRutina,
                                                 Model model) {

        EntrenamientoRutinaDTO entrut = entrenamientoRutinaService.getEntrenamientoRutina(idEntrenamientoRutina);
        EntrenamientoDTO entren = entrenamientoService.findbyID(idEntrenamiento);
        entrut.setEntrenamiento(entren.getId());
        entrut.setDiaSemana(diaSemana);
        entrut.setRutina(entrut.getRutina());
        entrenamientoRutinaService.guardarEntrenamientodeRutina(entrut);

        return "redirect:/crud/editar?idRutina=" + entrut.getRutina(); // le hace falta el id de la rutina

    }

    @GetMapping("/borrarEntrenamientosdeRutina")
    public String borrarEntrenamientosdeRutina(@RequestParam("id") Integer idEntrenamientoRutina,
                                               Model model) {
        EntrenamientoRutinaDTO er = entrenamientoRutinaService.getEntrenamientoRutina(idEntrenamientoRutina);
        Integer idRutina = er.getRutina();
        entrenamientoRutinaService.borrarEntrenamientodeRutina(er);

        return "redirect:/crud/editar?idRutina=" + idRutina; // le hace falta el id de la rutina
    }

    @GetMapping("/addEntrenamientosdeRutina") // no ref
    public String addEntrenamientosdeRutina(@RequestParam("id") Integer idRutina,
                                            Model model) {
        Rutina rutina = this.rutinaRepository.findById(idRutina).orElse(null);
        model.addAttribute("idRutina", rutina.getId());
        List<Entrenamiento> entrenamientos = this.entrenamientoRepository.findAll();
        model.addAttribute("entrenamientos", entrenamientos);

        return "crosstrainer/addEntrenamientoRutina"; // le hace falta el id de la rutina
    }

    @PostMapping("/guardarEntrenamientoNuevoDeRutina") // no ref
    public String guardarEntrenamientoNuevoDeRutina(@RequestParam("idRutina") Integer idRutina,
                                                    @RequestParam("idEntrenamiento") Integer idEntrenamiento,
                                                    @RequestParam("diaSemana") Integer diaSemana,
                                                    Model model) {
        EntrenamientoRutinaDTO entrut = new EntrenamientoRutinaDTO();
        entrut.setEntrenamiento(idEntrenamiento);
        entrut.setDiaSemana(diaSemana);
        entrut.setRutina(idRutina);
        entrut.setId(-1);
        entrenamientoRutinaService.guardarEntrenamientodeRutina(entrut);

        return "redirect:/crud/editar?idRutina=" + entrut.getRutina(); // le hace falta el id de la rutina
    }

    @PostMapping("/guardarDatosRutina")
    public String doGuardarRutina(@RequestParam("idRutina") Integer idRutina,
                                  @RequestParam("nombreRutina") String nombreRutina,
                                  @RequestParam("descripcionRutina") String descripcionRutina,
                                  Model model, HttpSession session) {


        RutinaDTO ru = new RutinaDTO();
        ru.setNombre(nombreRutina);
        ru.setDescripcion(descripcionRutina);
        ru.setId(idRutina);
        TipoRutinaDTO tr = tipoRutinaService.getTipoCrossfit();
        ru.setTipoRutina(tr);
        UsuarioDTO entrenadorE = (UsuarioDTO) session.getAttribute("usuario");
        ru.setEntrenador(entrenadorE);
        rutinaService.guardarRutina(ru);

        return "redirect:/crud";
    }
}

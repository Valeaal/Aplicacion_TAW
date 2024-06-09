package es.uma.proyectotaw.controller;

import es.uma.proyectotaw.dao.*;
import es.uma.proyectotaw.entity.*;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@Controller
public class CrossfitController {


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


    @GetMapping("/crud")
    public String doCRUD(Model model) {
        List<Rutina> rutinas = rutinaRepository.getCrossfitRutinas();
        model.addAttribute("rutinas", rutinas);
        return "crosstrainer/crudRutinas";
    }

    @GetMapping("/ClientesRutinas")
    public String doAsignarRutinas(Model model) {
        TipoUsuario tipoCliente = tipoUsuarioRepository.buscarPorID(5); // aqui estamos buscando al cliente
        List<Usuario> clientes = this.usuarioRepository.buscarPorTipo(tipoCliente);
        List<Rutina> rutinas = rutinaRepository.findAll();
        model.addAttribute("clientes", clientes);
        model.addAttribute("rutinas", rutinas);
        return "crosstrainer/asignarRutinas";
    }

    @PostMapping("/ClientesRutinas/asignar")
    public String doAsignacionRutinas(@RequestParam("idRutina") Integer idRutina,
                                      @RequestParam("idUser") Integer idUser,
                                      Model model) {

        if (idRutina != null && idUser != null) {
            Rutina rutina = rutinaRepository.findById(idRutina).orElse(null);
            Usuario user = this.usuarioRepository.findById(idUser).orElse(null);
            Cliente cliente = this.clienteRepository.getClienteByUserId(idUser);
            ClienteRutina asignacionRutinaACliente = new ClienteRutina();
            asignacionRutinaACliente.setCliente(cliente);
            asignacionRutinaACliente.setRutina(rutina);
            asignacionRutinaACliente.setVigente(true);
            for(ClienteRutina cr : this.cliente_RutinaRepository.findActiveRoutines()){ // para que la nueva sea la vigente
                cr.setVigente(false);
            }
            this.cliente_RutinaRepository.save(asignacionRutinaACliente);
        }

        return "redirect:/ClientesRutinas";
    }

    @GetMapping("/seguimientoRutinas")
    public String doSeguimientoRutinas() {

        return "crosstrainer/seguimientoRutinas";
    }

    @GetMapping("/numeroEntrenamientos")
    public String doMostrarNumeroEntrenmientos(HttpSession session) {

        return "crosstrainer/numeroEntrenamientos";
    }

    @PostMapping("/crearRutina")
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
        Usuario entrenador = (Usuario) session.getAttribute("usuario");
        nuevaRutina.setEntrenador(entrenador);
        this.rutinaRepository.save(nuevaRutina);
        model.addAttribute("idRutina", nuevaRutina.getId());

        numeroDia++;
        model.addAttribute("numeroDia", numeroDia);

        List<Entrenamiento> entrenamientos = this.entrenamientoRepository.findAll();
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
        List<Entrenamiento> entrenamientos = this.entrenamientoRepository.findAll();
        model.addAttribute("entrenamientos", entrenamientos);
        return "crosstrainer/crearRutina";
    }

    @GetMapping("/crud/borrar")
    public String doBorrar(@RequestParam("idRutina") Integer idRutina, Model model) {
        Rutina rutina = this.rutinaRepository.findById(idRutina).orElse(null);
        for (EntrenamientoRutina erEliminados : this.entrenamiento_RutinaRepository.findAll()) {
            if (erEliminados.getRutina().getId() == idRutina) {
                this.entrenamiento_RutinaRepository.delete(erEliminados);
            }
        }
        for (ClienteRutina cr : this.cliente_RutinaRepository.findAll()) {
            if (cr.getRutina().getId() == idRutina) {
                this.cliente_RutinaRepository.delete(cr);
            }
        }
        this.rutinaRepository.delete(rutina);

        List<Rutina> rutinas = rutinaRepository.findAll();
        model.addAttribute("rutinas", rutinas);
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
        EntrenamientoRutina er = this.entrenamiento_RutinaRepository.findById(idEntrenamientoRutina).orElse(null);

        Entrenamiento entrenamiento = entrenamientoRepository.findById(idEntrenamiento).orElse(null);
        er.setEntrenamiento(entrenamiento);
        er.setDiaSemana(diaSemana);
        this.entrenamiento_RutinaRepository.save(er);
        return "redirect:/crud/editar?idRutina=" + er.getRutina().getId(); // le hace falta el id de la rutina

    }

    @GetMapping("/borrarEntrenamientosdeRutina")
    public String borrarEntrenamientosdeRutina(@RequestParam("id") Integer idEntrenamientoRutina,
                                               Model model) {
        EntrenamientoRutina er = this.entrenamiento_RutinaRepository.findById(idEntrenamientoRutina).orElse(null);
        Integer idRutina = er.getRutina().getId();
        this.entrenamiento_RutinaRepository.delete(er);

        return "redirect:/crud/editar?idRutina=" + idRutina; // le hace falta el id de la rutina
    }

    @GetMapping("/addEntrenamientosdeRutina")
    public String addEntrenamientosdeRutina(@RequestParam("id") Integer idRutina,
                                            Model model) {
        Rutina rutina = this.rutinaRepository.findById(idRutina).orElse(null);
        model.addAttribute("idRutina", rutina.getId());
        List<Entrenamiento> entrenamientos = this.entrenamientoRepository.findAll();
        model.addAttribute("entrenamientos", entrenamientos);

        return "crosstrainer/addEntrenamientoRutina"; // le hace falta el id de la rutina
    }

    @PostMapping("/guardarEntrenamientoNuevoDeRutina")
    public String guardarEntrenamientoNuevoDeRutina(@RequestParam("idRutina") Integer idRutina,
                                                    @RequestParam("idEntrenamiento") Integer idEntrenamiento,
                                                    @RequestParam("diaSemana") Integer diaSemana,
                                                    Model model) {
        EntrenamientoRutina er = new EntrenamientoRutina();
        Rutina rutina = rutinaRepository.findById(idRutina).orElse(null);
        Entrenamiento entrenamiento = entrenamientoRepository.findById(idEntrenamiento).orElse(null);
        er.setRutina(rutina);
        er.setEntrenamiento(entrenamiento);
        er.setDiaSemana(diaSemana);
        this.entrenamiento_RutinaRepository.save(er);

        return "redirect:/crud/editar?idRutina=" + idRutina; // le hace falta el id de la rutina
    }

    @PostMapping("/guardarDatosRutina")
    public String doGuardarRutina(@RequestParam("idRutina") Integer idRutina,
                                  @RequestParam("nombreRutina") String nombreRutina,
                                  @RequestParam("descripcionRutina") String descripcionRutina,
                                  Model model) {

        Rutina r = this.rutinaRepository.findById(idRutina).orElse(null);
        r.setNombre(nombreRutina);
        r.setDescripcion(descripcionRutina);
        this.rutinaRepository.save(r);
        return "redirect:/crud";
    }
}

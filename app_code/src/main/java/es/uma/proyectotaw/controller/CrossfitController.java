package es.uma.proyectotaw.controller;

import es.uma.proyectotaw.dao.*;
import es.uma.proyectotaw.entity.*;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDate;
import java.util.List;

@Controller
@RequestMapping("/crossfit")
public class CrossfitController {


    @Autowired
    private UsuarioRepository usuarioRepository;
    @Autowired
    private TipoUsuarioRepository tipoUsuarioRepository;
    @Autowired
    protected  RutinaRepository rutinaRepository;
    @Autowired
    protected EntrenamientoRepository entrenamientoRepository;
    @Autowired
    protected Entrenamiento_RutinaRepository entrenamiento_RutinaRepository;


    @GetMapping("/crud")
    public String doCRUD(Model model) {
        List<Rutina> rutinas = rutinaRepository.findAll();
        model.addAttribute("rutinas", rutinas);
        return "crosstrainer/crudRutinas";
    }
    @GetMapping("/asignarRutinas")
    public String doAsignarRutinas(Model model) {
        TipoUsuario tipoCliente = tipoUsuarioRepository.buscarPorID(5); // aqui estamos buscando al cliente
        List<Usuario> clientes = usuarioRepository.buscarPorTipo(tipoCliente);
        List<Rutina> rutinas = rutinaRepository.findAll();
        model.addAttribute("clientes", clientes);
        model.addAttribute("rutinas", rutinas);
        return "crosstrainer/asignarRutinas";
    }
    @GetMapping("/seguimientoRutinas")
    public String doSeguimientoRutinas(HttpSession session) {

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
    Usuario entrenador = (Usuario)  session.getAttribute("usuario");
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

        if(numeroDia == numEntrenamientos){
            return "redirect:/crossfit/crud";
        }

        numeroDia++;
        model.addAttribute("numeroDia", numeroDia);

        model.addAttribute("idRutina", idRutina);
        List<Entrenamiento> entrenamientos = this.entrenamientoRepository.findAll();
        model.addAttribute("entrenamientos", entrenamientos);
        return "crosstrainer/crearRutina";
    }

    @GetMapping("/crud/borrar")
    public String doBorrar(@RequestParam("idRutina") Integer idRutina ,Model model) {
        Rutina rutina = this.rutinaRepository.findById(idRutina).orElse(null);
        for(EntrenamientoRutina erEliminados : this.entrenamiento_RutinaRepository.findAll()) {
            if(erEliminados.getRutina().getId() == idRutina) {
                this.entrenamiento_RutinaRepository.delete(erEliminados);
            }
        }
        this.rutinaRepository.delete(rutina);

        List<Rutina> rutinas = rutinaRepository.findAll();
        model.addAttribute("rutinas", rutinas);
        return "redirect:/crossfit/crud";
    }

    @GetMapping("/crud/editar")
    public String doEditar(@RequestParam("idRutina") Integer idRutina ,Model model) {
        Rutina rutina = this.rutinaRepository.findById(idRutina).orElse(null);
        model.addAttribute("rutina", rutina);
        List<EntrenamientoRutina> entrenamientosdeRutina = this.entrenamiento_RutinaRepository.buscarEntrenamientosdeRutina(idRutina);
        model.addAttribute("entrenamientosdeRutina", entrenamientosdeRutina);
        return "crosstrainer/editarRutina";
    }
}

/*
@author: Miguel Galdeano Rodríguez
 */
package es.uma.proyectotaw.controller;

import es.uma.proyectotaw.dto.*;
import es.uma.proyectotaw.service.*;
import es.uma.proyectotaw.entity.*;
import es.uma.proyectotaw.dao.*;
import es.uma.proyectotaw.ui.FiltroBodyBuilder;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cglib.core.Local;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.chrono.ChronoLocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Controller
@RequestMapping("/bodybuilding")
public class BodybuildingController {
    //Service
    @Autowired
    EntrenamientoService entrenamientoService;
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

    @GetMapping("/")
    public String doListar(Model model, HttpSession session){
        List<Rutina> rutinas = this.rutinaRepository.findByTipo(0);
        FiltroBodyBuilder filtro = new FiltroBodyBuilder();
        model.addAttribute("filtro", filtro);
        model.addAttribute("rutinas", rutinas);
        return "bodybuilding/listadoRutinas";
    }

    public void inicializacion(Model model){
        List<Entrenamiento> entrenamientos = this.entrenamientoService.findAll();
        model.addAttribute("entrenamientos", entrenamientos);
    }
    /* TO DO:
        - Añadir editar
        - Añadir crear rutina
    */
    @GetMapping("/editarRutina")
    public String doEditarEjercicio(@RequestParam("id")Integer id, Model model, HttpSession session){
        Rutina rutina = this.rutinaRepository.findById(id).orElse(null);
        List<GrupoMuscular> gruposMusculares = this.grupoMuscularRepository.findAll();
        List<TipoEjercicio> tiposEjercicios = this.tipoEjercicioRepository.findAll();
        List<Entrenamiento> entrenamientos = this.entrenamientoService.findAll();
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
        rutina.setTipoRutina(this.tipoRutinaRepository.findById(0).orElse(null));
        //rutina.setEntrenador(this.usuarioRepository.findById(5).orElse(null));
        rutina.setEntrenador((Usuario) session.getAttribute("usuario"));
        rutina.setFechaCreacion(LocalDate.parse(new java.sql.Date(System.currentTimeMillis()).toString()));
        List<GrupoMuscular> gruposMusculares = this.grupoMuscularRepository.findAll();
        List<TipoEjercicio> tiposEjercicios = this.tipoEjercicioRepository.findAll();
        List<Entrenamiento> entrenamientos = this.entrenamientoService.findAll();
        List<EntrenamientoRutina> entrenamientoRutinas = this.entrenamientoRutinaRepository.findAll();
        model.addAttribute("entrenamientoRutinas", entrenamientoRutinas);
        model.addAttribute("entrenamientos", entrenamientos);
        model.addAttribute("gruposMusculares", gruposMusculares);
        model.addAttribute("tiposEjercicios", tiposEjercicios);
        model.addAttribute("rutina", rutina);
        this.inicializacion(model);
        return "bodybuilding/editarRutina";
    }
    @GetMapping("/eliminarRutina")
    public String doEliminarRutina(@RequestParam("id")Integer id,Model model, HttpSession session){
        Rutina rutina = this.rutinaRepository.findById(id).orElse(null);
        this.rutinaRepository.delete(rutina);
        return "redirect:/bodybuilding/listar";
    }
    /* TO DO:
    - Añadir filtrado por fecha
     */
    @PostMapping("/filtrarRutinas")
    public String doFiltrar(@ModelAttribute("filtro")FiltroBodyBuilder filtro, Model model, HttpSession session){
        List<Rutina> rutinas;
        //LocalDate fechaFin = LocalDate.now();
        //LocalDate fechaInicio = fechaFin.minusYears(4);
        /*
        if(filtro.getFechaFin()!=null){
             fechaFin = filtro.getFechaFin();
        }
        if(filtro.getFechaInicio()!=null){
            fechaInicio = filtro.getFechaInicio();
        }*/
        if(filtro.getActivo()){
            if(filtro.getNumEntrenamientos().equals("-")){
                //Filtro solo por nombre y fecha
                rutinas = this.rutinaRepository.findByNombre(filtro.getNombre(), filtro.getFechaInicio(),filtro.getFechaFin(),0);
            }else {
                //Filtro por nombre y fecha y numero de entrenos
                rutinas = this.rutinaRepository.findByNombreAndEntrenos(filtro.getNombre(),Integer.parseInt(filtro.getNumEntrenamientos()), 0,filtro.getFechaInicio(),filtro.getFechaFin());
            }
        }else{
            return "redirect:/bodybuilding/listar";
        }
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
        //this.entrenamientoService.guardar(entrenamiento);
        return "redirect:/bodybuilding/editarRutina?id=" + idRutina;
    }

    @PostMapping("/guardarCambiosEdicion")
    public String doGuardarCambios (@ModelAttribute("rutina")Rutina rutina, Model model, HttpSession session){
        this.rutinaRepository.save(rutina);
        return "redirect:/bodybuilding/listar";
    }

    @GetMapping("/anyadirEntrenamientoRutina")
    public String doAnyadirEntrenamientoRutina(@RequestParam("id")Integer id,@RequestParam("dia")Integer dia,Model model, HttpSession session){
        EntrenamientoRutina ER = new EntrenamientoRutina();
        Rutina rutina = this.rutinaRepository.findById(id).orElse(null);
        ER.setRutina(rutina);
        ER.setDiaSemana(dia);
        model.addAttribute("ER",ER);
        List<Entrenamiento> entrenamientos = this.entrenamientoService.findAll();
        model.addAttribute("entrenamientos", entrenamientos);
        return "anyadirEntrenoRutina";
    }

    @GetMapping("/asignarRutinas")
    public String asignarRutinaCliente(Model model, HttpSession session){
        EntrenamientoRutina ER = new EntrenamientoRutina();
        List<Rutina> rutinas = this.rutinaRepository.findByTipo(0);
        List<Usuario> clientes = this.clienteRepository.findByEntrenador((Usuario) session.getAttribute("usuario"));
        model.addAttribute("rutinas",rutinas);
        model.addAttribute("clientes",clientes);
        return "bodybuilding/asignacionRutinas";
    }

    @PostMapping("/guardarEntrenamientoRutina")
    public String doAnyadirEntrenamientoRutina(@ModelAttribute("ER")EntrenamientoRutina ER,Model model, HttpSession session){
        this.entrenamientoRutinaRepository.save(ER);
        return "redirect:/bodybuilding/editarRutina?id=" + ER.getRutina().getId();
    }

    public Rutina inicializarRutina(){
        Rutina rutina = new Rutina();
        this.rutinaRepository.save(rutina);
        return rutina;
    }
}

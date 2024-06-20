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
    TipoEjercicioService tipoEjercicioService;
    @Autowired
    GrupoMuscularService grupoMuscularService;
    @Autowired
    EntrenamientoRutinaService entrenamientoRutinaService;
    @Autowired
    TipoRutinaService tipoRutinaService;
    @Autowired
    UsuarioService usuarioService;
    @Autowired
    ClienteService clienteService;
    @Autowired
    ClienteRutinaService clienteRutinaService;
    @Autowired
    DesempenoService desempenoService;
    @Autowired
    EjercicioEntrenamientoService ejercicioEntrenamientoService;
    @Autowired
    EjercicioService ejercicioService;
    private int tipo_rutina = 1;

    @GetMapping("/")
    public String doListar(Model model, HttpSession session){
        List<RutinaDTO> rutinas = this.rutinaService.findByTipo(tipo_rutina);
        FiltroBodyBuilder filtro = new FiltroBodyBuilder();
        List<UsuarioDTO> entrenadores = this.usuarioService.buscarPorTipoUsuario(2);
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
        List<GrupoMuscularDTO> gruposMusculares = this.grupoMuscularService.findAll();
        List<TipoEjercicioDTO> tiposEjercicios = this.tipoEjercicioService.findAll();

        List<EntrenamientoDTO> entrenamientos = this.entrenamientoService.findAll();
        Map<Integer,EntrenamientoDTO> entrenamientosConId = new HashMap<>();
        for(EntrenamientoDTO entrenamiento : entrenamientos){
            entrenamientosConId.put(entrenamiento.getId(), entrenamiento);
        }

        List<EjercicioDTO> ejercicios = this.ejercicioService.findAll();
        Map<Integer,EjercicioDTO> ejerciciosConId = new HashMap<>();
        for(EjercicioDTO ej : ejercicios){
            ejerciciosConId.put(ej.getId(), ej);
        }

        List<EntrenamientoRutinaDTO> entrenamientoRutinas = this.entrenamientoRutinaService.findAll();

        model.addAttribute("entrenamientoRutinas", entrenamientoRutinas);
        model.addAttribute("mapEntrenamientos", entrenamientosConId);
        model.addAttribute("mapEjercicios", ejerciciosConId);
        model.addAttribute("gruposMusculares", gruposMusculares);
        model.addAttribute("tiposEjercicios", tiposEjercicios);
        model.addAttribute("rutina", rutina);

        return "bodybuilding/editarRutina";
    }

    @GetMapping("/crearRutina")
    public String doCrearRutina(Model model, HttpSession session){
        RutinaDTO rutina = new RutinaDTO();

        List<EntrenamientoDTO> entrenamientos = this.entrenamientoService.findAll();
        Map<Integer,EntrenamientoDTO> entrenamientosConId = new HashMap<>();
        for(EntrenamientoDTO entrenamiento : entrenamientos){
            entrenamientosConId.put(entrenamiento.getId(), entrenamiento);
        }

        List<EjercicioDTO> ejercicios = this.ejercicioService.findAll();
        Map<Integer,EjercicioDTO> ejerciciosConId = new HashMap<>();
        for(EjercicioDTO ejercicio : ejercicios){
            ejerciciosConId.put(ejercicio.getId(), ejercicio);
        }
        rutina.setTipoRutina(this.tipoRutinaService.findById(tipo_rutina));
        rutina.setEntrenador((UsuarioDTO) session.getAttribute("usuario"));
        rutina.setFechaCreacion(LocalDate.parse(new java.sql.Date(System.currentTimeMillis()).toString()));
        model.addAttribute("rutina", rutina);
        model.addAttribute("ejercicios", ejerciciosConId);
        model.addAttribute("entrenamientos", entrenamientosConId);

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

        if(filtro.getNumEntrenamientos().equals("-") && filtro.getEntrenadorCreador().getId()==null){
            //Filtro solo por nombre
            rutinas = this.rutinaService.findByNombre(filtro.getNombre(),tipo_rutina,fecha);
        }else if(filtro.getEntrenadorCreador().getId()==null){
            //Filtro por nombre y numero de entrenos
            rutinas = this.rutinaService.findByNombreAndEntrenos(filtro.getNombre(),Integer.parseInt(filtro.getNumEntrenamientos()), tipo_rutina,fecha);
        }else if(filtro.getNumEntrenamientos().equals("-")){
            //Filtro por nombre y creador
            rutinas = this.rutinaService.findByNombreAndCreador(filtro.getNombre(),filtro.getEntrenadorCreador().getId(), tipo_rutina,fecha);
        }else{
            //Filtro por nombre, numero de entrenos y creador
            rutinas = this.rutinaService.findByNombreEntrenosAndCreador(filtro.getNombre(),Integer.parseInt(filtro.getNumEntrenamientos()),filtro.getEntrenadorCreador().getId(), tipo_rutina,fecha);
        }
        List<UsuarioDTO> entrenadores = this.usuarioService.buscarPorTipoUsuario(2);
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
            return "redirect:/bodybuilding/";
        }else{
            RutinaDTO r = this.rutinaService.findById(rutina.getId());
            rutina.setClientes(r.getClientes());
            rutina.setEntrenamientos(r.getEntrenamientos());
            this.rutinaService.guardar(rutina);
            return "redirect:/bodybuilding/";
        }
    }

    @GetMapping("/anyadirEntrenamientoRutina")
    public String doAnyadirEntrenamientoRutina(@RequestParam("id")Integer idRutina,@RequestParam("dia")Integer dia,Model model, HttpSession session){
        EntrenamientoRutinaDTO ER = new EntrenamientoRutinaDTO();
        RutinaDTO rutina = this.rutinaService.findById(idRutina);
        ER.setRutina(rutina.getId());
        ER.setDiaSemana(dia);
        model.addAttribute("ER",ER);
        List<EntrenamientoDTO> entrenamientos = this.entrenamientoService.findAll();
        model.addAttribute("rutina", rutina);
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
            cr.setCliente(cliente.getId());
            cr.setRutina(rutina.getId());
            cr.setVigente(true);
            if (activa != null ) {
                activa.setVigente(false);
                this.clienteRutinaService.guardar1(activa);
            }
            this.clienteRutinaService.guardar1(cr);
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
                RutinaDTO rutina = this.rutinaService.findById(crs.getRutina());
                rutinas.put(cliente,rutina);
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
        List<DesempenoDTO> desempenos = this.desempenoService.getByClienteId(idCliente);
        Map<Integer,EjercicioEntrenamientoDTO> ENR = new HashMap<Integer,EjercicioEntrenamientoDTO>();

        for(DesempenoDTO desempeno : desempenos){
            ENR.put(desempeno.getId(),this.ejercicioEntrenamientoService.findByDesempeno(desempeno.getId()));
        }
        model.addAttribute("cliente",cliente);
        model.addAttribute("desempenos",desempenos);
        model.addAttribute("ENR",ENR);
        return"bodybuilding/verComentarios";
    }

    public RutinaDTO inicializarRutina(){
        RutinaDTO rutina = new RutinaDTO();
        this.rutinaService.guardar(rutina);
        return rutina;
    }
}

/*
@author: Miguel Galdeano Rodríguez
 */
package es.uma.proyectotaw.controller;

import es.uma.proyectotaw.dao.*;
import es.uma.proyectotaw.entity.*;
import es.uma.proyectotaw.ui.FiltroBodyBuilder;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

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

    @GetMapping("/listar")
    public String doListar(Model model, HttpSession session){
        List<Rutina> rutinas = this.rutinaRepository.findByTipo(0);
        FiltroBodyBuilder filtro = new FiltroBodyBuilder();
        model.addAttribute("filtro", filtro);
        model.addAttribute("rutinas", rutinas);
        return "bodybuilding/listadoRutinas";
    }

    public void inicializacion(Model model){
        List<Entrenamiento> entrenamientos = this.entrenamientoRepository.findAll();
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
    /* TO DO:
    - Añadir filtrado por fecha
     */
    @PostMapping("/filtrarEntrenamientos")
    public String doFiltrar(@ModelAttribute("filtro")FiltroBodyBuilder filtro, Model model, HttpSession session){
        List<Rutina> rutinas;
        if(filtro.getActivo()){
            if(filtro.getNumEntrenamientos().equals("-")){
                rutinas = this.rutinaRepository.findByNombre(filtro.getNombre(),0);
            }else{
                rutinas = this.rutinaRepository.findByNombreAndNumEntrenamientos(filtro.getNombre(),Integer.parseInt(filtro.getNumEntrenamientos()),0);
            }
        }else{
            return "redirect:/bodybuilding/listar";
        }
        model.addAttribute("rutinas", rutinas);
        return "bodybuilding/listadoRutinas";
    }
    @PostMapping("/guardarCambiosEdicion")
    public String doGuardarCambios (@ModelAttribute("rutina")Rutina rutina, Model model, HttpSession session){
        this.rutinaRepository.save(rutina);
        return "redirect:/bodybuilding/listar";
    }


    public Rutina inicializarRutina(){
        Rutina rutina = new Rutina();
        this.rutinaRepository.save(rutina);
        return rutina;
    }
}

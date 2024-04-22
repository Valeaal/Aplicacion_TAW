package es.uma.proyectotaw.controller;

import org.springframework.ui.Model;
import es.uma.proyectotaw.entity.*;
import es.uma.proyectotaw.dao.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import java.util.*;

@Controller
public class AppController {

    @Autowired
    protected EjercicioRepository ejercicioRepository;

    @GetMapping("/")
    public String inicio(Model model) {
        List<Ejercicio> listaEjerciciosCompleta= this.ejercicioRepository.findAll();
        model.addAttribute("listaEjerciciosCompleta", listaEjerciciosCompleta);
        return "Inicio";
    }

}

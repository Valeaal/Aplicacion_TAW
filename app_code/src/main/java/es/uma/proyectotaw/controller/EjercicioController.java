package es.uma.proyectotaw.controller;

import es.uma.proyectotaw.dao.EjercicioRepository;
import es.uma.proyectotaw.entity.Ejercicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class EjercicioController {

    @Autowired
    protected EjercicioRepository ejercicioRepository;

    @GetMapping("/ejercicios/listar")
    public String inicio(Model model) {
        List<Ejercicio> listaEjerciciosCompleta= this.ejercicioRepository.findAll();
        model.addAttribute("listaEjerciciosCompleta", listaEjerciciosCompleta);
        return "home";
    }

}

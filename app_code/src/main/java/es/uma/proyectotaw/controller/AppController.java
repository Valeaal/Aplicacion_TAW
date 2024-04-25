package es.uma.proyectotaw.controller;

import org.springframework.ui.Model;
import es.uma.proyectotaw.entity.*;
import es.uma.proyectotaw.dao.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.*;

@Controller
public class AppController {

    @Autowired
    protected EjercicioRepository ejercicioRepository;

    @Autowired
    protected UsuarioRepository usuarioRepository;

    @GetMapping("/login")
    public String login(Model model) {
        return "login";
    }

    @PostMapping("/logear")
    public String logear(@RequestParam(value = "email", required = true) String inputEmail,
                         @RequestParam(value = "password", required = true) String password,
                         Model model) {

        Usuario user = usuarioRepository.buscarPorEmail(inputEmail);

        //nos hemos quedado por aquí

        return "login";
    }

    @GetMapping("/")
    public String home(Model model) {
        List<Ejercicio> listaEjerciciosCompleta= this.ejercicioRepository.findAll();
        model.addAttribute("listaEjerciciosCompleta", listaEjerciciosCompleta);
        return "home";
    }



}

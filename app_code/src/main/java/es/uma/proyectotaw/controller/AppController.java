package es.uma.proyectotaw.controller;

import jakarta.servlet.http.HttpSession;
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
                         HttpSession session, Model model) {

        Usuario user = usuarioRepository.buscarPorEmail(inputEmail);

        //Esto es para cargar el inicio, que pide una variable a la bdd
        List<Ejercicio> listaEjerciciosCompleta= this.ejercicioRepository.findAll();
        model.addAttribute("listaEjerciciosCompleta", listaEjerciciosCompleta);

        if (user == null) {
            model.addAttribute("error", "Error: Usuario no encontrado");
            return "login";
        } else if (!password.equals(user.getPassword())) {
            model.addAttribute("error", "Error: Contraseña incorrecta");
            return "login";
        } else{
            session.setAttribute("usuario", user);
            return "home";
        }
    }

    @PostMapping("/salir")
    public String salir(HttpSession session) {
        session.invalidate();
        return "home";
    }

    @GetMapping("/")
    public String home(Model model) {
        List<Ejercicio> listaEjerciciosCompleta= this.ejercicioRepository.findAll();
        model.addAttribute("listaEjerciciosCompleta", listaEjerciciosCompleta);
        return "home";
    }



}

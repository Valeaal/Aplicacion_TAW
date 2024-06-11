//Autor: Álvaro Valencia Vilallón
package es.uma.proyectotaw.controller;

import es.uma.proyectotaw.dto.EjercicioDTO;
import es.uma.proyectotaw.dto.TipoUsuarioDTO;
import es.uma.proyectotaw.dto.UsuarioDTO;
import es.uma.proyectotaw.service.AppService;
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
    private AppService appService;


    @GetMapping("/login")
    public String login(Model model) {
        return "login";
    }

    @PostMapping("/logear")
    public String logear(@RequestParam(value = "email", required = true) String inputEmail,
                         @RequestParam(value = "password", required = true) String password,
                         HttpSession session, Model model) {

        UsuarioDTO user = appService.buscarPorEmail(inputEmail);

        if (user == null) {
            model.addAttribute("error", "Error: Usuario no encontrado");
            return "login";
        } else if (!password.equals(user.getPassword())) {
            model.addAttribute("error", "Error: Contraseña incorrecta");
            return "login";
        } else{
            TipoUsuarioDTO tipoUsuario = appService.buscarPorID(user.getTipoUsuario().getId());
            //Añadimos a la sesion el atributo del tipo de usuario (otra consulta SQL) cuando ya sabemos que tenemos el usuario not null
            session.setAttribute("usuario", user);
            session.setAttribute("tipo", tipoUsuario);
            return "redirect:/";    //Si no usara el redirect y llamara directamente a home, tendría que cargar al model los atributos necesarios en cada metodo que devolviera a home
        }

    }

    @GetMapping("/salir")
    public String salir(HttpSession session) {
        session.invalidate();
        return "redirect:/";    //Si no usara el redirect y llamara directamente a home, tendría que cargar al model los atributos necesarios en cada metodo que devolviera a home
    }

    @GetMapping("/")
    public String home(Model model) {

        //Esto es para cargar el inicio, que pide una variable a la bdd
        List<EjercicioDTO> listaEjerciciosCompleta= appService.listaEjerciciosCompleta();
        model.addAttribute("listaEjerciciosCompleta", listaEjerciciosCompleta);

        return "home";
    }


}

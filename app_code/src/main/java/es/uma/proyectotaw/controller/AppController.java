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
    @Autowired
    private TipoUsuarioRepository tipoUsuarioRepository;
    @Autowired
    private RutinaRepository rutinaRepository;
    @Autowired
    private ClienteRepository clienteRepository;
    @Autowired
    private EntrenamientoRepository entrenamientoRepository;


    @GetMapping("/login")
    public String login(Model model) {
        return "login";
    }

    @PostMapping("/logear")
    public String logear(@RequestParam(value = "email", required = true) String inputEmail,
                         @RequestParam(value = "password", required = true) String password,
                         HttpSession session, Model model) {

        Usuario user = usuarioRepository.buscarPorEmail(inputEmail);

        if (user == null) {
            model.addAttribute("error", "Error: Usuario no encontrado");
            return "login";
        } else if (!password.equals(user.getPassword())) {
            model.addAttribute("error", "Error: Contraseña incorrecta");
            return "login";
        } else{
            TipoUsuario tipoUsuario = tipoUsuarioRepository.buscarPorID(user.getTipoUsuario().getId());
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
        List<Ejercicio> listaEjerciciosCompleta= this.ejercicioRepository.findAll();
        model.addAttribute("listaEjerciciosCompleta", listaEjerciciosCompleta);

        return "home";
    }

    @GetMapping("/rutina")
    public String rutina(@RequestParam("id") Integer id, Model model) {
        Usuario user = usuarioRepository.getReferenceById(id);
        Cliente client = clienteRepository.getClienteByUserId(id);
        Rutina rutina = (Rutina) rutinaRepository.getActiveRutinasByClienteId(client.getId()).get(0);
        List<Entrenamiento> entrenamientos = entrenamientoRepository.findByRutinaId(rutina.getId());
        model.addAttribute("rutina", rutina);
        model.addAttribute("cliente", client);
        model.addAttribute("entrenamientos", entrenamientos);
        return "cliente/rutina";
    }


}

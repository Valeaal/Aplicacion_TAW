package es.uma.proyectotaw.controller;

import org.springframework.ui.Model;
import es.uma.proyectotaw.entity.*;
import es.uma.proyectotaw.dao.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import java.util.List;

@Controller
public class ClienteController {
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
    @Autowired
    private GrupoMuscularRepository grupoMuscularRepository;

    @GetMapping("/dia")
    public String getDia(@RequestParam("id") Integer id, Model model){
        List<Ejercicio> ejercicios = ejercicioRepository.findEjerciciosByEntrenamientoId(id);
        model.addAttribute("ejercicios", ejercicios);

        return "/cliente/diaRutina";
    }
}

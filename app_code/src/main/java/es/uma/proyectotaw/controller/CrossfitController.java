package es.uma.proyectotaw.controller;

import es.uma.proyectotaw.dao.*;
import es.uma.proyectotaw.entity.Rutina;
import es.uma.proyectotaw.entity.TipoUsuario;
import es.uma.proyectotaw.entity.Usuario;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequestMapping("/crossfit")
public class CrossfitController {


    @Autowired
    private UsuarioRepository usuarioRepository;
    @Autowired
    private TipoUsuarioRepository tipoUsuarioRepository;
    @Autowired
    protected  RutinaRepository rutinaRepository;



    @GetMapping("/crud")
    public String doCRUD(HttpSession session) {

        return "crosstrainer/crudRutinas";
    }
    @GetMapping("/asignarRutinas")
    public String doAsignarRutinas(Model model) {
        TipoUsuario tipoCliente = tipoUsuarioRepository.buscarPorID(5); // aqui estamos buscando al cliente
        List<Usuario> clientes = usuarioRepository.buscarPorTipo(tipoCliente);
        List<Rutina> rutinas = rutinaRepository.findAll();
        model.addAttribute("clientes", clientes);
        model.addAttribute("rutinas", rutinas);
        return "crosstrainer/asignarRutinas";
    }
    @GetMapping("/seguimientoRutinas")
    public String doSeguimientoRutinas(HttpSession session) {

        return "crosstrainer/seguimientoRutinas";
    }
    @GetMapping("/numeroEntrenamientos")
    public String doMostrarNumeroEntrenmientos(HttpSession session) {

        return "crosstrainer/numeroEntrenamientos";
    }
    @GetMapping("/crearRutina")
    public String doCrearRutina(@RequestParam("numEntrenamientos") Integer numEntrenamientos, HttpSession session, Model model) {

        return "crosstrainer/crearRutina";
    }

}

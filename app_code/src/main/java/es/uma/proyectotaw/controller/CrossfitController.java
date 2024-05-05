package es.uma.proyectotaw.controller;

import es.uma.proyectotaw.dao.*;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/crossfit")
public class CrossfitController {


    @Autowired
    private UsuarioRepository usuarioRepository;
    @Autowired
    private TipoUsuarioRepository tipoUsuarioRepository;



    @GetMapping("/crud")
    public String doCRUD(HttpSession session) {

        return "crosstrainer/crudRutinas";
    }
    @GetMapping("/asignarRutinas")
    public String doAsignarRutinas(HttpSession session) {

        return "crosstrainer/asignarRutinas";
    }
    @GetMapping("/seguimientoRutinas")
    public String doSeguimientoRutinas(HttpSession session) {

        return "crosstrainer/seguimientoRutinas";
    }

}

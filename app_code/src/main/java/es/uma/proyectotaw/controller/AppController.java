package es.uma.proyectotaw.controller;

import ch.qos.logback.core.model.Model;
import es.uma.proyectotaw.dao.ClienteRepository;
import es.uma.proyectotaw.dao.ComidaRepository;
import es.uma.proyectotaw.dao.EjercicioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AppController {

    @GetMapping("/")
    public String inicio(Model model) {
        return null;
    }

}

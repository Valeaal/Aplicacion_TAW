package es.uma.proyectotaw.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin")

public class AdminController {

    @GetMapping("/clientes")
    public String login(Model model) {

        return "administrador/clientes";
    }

}

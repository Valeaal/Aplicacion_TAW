package es.uma.proyectotaw.controller;

import es.uma.proyectotaw.dao.*;
import es.uma.proyectotaw.entity.Cliente;
import es.uma.proyectotaw.entity.Dieta;
import es.uma.proyectotaw.entity.Menu;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.ui.Model;

import java.util.List;

@Controller
@RequestMapping("/dietista")
public class DietistaController {

    @Autowired
    private ClienteRepository clienteRepository;
    @Autowired
    private DietaRepository dietaRepository;
    @Autowired
    private MenuRepository menuRepository;
    

    @GetMapping("/dietas")
    public String doDietas(Model model){
        String strTo = "crudDietas";
        List<Dieta> dietas = this.dietaRepository.findAll();
        model.addAttribute("dietas",dietas);
        return strTo;
    }

    @GetMapping("/dietas/nueva")
    public String doNuevaDieta(Model model){
        String strTo = "creacionDietas";
        List<Menu> menus = this.menuRepository.findAll();
        model.addAttribute("dietas",menus);
        return strTo;
    }

    @GetMapping("/seguimiento")
    public String doSeguimiento(Model model){
        String strTo = "seguimiento";
        List<Cliente> clientes = this.clienteRepository.clientesConDietas();
        model.addAttribute("clientes",clientes);
        return strTo;
    }




}
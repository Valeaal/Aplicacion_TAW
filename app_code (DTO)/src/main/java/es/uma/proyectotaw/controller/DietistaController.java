package es.uma.proyectotaw.controller;

import es.uma.proyectotaw.dao.*;
import es.uma.proyectotaw.entity.*;
import es.uma.proyectotaw.ui.ComidasDieta;
import es.uma.proyectotaw.ui.FiltroDietas;
import es.uma.proyectotaw.ui.NuevaDieta;
import es.uma.proyectotaw.ui.SeguimientoDietasCliente;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.ui.Model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Controller
public class DietistaController {

    @Autowired
    private ClienteRepository clienteRepository;
    @Autowired
    private DietaRepository dietaRepository;
    @Autowired
    private MenuRepository menuRepository;
    @Autowired
    private TipoUsuarioRepository tipoUsuarioRepository;
    @Autowired
    private UsuarioRepository usuarioRepository;
    @Autowired
    private Dieta_ComidaRepository dietaComidaRepository;


    @GetMapping("/dietistaAsignacion")
    public String asignacionDietas(Model model) {

        TipoUsuario tipoCliente = this.tipoUsuarioRepository.buscarPorID(5);
        List<Cliente> clientes = this.clienteRepository.getClientesSinDieta();

        List<Dieta> dietas = this.dietaRepository.findAll();

        model.addAttribute("clientes", clientes);
        model.addAttribute("dietas", dietas);

        return "dietista/asignacion";
    }

    @GetMapping("/dietistaAsignacion/asignar")
    public String clientesDietaAsignar(@RequestParam(required = false, name = "clienteSeleccionado") Integer clienteId,
                                              @RequestParam(required = false, name = "dietaSeleccionado") Integer dietaId,
                                              Model model) {

        if (clienteId != null && dietaId != null) {
            Cliente cliente = clienteRepository.getClienteById(clienteId);
            Dieta dieta = dietaRepository.buscarPorID(dietaId);

            cliente.setDieta(dieta);

            clienteRepository.save(cliente);
        }

        return "redirect:/dietistaAsignacion";
    }

    @GetMapping("/dietas")
    public String doDietas(Model model) {
        List<Dieta> dietas = dietaRepository.findAll();
        model.addAttribute("dietas", dietas);
        model.addAttribute("FiltroDietas", new FiltroDietas());
        return "dietista/crudDietas";
    }

    @GetMapping("/dietas/eliminar")
    public String doBorrar(@RequestParam("id") Integer id ,Model model) {
        Dieta dieta = this.dietaRepository.findById(id).orElse(null);
        for(DietaComida dc : this.dietaComidaRepository.findAll()) {
            if(dc.getDieta().getId() == id) {
                this.dietaComidaRepository.delete(dc);
            }
        }
        this.dietaRepository.delete(dieta);

        List<Dieta> dietas = dietaRepository.findAll();
        model.addAttribute("dietas", dietas);
        return "redirect:/dietas";
    }

    @GetMapping("/dietistaSeguimiento")
    public String doSeguimientoClientes(Model model){
        List<Cliente> clientes = this.clienteRepository.getClientesConDieta();
        model.addAttribute("clientes", clientes);
        model.addAttribute("SeguimientoDietasCliente", new SeguimientoDietasCliente());
        return "dietista/seguimientoClientes";
    }

    @PostMapping("/filtrarClientesSeguimiento")
    public String buscarClientes(@ModelAttribute("SeguimientoDietasCliente") SeguimientoDietasCliente seguimientoDietasCliente, Model model) {
        if(seguimientoDietasCliente.getDieta()==""){seguimientoDietasCliente.setDieta(null);}
        if(seguimientoDietasCliente.getNombre()==""){seguimientoDietasCliente.setNombre(null);}
        List<Cliente> clientes = clienteRepository.getClienteFiltrado(seguimientoDietasCliente.getEdad(), seguimientoDietasCliente.getNombre(), seguimientoDietasCliente.getDieta());
        model.addAttribute("clientes", clientes);
        return "dietista/seguimientoClientes";
    }

    @PostMapping("/filtrarDietas")
    public String filtrarDietas(@ModelAttribute("FiltroDietas") FiltroDietas filtroDietas, Model model){
        if(filtroDietas.getNombre()==""){filtroDietas.setNombre(null);}
        List<Dieta> dietas = dietaRepository.getDietasFiltradas(filtroDietas.getNombre(), filtroDietas.getCalorias());
        model.addAttribute("dietas", dietas);
        return "dietista/crudDietas";
    }

    @GetMapping("/dietaCrear")
    public String mostrarFormularioNuevaDieta(Model model) {
        model.addAttribute("nuevaDieta", new NuevaDieta());
        return "dietista/nuevaDieta";
    }

    @PostMapping("/crearDieta")
    public String procesarFormularioNuevaDieta(@ModelAttribute("nuevaDieta") NuevaDieta nuevaDieta, Model model, HttpSession sesion) {
        String strTo = "";
        Dieta dieta = new Dieta();
        dieta.setNombre(nuevaDieta.getNombre());
        dieta.setDescripcion(nuevaDieta.getDescripcion());
        dieta.setCalorias(nuevaDieta.getCalorias());
        dieta.setFecha(LocalDate.now());
        Usuario dietista = (Usuario) sesion.getAttribute("usuario");
        dieta.setDietista(dietista);
        this.dietaRepository.save(dieta);
        sesion.setAttribute("cantidadIngestas", nuevaDieta.getComidasDiarias());
        model.addAttribute("ComidasDieta", new ComidasDieta());
        if((Integer) sesion.getAttribute("cantidadIngestas")==3){
            strTo = "dietista/nuevaDietaComidas3";
        }
        if((Integer) sesion.getAttribute("cantidadIngestas")==4){
            strTo = "dietista/nuevaDietaComidas4";
        }
        if((Integer) sesion.getAttribute("cantidadIngestas")==5){
            strTo = "dietista/nuevaDietaComidas5";
        }
        
        return strTo;
    }


    @GetMapping("/dietas/modificar")
    public String doModificar(@RequestParam("id") Integer id ,Model model) {
        Dieta dieta = this.dietaRepository.findById(id).orElse(null);
        model.addAttribute("nuevaDieta", new NuevaDieta());
        model.addAttribute("dieta", dieta);
        return "dietista/editarDieta";
    }

    @PostMapping("/editarDieta")
    public String doEditarDieta(@ModelAttribute("nuevaDieta") NuevaDieta nuevaDieta, Model model){
        Dieta dieta = this.dietaRepository.findById(nuevaDieta.getId()).orElse(null);
        dieta.setNombre(nuevaDieta.getNombre());
        dieta.setDescripcion(nuevaDieta.getDescripcion());
        dieta.setCalorias(nuevaDieta.getCalorias());
        this.dietaRepository.save(dieta);

        return null;
        //Lo he pusto pq  si no no compila srry
        
    }






}
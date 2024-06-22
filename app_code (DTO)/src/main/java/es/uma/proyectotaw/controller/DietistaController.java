//Autor: Javier Torrecilla

package es.uma.proyectotaw.controller;

import es.uma.proyectotaw.dao.*;
import es.uma.proyectotaw.dto.*;
import es.uma.proyectotaw.entity.*;
import es.uma.proyectotaw.service.*;
import es.uma.proyectotaw.ui.*;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.ui.Model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Controller
public class DietistaController {

    @Autowired
    private ClienteService clienteService;
    @Autowired
    private DietaService dietaService;
    @Autowired
    private MenuService menuService;
    @Autowired
    private TipoUsuarioService tipoUsuarioService;
    @Autowired
    private UsuarioService usuarioService;
    @Autowired
    private DietaComidaService dietaComidaService;
    @Autowired
    private ComidaService comidaService;


    @GetMapping("/dietistaAsignacion")
    public String asignacionDietas(Model model) {

        TipoUsuarioDTO tipoCliente = this.tipoUsuarioService.buscarRolPorId(5);
        List<ClienteDTO> clientes = this.clienteService.clientesSinDietas();

        List<DietaDTO> dietas = this.dietaService.getAll();

        model.addAttribute("clientes", clientes);
        model.addAttribute("dietas", dietas);

        return "dietista/asignacion";
    }

    @GetMapping("/dietistaAsignacion/asignar")
    public String clientesDietaAsignar(@RequestParam(required = false, name = "clienteSeleccionado") Integer clienteId,
                                       @RequestParam(required = false, name = "dietaSeleccionado") Integer dietaId,
                                       Model model) {

        if (clienteId != null && dietaId != null) {
            ClienteDTO cliente = clienteService.getClienteById(clienteId);
            DietaDTO dieta = dietaService.getDietaById(dietaId);

            cliente.setDieta(dieta);

            clienteService.guardarCliente(cliente);
        }

        return "redirect:/dietistaAsignacion";
    }

    @GetMapping("/dietas")
    public String doDietas(Model model) {
        List<DietaDTO> dietas = dietaService.getAll();
        model.addAttribute("dietas", dietas);
        model.addAttribute("FiltroDietas", new FiltroDietas());
        return "dietista/crudDietas";
    }

    @GetMapping("/dietas/eliminar")
    public String doBorrar(@RequestParam("id") Integer id ,Model model) {
        DietaDTO dieta = dietaService.getDietaById(id);
        for(DietaComidaDTO dc : dietaComidaService.getAll()) {
            if(dc.getDieta() == id) {
               dietaComidaService.deleteDietaComida(dc.getId());
            }
        }
        dietaService.deleteDieta(dieta.getId());

        List<DietaDTO> dietas = dietaService.getAll();
        model.addAttribute("dietas", dietas);
        return "redirect:/dietas";
    }

    @GetMapping("/dietistaSeguimiento")
    public String doSeguimientoClientes(Model model){
        List<ClienteDTO> clientes = clienteService.clientesConDieta();
        model.addAttribute("clientes", clientes);
        model.addAttribute("SeguimientoDietasCliente", new SeguimientoDietasCliente());
        return "dietista/seguimientoClientes";
    }

    @PostMapping("/filtrarClientesSeguimiento")
    public String buscarClientes(@ModelAttribute("SeguimientoDietasCliente") SeguimientoDietasCliente seguimientoDietasCliente, Model model) {
        if(seguimientoDietasCliente.getDieta()==""){seguimientoDietasCliente.setDieta(null);}
        if(seguimientoDietasCliente.getNombre()==""){seguimientoDietasCliente.setNombre(null);}
        List<ClienteDTO> clientes = clienteService.clientesFiltradosSeguimiento(seguimientoDietasCliente.getEdad(), seguimientoDietasCliente.getNombre(), seguimientoDietasCliente.getDieta());
        model.addAttribute("clientes", clientes);
        return "dietista/seguimientoClientes";
    }

    @PostMapping("/filtrarDietas")
    public String filtrarDietas(@ModelAttribute("FiltroDietas") FiltroDietas filtroDietas, Model model){
        if(filtroDietas.getNombre()==""){filtroDietas.setNombre(null);}
        List<DietaDTO> dietas = dietaService.dietasFiltradas(filtroDietas.getNombre(), filtroDietas.getCalorias());
        model.addAttribute("dietas", dietas);
        return "dietista/crudDietas";
    }

    @GetMapping("/dietaCrear")
    public String mostrarFormularioNuevaDieta(Model model) {
        model.addAttribute("nuevaDieta", new NuevaDieta());
        return "dietista/nuevaDieta";
    }

    @PostMapping("/crearDieta")
    public String procesarFormularioNuevaDieta(
            @ModelAttribute("nuevaDieta") NuevaDieta nuevaDieta,
            Model model,
            HttpSession sesion) {

        DietaDTO dieta = new DietaDTO();
        dieta.setNombre(nuevaDieta.getNombre());
        dieta.setDescripcion(nuevaDieta.getDescripcion());
        dieta.setCalorias(nuevaDieta.getCalorias());
        dieta.setFecha(LocalDate.now());

        UsuarioDTO dietista = (UsuarioDTO) sesion.getAttribute("usuario");
        dieta.setDietista(dietista);

        dietaService.guardarDieta(dieta);
        sesion.setAttribute("cantidadIngestas", nuevaDieta.getComidasDiarias());

        model.addAttribute("comidas", comidaService.getAll());
        model.addAttribute("dieta", dieta);

        String strTo = switch ((Integer) sesion.getAttribute("cantidadIngestas")) {
            case 3 -> {
                model.addAttribute("DietaCrearForm", new DietaCrearForm());
                yield "dietista/nuevaDietaComida3";
            }
            case 4 -> {
                model.addAttribute("DietaCrearForm", new DietaCrearForm2());
                yield "dietista/nuevaDietaComida4";
            }
            case 5 -> {
                model.addAttribute("DietaCrearForm", new DietaCrearForm3());
                yield "dietista/nuevaDietaComida5";
            }
            default -> throw new IllegalStateException("Unexpected value: " + sesion.getAttribute("cantidadIngestas"));
        };

        return strTo;
    }

    @PostMapping("dietista/generar3")
    public String doGuardar3(@ModelAttribute("DietaCrearForm") DietaCrearForm form) {
        dietaComidaService.guardarDietaComida(form.getDieta(), form.getIngesta1Dia1(), 1, 1);
        dietaComidaService.guardarDietaComida(form.getDieta(), form.getIngesta1Dia2(), 2, 1);
        dietaComidaService.guardarDietaComida(form.getDieta(), form.getIngesta1Dia3(), 3, 1);
        dietaComidaService.guardarDietaComida(form.getDieta(), form.getIngesta1Dia4(), 4, 1);
        dietaComidaService.guardarDietaComida(form.getDieta(), form.getIngesta1Dia5(), 5, 1);

        dietaComidaService.guardarDietaComida(form.getDieta(), form.getIngesta2Dia1(), 1, 2);
        dietaComidaService.guardarDietaComida(form.getDieta(), form.getIngesta2Dia2(), 2, 2);
        dietaComidaService.guardarDietaComida(form.getDieta(), form.getIngesta2Dia3(), 3, 2);
        dietaComidaService.guardarDietaComida(form.getDieta(), form.getIngesta2Dia4(), 4, 2);
        dietaComidaService.guardarDietaComida(form.getDieta(), form.getIngesta2Dia5(), 5, 2);

        dietaComidaService.guardarDietaComida(form.getDieta(), form.getIngesta3Dia1(), 1, 3);
        dietaComidaService.guardarDietaComida(form.getDieta(), form.getIngesta3Dia2(), 2, 3);
        dietaComidaService.guardarDietaComida(form.getDieta(), form.getIngesta3Dia3(), 3, 3);
        dietaComidaService.guardarDietaComida(form.getDieta(), form.getIngesta3Dia4(), 4, 3);
        dietaComidaService.guardarDietaComida(form.getDieta(), form.getIngesta3Dia5(), 5, 3);

        return "redirect:/dietas";
    }

    @PostMapping("dietista/generar4")
    public String doGuardar4(@ModelAttribute("DietaCrearForm") DietaCrearForm2 form){
        dietaComidaService.guardarDietaComida(form.getDieta(), form.getIngesta1Dia1(), 1, 1);
        dietaComidaService.guardarDietaComida(form.getDieta(), form.getIngesta1Dia2(), 2, 1);
        dietaComidaService.guardarDietaComida(form.getDieta(), form.getIngesta1Dia3(), 3, 1);
        dietaComidaService.guardarDietaComida(form.getDieta(), form.getIngesta1Dia4(), 4, 1);
        dietaComidaService.guardarDietaComida(form.getDieta(), form.getIngesta1Dia5(), 5, 1);

        dietaComidaService.guardarDietaComida(form.getDieta(), form.getIngesta2Dia1(), 1, 2);
        dietaComidaService.guardarDietaComida(form.getDieta(), form.getIngesta2Dia2(), 2, 2);
        dietaComidaService.guardarDietaComida(form.getDieta(), form.getIngesta2Dia3(), 3, 2);
        dietaComidaService.guardarDietaComida(form.getDieta(), form.getIngesta2Dia4(), 4, 2);
        dietaComidaService.guardarDietaComida(form.getDieta(), form.getIngesta2Dia5(), 5, 2);

        dietaComidaService.guardarDietaComida(form.getDieta(), form.getIngesta3Dia1(), 1, 3);
        dietaComidaService.guardarDietaComida(form.getDieta(), form.getIngesta3Dia2(), 2, 3);
        dietaComidaService.guardarDietaComida(form.getDieta(), form.getIngesta3Dia3(), 3, 3);
        dietaComidaService.guardarDietaComida(form.getDieta(), form.getIngesta3Dia4(), 4, 3);
        dietaComidaService.guardarDietaComida(form.getDieta(), form.getIngesta3Dia5(), 5, 3);

        dietaComidaService.guardarDietaComida(form.getDieta(), form.getIngesta4Dia1(), 1, 4);
        dietaComidaService.guardarDietaComida(form.getDieta(), form.getIngesta4Dia2(), 2, 4);
        dietaComidaService.guardarDietaComida(form.getDieta(), form.getIngesta4Dia3(), 3, 4);
        dietaComidaService.guardarDietaComida(form.getDieta(), form.getIngesta4Dia4(), 4, 4);
        dietaComidaService.guardarDietaComida(form.getDieta(), form.getIngesta4Dia5(), 5, 4);

        return "redirect:/dietas";
    }

    @PostMapping("dietista/generar5")
    public String doGuardar5(@ModelAttribute("DietaCrearForm") DietaCrearForm3 form){
        dietaComidaService.guardarDietaComida(form.getDieta(), form.getIngesta1Dia1(), 1, 1);
        dietaComidaService.guardarDietaComida(form.getDieta(), form.getIngesta1Dia2(), 2, 1);
        dietaComidaService.guardarDietaComida(form.getDieta(), form.getIngesta1Dia3(), 3, 1);
        dietaComidaService.guardarDietaComida(form.getDieta(), form.getIngesta1Dia4(), 4, 1);
        dietaComidaService.guardarDietaComida(form.getDieta(), form.getIngesta1Dia5(), 5, 1);

        dietaComidaService.guardarDietaComida(form.getDieta(), form.getIngesta2Dia1(), 1, 2);
        dietaComidaService.guardarDietaComida(form.getDieta(), form.getIngesta2Dia2(), 2, 2);
        dietaComidaService.guardarDietaComida(form.getDieta(), form.getIngesta2Dia3(), 3, 2);
        dietaComidaService.guardarDietaComida(form.getDieta(), form.getIngesta2Dia4(), 4, 2);
        dietaComidaService.guardarDietaComida(form.getDieta(), form.getIngesta2Dia5(), 5, 2);

        dietaComidaService.guardarDietaComida(form.getDieta(), form.getIngesta3Dia1(), 1, 3);
        dietaComidaService.guardarDietaComida(form.getDieta(), form.getIngesta3Dia2(), 2, 3);
        dietaComidaService.guardarDietaComida(form.getDieta(), form.getIngesta3Dia3(), 3, 3);
        dietaComidaService.guardarDietaComida(form.getDieta(), form.getIngesta3Dia4(), 4, 3);
        dietaComidaService.guardarDietaComida(form.getDieta(), form.getIngesta3Dia5(), 5, 3);

        dietaComidaService.guardarDietaComida(form.getDieta(), form.getIngesta4Dia1(), 1, 4);
        dietaComidaService.guardarDietaComida(form.getDieta(), form.getIngesta4Dia2(), 2, 4);
        dietaComidaService.guardarDietaComida(form.getDieta(), form.getIngesta4Dia3(), 3, 4);
        dietaComidaService.guardarDietaComida(form.getDieta(), form.getIngesta4Dia4(), 4, 4);
        dietaComidaService.guardarDietaComida(form.getDieta(), form.getIngesta4Dia5(), 5, 4);

        dietaComidaService.guardarDietaComida(form.getDieta(), form.getIngesta5Dia1(), 1, 5);
        dietaComidaService.guardarDietaComida(form.getDieta(), form.getIngesta5Dia2(), 2, 5);
        dietaComidaService.guardarDietaComida(form.getDieta(), form.getIngesta5Dia3(), 3, 5);
        dietaComidaService.guardarDietaComida(form.getDieta(), form.getIngesta5Dia4(), 4, 5);
        dietaComidaService.guardarDietaComida(form.getDieta(), form.getIngesta5Dia5(), 5, 5);

        return "redirect:/dietas";
    }


    @GetMapping("/dietas/modificar")
    public String doModificar(@RequestParam("id") Integer id ,Model model) {
        DietaDTO dieta = dietaService.getDietaById(id);
        model.addAttribute("nuevaDieta", new NuevaDieta());
        model.addAttribute("dieta", dieta);
        return "dietista/editarDieta";
    }

    @PostMapping("/editarDieta")
    public String doEditarDieta(@ModelAttribute("nuevaDieta") NuevaDieta nuevaDieta, Model model){
        DietaDTO dieta = dietaService.getDietaById(nuevaDieta.getId());
        dieta.setNombre(nuevaDieta.getNombre());
        dieta.setDescripcion(nuevaDieta.getDescripcion());
        dieta.setCalorias(nuevaDieta.getCalorias());
        dietaService.guardarDieta(dieta);

        return "redirect:/dietas";

    }







}
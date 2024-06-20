package es.uma.proyectotaw.controller;

import es.uma.proyectotaw.dao.*;
import es.uma.proyectotaw.entity.*;
import es.uma.proyectotaw.ui.*;
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
    @Autowired
    private ComidaRepository comidaRepository;


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
        model.addAttribute("comidas", this.comidaRepository.findAll());
        model.addAttribute("dieta", dieta);
        if((Integer) sesion.getAttribute("cantidadIngestas")==3){
            model.addAttribute("DietaCrearForm", new DietaCrearForm());
            strTo = "dietista/nuevaDietaComida3";
        }
        if((Integer) sesion.getAttribute("cantidadIngestas")==4){
            model.addAttribute("DietaCrearForm", new DietaCrearForm2());
            strTo = "dietista/nuevaDietaComida4";
        }
        if((Integer) sesion.getAttribute("cantidadIngestas")==5){
            model.addAttribute("DietaCrearForm", new DietaCrearForm3());
            strTo = "dietista/nuevaDietaComida5";
        }
        
        return strTo;
    }

    @PostMapping("dietista/generar3")
    public String doGuardar3(@ModelAttribute("DietaCrearForm") DietaCrearForm form){
        Dieta dieta = this.dietaRepository.findById(form.getDieta()).orElse(null);
        DietaComida dc11 = new DietaComida();
        dc11.setDieta(dieta);
        dc11.setComida(this.comidaRepository.getById(form.getIngesta1Dia1()));
        dc11.setDia(1);
        dc11.setMomentoDia(1);
        dc11.setId(1);
        dietaComidaRepository.save(dc11);
        DietaComida dc12 = new DietaComida();
        dc12.setDieta(dieta);
        dc12.setComida(this.comidaRepository.getById(form.getIngesta1Dia2()));
        dc12.setDia(2);
        dc12.setMomentoDia(1);
        dc12.setId(1);
        dietaComidaRepository.save(dc12);
        DietaComida dc13 = new DietaComida();
        dc13.setDieta(dieta);
        dc13.setComida(this.comidaRepository.getById(form.getIngesta1Dia3()));
        dc13.setDia(3);
        dc13.setMomentoDia(1);
        dc13.setId(1);
        dietaComidaRepository.save(dc13);
        DietaComida dc14 = new DietaComida();
        dc14.setDieta(dieta);
        dc14.setComida(this.comidaRepository.getById(form.getIngesta1Dia4()));
        dc14.setDia(4);
        dc14.setMomentoDia(1);
        dc14.setId(1);
        dietaComidaRepository.save(dc14);
        DietaComida dc15 = new DietaComida();
        dc15.setDieta(dieta);
        dc15.setComida(this.comidaRepository.getById(form.getIngesta1Dia5()));
        dc15.setDia(5);
        dc15.setMomentoDia(1);
        dc15.setId(1);
        dietaComidaRepository.save(dc15);
        DietaComida dc21 = new DietaComida();
        dc21.setDieta(dieta);
        dc21.setComida(this.comidaRepository.getById(form.getIngesta2Dia1()));
        dc21.setDia(1);
        dc21.setMomentoDia(2);
        dc21.setId(1);
        dietaComidaRepository.save(dc21);
        DietaComida dc22 = new DietaComida();
        dc22.setDieta(dieta);
        dc22.setComida(this.comidaRepository.getById(form.getIngesta2Dia2()));
        dc22.setDia(2);
        dc22.setMomentoDia(2);
        dc22.setId(1);
        dietaComidaRepository.save(dc22);
        DietaComida dc23 = new DietaComida();
        dc23.setDieta(dieta);
        dc23.setComida(this.comidaRepository.getById(form.getIngesta2Dia3()));
        dc23.setDia(3);
        dc23.setMomentoDia(2);
        dc23.setId(1);
        dietaComidaRepository.save(dc23);
        DietaComida dc24 = new DietaComida();
        dc24.setDieta(dieta);
        dc24.setComida(this.comidaRepository.getById(form.getIngesta2Dia4()));
        dc24.setDia(4);
        dc24.setMomentoDia(2);
        dc24.setId(1);
        dietaComidaRepository.save(dc24);
        DietaComida dc25 = new DietaComida();
        dc25.setDieta(dieta);
        dc25.setComida(this.comidaRepository.getById(form.getIngesta2Dia5()));
        dc25.setDia(5);
        dc25.setMomentoDia(2);
        dc25.setId(1);
        dietaComidaRepository.save(dc25);
        DietaComida dc31 = new DietaComida();
        dc31.setDieta(dieta);
        dc31.setComida(this.comidaRepository.getById(form.getIngesta3Dia1()));
        dc31.setDia(1);
        dc31.setMomentoDia(3);
        dc31.setId(1);
        dietaComidaRepository.save(dc31);
        DietaComida dc32 = new DietaComida();
        dc32.setDieta(dieta);
        dc32.setComida(this.comidaRepository.getById(form.getIngesta3Dia2()));
        dc32.setDia(2);
        dc32.setMomentoDia(3);
        dc32.setId(1);
        dietaComidaRepository.save(dc32);
        DietaComida dc33 = new DietaComida();
        dc33.setDieta(dieta);
        dc33.setComida(this.comidaRepository.getById(form.getIngesta3Dia3()));
        dc33.setDia(3);
        dc33.setMomentoDia(3);
        dc33.setId(1);
        dietaComidaRepository.save(dc33);
        DietaComida dc34 = new DietaComida();
        dc34.setDieta(dieta);
        dc34.setComida(this.comidaRepository.getById(form.getIngesta3Dia4()));
        dc34.setDia(4);
        dc34.setMomentoDia(3);
        dc34.setId(1);
        dietaComidaRepository.save(dc34);
        DietaComida dc35 = new DietaComida();
        dc35.setDieta(dieta);
        dc35.setComida(this.comidaRepository.getById(form.getIngesta3Dia5()));
        dc35.setDia(5);
        dc35.setMomentoDia(3);
        dc35.setId(1);
        dietaComidaRepository.save(dc35);

        return "redirect:/dietas";
    }

    @PostMapping("dietista/generar4")
    public String doGuardar4(@ModelAttribute("DietaCrearForm") DietaCrearForm2 form){
        Dieta dieta = this.dietaRepository.findById(form.getDieta()).orElse(null);
        DietaComida dc11 = new DietaComida();
        dc11.setDieta(dieta);
        dc11.setComida(this.comidaRepository.getById(form.getIngesta1Dia1()));
        dc11.setDia(1);
        dc11.setMomentoDia(1);
        dc11.setId(1);
        dietaComidaRepository.save(dc11);
        DietaComida dc12 = new DietaComida();
        dc12.setDieta(dieta);
        dc12.setComida(this.comidaRepository.getById(form.getIngesta1Dia2()));
        dc12.setDia(2);
        dc12.setMomentoDia(1);
        dc12.setId(1);
        dietaComidaRepository.save(dc12);
        DietaComida dc13 = new DietaComida();
        dc13.setDieta(dieta);
        dc13.setComida(this.comidaRepository.getById(form.getIngesta1Dia3()));
        dc13.setDia(3);
        dc13.setMomentoDia(1);
        dc13.setId(1);
        dietaComidaRepository.save(dc13);
        DietaComida dc14 = new DietaComida();
        dc14.setDieta(dieta);
        dc14.setComida(this.comidaRepository.getById(form.getIngesta1Dia4()));
        dc14.setDia(4);
        dc14.setMomentoDia(1);
        dc14.setId(1);
        dietaComidaRepository.save(dc14);
        DietaComida dc15 = new DietaComida();
        dc15.setDieta(dieta);
        dc15.setComida(this.comidaRepository.getById(form.getIngesta1Dia5()));
        dc15.setDia(5);
        dc15.setMomentoDia(1);
        dc15.setId(1);
        dietaComidaRepository.save(dc15);
        DietaComida dc21 = new DietaComida();
        dc21.setDieta(dieta);
        dc21.setComida(this.comidaRepository.getById(form.getIngesta2Dia1()));
        dc21.setDia(1);
        dc21.setMomentoDia(2);
        dc21.setId(1);
        dietaComidaRepository.save(dc21);
        DietaComida dc22 = new DietaComida();
        dc22.setDieta(dieta);
        dc22.setComida(this.comidaRepository.getById(form.getIngesta2Dia2()));
        dc22.setDia(2);
        dc22.setMomentoDia(2);
        dc22.setId(1);
        dietaComidaRepository.save(dc22);
        DietaComida dc23 = new DietaComida();
        dc23.setDieta(dieta);
        dc23.setComida(this.comidaRepository.getById(form.getIngesta2Dia3()));
        dc23.setDia(3);
        dc23.setMomentoDia(2);
        dc23.setId(1);
        dietaComidaRepository.save(dc23);
        DietaComida dc24 = new DietaComida();
        dc24.setDieta(dieta);
        dc24.setComida(this.comidaRepository.getById(form.getIngesta2Dia4()));
        dc24.setDia(4);
        dc24.setMomentoDia(2);
        dc24.setId(1);
        dietaComidaRepository.save(dc24);
        DietaComida dc25 = new DietaComida();
        dc25.setDieta(dieta);
        dc25.setComida(this.comidaRepository.getById(form.getIngesta2Dia5()));
        dc25.setDia(5);
        dc25.setMomentoDia(2);
        dc25.setId(1);
        dietaComidaRepository.save(dc25);
        DietaComida dc31 = new DietaComida();
        dc31.setDieta(dieta);
        dc31.setComida(this.comidaRepository.getById(form.getIngesta3Dia1()));
        dc31.setDia(1);
        dc31.setMomentoDia(3);
        dc31.setId(1);
        dietaComidaRepository.save(dc31);
        DietaComida dc32 = new DietaComida();
        dc32.setDieta(dieta);
        dc32.setComida(this.comidaRepository.getById(form.getIngesta3Dia2()));
        dc32.setDia(2);
        dc32.setMomentoDia(3);
        dc32.setId(1);
        dietaComidaRepository.save(dc32);
        DietaComida dc33 = new DietaComida();
        dc33.setDieta(dieta);
        dc33.setComida(this.comidaRepository.getById(form.getIngesta3Dia3()));
        dc33.setDia(3);
        dc33.setMomentoDia(3);
        dc33.setId(1);
        dietaComidaRepository.save(dc33);
        DietaComida dc34 = new DietaComida();
        dc34.setDieta(dieta);
        dc34.setComida(this.comidaRepository.getById(form.getIngesta3Dia4()));
        dc34.setDia(4);
        dc34.setMomentoDia(3);
        dc34.setId(1);
        dietaComidaRepository.save(dc34);
        DietaComida dc35 = new DietaComida();
        dc35.setDieta(dieta);
        dc35.setComida(this.comidaRepository.getById(form.getIngesta3Dia5()));
        dc35.setDia(5);
        dc35.setMomentoDia(3);
        dc35.setId(1);
        dietaComidaRepository.save(dc35);
        DietaComida dc41 = new DietaComida();
        dc41.setDieta(dieta);
        dc41.setComida(this.comidaRepository.getById(form.getIngesta4Dia1()));
        dc41.setDia(1);
        dc41.setMomentoDia(4);
        dc41.setId(1);
        dietaComidaRepository.save(dc41);
        DietaComida dc42 = new DietaComida();
        dc42.setDieta(dieta);
        dc42.setComida(this.comidaRepository.getById(form.getIngesta4Dia2()));
        dc42.setDia(2);
        dc42.setMomentoDia(4);
        dc42.setId(1);
        dietaComidaRepository.save(dc42);
        DietaComida dc43 = new DietaComida();
        dc43.setDieta(dieta);
        dc43.setComida(this.comidaRepository.getById(form.getIngesta4Dia3()));
        dc43.setDia(3);
        dc43.setMomentoDia(4);
        dc43.setId(1);
        dietaComidaRepository.save(dc43);
        DietaComida dc44 = new DietaComida();
        dc44.setDieta(dieta);
        dc44.setComida(this.comidaRepository.getById(form.getIngesta4Dia4()));
        dc44.setDia(4);
        dc44.setMomentoDia(4);
        dc44.setId(1);
        dietaComidaRepository.save(dc44);
        DietaComida dc45 = new DietaComida();
        dc45.setDieta(dieta);
        dc45.setComida(this.comidaRepository.getById(form.getIngesta4Dia5()));
        dc45.setDia(5);
        dc45.setMomentoDia(4);
        dc45.setId(1);
        dietaComidaRepository.save(dc45);
        return "redirect:/dietas";
    }

    @PostMapping("dietista/generar5")
    public String doGuardar5(@ModelAttribute("DietaCrearForm") DietaCrearForm3 form){
        Dieta dieta = this.dietaRepository.findById(form.getDieta()).orElse(null);
        DietaComida dc11 = new DietaComida();
        dc11.setDieta(dieta);
        dc11.setComida(this.comidaRepository.getById(form.getIngesta1Dia1()));
        dc11.setDia(1);
        dc11.setMomentoDia(1);
        dc11.setId(1);
        dietaComidaRepository.save(dc11);
        DietaComida dc12 = new DietaComida();
        dc12.setDieta(dieta);
        dc12.setComida(this.comidaRepository.getById(form.getIngesta1Dia2()));
        dc12.setDia(2);
        dc12.setMomentoDia(1);
        dc12.setId(1);
        dietaComidaRepository.save(dc12);
        DietaComida dc13 = new DietaComida();
        dc13.setDieta(dieta);
        dc13.setComida(this.comidaRepository.getById(form.getIngesta1Dia3()));
        dc13.setDia(3);
        dc13.setMomentoDia(1);
        dc13.setId(1);
        dietaComidaRepository.save(dc13);
        DietaComida dc14 = new DietaComida();
        dc14.setDieta(dieta);
        dc14.setComida(this.comidaRepository.getById(form.getIngesta1Dia4()));
        dc14.setDia(4);
        dc14.setMomentoDia(1);
        dc14.setId(1);
        dietaComidaRepository.save(dc14);
        DietaComida dc15 = new DietaComida();
        dc15.setDieta(dieta);
        dc15.setComida(this.comidaRepository.getById(form.getIngesta1Dia5()));
        dc15.setDia(5);
        dc15.setMomentoDia(1);
        dc15.setId(1);
        dietaComidaRepository.save(dc15);
        DietaComida dc21 = new DietaComida();
        dc21.setDieta(dieta);
        dc21.setComida(this.comidaRepository.getById(form.getIngesta2Dia1()));
        dc21.setDia(1);
        dc21.setMomentoDia(2);
        dc21.setId(1);
        dietaComidaRepository.save(dc21);
        DietaComida dc22 = new DietaComida();
        dc22.setDieta(dieta);
        dc22.setComida(this.comidaRepository.getById(form.getIngesta2Dia2()));
        dc22.setDia(2);
        dc22.setMomentoDia(2);
        dc22.setId(1);
        dietaComidaRepository.save(dc22);
        DietaComida dc23 = new DietaComida();
        dc23.setDieta(dieta);
        dc23.setComida(this.comidaRepository.getById(form.getIngesta2Dia3()));
        dc23.setDia(3);
        dc23.setMomentoDia(2);
        dc23.setId(1);
        dietaComidaRepository.save(dc23);
        DietaComida dc24 = new DietaComida();
        dc24.setDieta(dieta);
        dc24.setComida(this.comidaRepository.getById(form.getIngesta2Dia4()));
        dc24.setDia(4);
        dc24.setMomentoDia(2);
        dc24.setId(1);
        dietaComidaRepository.save(dc24);
        DietaComida dc25 = new DietaComida();
        dc25.setDieta(dieta);
        dc25.setComida(this.comidaRepository.getById(form.getIngesta2Dia5()));
        dc25.setDia(5);
        dc25.setMomentoDia(2);
        dc25.setId(1);
        dietaComidaRepository.save(dc25);
        DietaComida dc31 = new DietaComida();
        dc31.setDieta(dieta);
        dc31.setComida(this.comidaRepository.getById(form.getIngesta3Dia1()));
        dc31.setDia(1);
        dc31.setMomentoDia(3);
        dc31.setId(1);
        dietaComidaRepository.save(dc31);
        DietaComida dc32 = new DietaComida();
        dc32.setDieta(dieta);
        dc32.setComida(this.comidaRepository.getById(form.getIngesta3Dia2()));
        dc32.setDia(2);
        dc32.setMomentoDia(3);
        dc32.setId(1);
        dietaComidaRepository.save(dc32);
        DietaComida dc33 = new DietaComida();
        dc33.setDieta(dieta);
        dc33.setComida(this.comidaRepository.getById(form.getIngesta3Dia3()));
        dc33.setDia(3);
        dc33.setMomentoDia(3);
        dc33.setId(1);
        dietaComidaRepository.save(dc33);
        DietaComida dc34 = new DietaComida();
        dc34.setDieta(dieta);
        dc34.setComida(this.comidaRepository.getById(form.getIngesta3Dia4()));
        dc34.setDia(4);
        dc34.setMomentoDia(3);
        dc34.setId(1);
        dietaComidaRepository.save(dc34);
        DietaComida dc35 = new DietaComida();
        dc35.setDieta(dieta);
        dc35.setComida(this.comidaRepository.getById(form.getIngesta3Dia5()));
        dc35.setDia(5);
        dc35.setMomentoDia(3);
        dc35.setId(1);
        dietaComidaRepository.save(dc35);
        DietaComida dc41 = new DietaComida();
        dc41.setDieta(dieta);
        dc41.setComida(this.comidaRepository.getById(form.getIngesta4Dia1()));
        dc41.setDia(1);
        dc41.setMomentoDia(4);
        dc41.setId(1);
        dietaComidaRepository.save(dc41);
        DietaComida dc42 = new DietaComida();
        dc42.setDieta(dieta);
        dc42.setComida(this.comidaRepository.getById(form.getIngesta4Dia2()));
        dc42.setDia(2);
        dc42.setMomentoDia(4);
        dc42.setId(1);
        dietaComidaRepository.save(dc42);
        DietaComida dc43 = new DietaComida();
        dc43.setDieta(dieta);
        dc43.setComida(this.comidaRepository.getById(form.getIngesta4Dia3()));
        dc43.setDia(3);
        dc43.setMomentoDia(4);
        dc43.setId(1);
        dietaComidaRepository.save(dc43);
        DietaComida dc44 = new DietaComida();
        dc44.setDieta(dieta);
        dc44.setComida(this.comidaRepository.getById(form.getIngesta4Dia4()));
        dc44.setDia(4);
        dc44.setMomentoDia(4);
        dc44.setId(1);
        dietaComidaRepository.save(dc44);
        DietaComida dc45 = new DietaComida();
        dc45.setDieta(dieta);
        dc45.setComida(this.comidaRepository.getById(form.getIngesta4Dia5()));
        dc45.setDia(5);
        dc45.setMomentoDia(4);
        dc45.setId(1);
        dietaComidaRepository.save(dc45);
        DietaComida dc51 = new DietaComida();
        dc51.setDieta(dieta);
        dc51.setComida(this.comidaRepository.getById(form.getIngesta5Dia1()));
        dc51.setDia(1);
        dc51.setMomentoDia(5);
        dc51.setId(1);
        dietaComidaRepository.save(dc51);
        DietaComida dc52 = new DietaComida();
        dc52.setDieta(dieta);
        dc52.setComida(this.comidaRepository.getById(form.getIngesta5Dia2()));
        dc52.setDia(2);
        dc52.setMomentoDia(5);
        dc52.setId(1);
        dietaComidaRepository.save(dc52);
        DietaComida dc53 = new DietaComida();
        dc53.setDieta(dieta);
        dc53.setComida(this.comidaRepository.getById(form.getIngesta5Dia3()));
        dc53.setDia(3);
        dc53.setMomentoDia(5);
        dc53.setId(1);
        dietaComidaRepository.save(dc53);
        DietaComida dc54 = new DietaComida();
        dc54.setDieta(dieta);
        dc54.setComida(this.comidaRepository.getById(form.getIngesta5Dia4()));
        dc54.setDia(4);
        dc54.setMomentoDia(5);
        dc54.setId(1);
        dietaComidaRepository.save(dc54);
        DietaComida dc55 = new DietaComida();
        dc55.setDieta(dieta);
        dc55.setComida(this.comidaRepository.getById(form.getIngesta5Dia5()));
        dc55.setDia(5);
        dc55.setMomentoDia(5);
        dc55.setId(1);
        dietaComidaRepository.save(dc55);
        return "redirect:/dietas";
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

        return "redirect:/dietas";
        
    }






}
package es.uma.proyectotaw.controller;

import es.uma.proyectotaw.dto.*;
import es.uma.proyectotaw.service.*;
import es.uma.proyectotaw.ui.*;
import org.springframework.ui.Model;
import es.uma.proyectotaw.entity.*;
import es.uma.proyectotaw.dao.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

// autor: Alba de la Torre

@Controller
public class ClienteController {
    @Autowired
    private EjercicioService ejercicioService;
    @Autowired
    private RutinaService rutinaService;
    @Autowired
    private ClienteService clienteService;
    @Autowired
    private EntrenamientoService entrenamientoService;
    @Autowired
    private GrupoMuscularService grupoMuscularService;
    @Autowired
    private DesempenoService desempenoService;
    @Autowired
    private EjercicioEntrenamientoService ejercicioEntrenamientoService;
    @Autowired
    private ComidaService comidaService;
    @Autowired
    private DietaService dietaService;
    @Autowired
    private EntrenamientoRutinaService entrenamientoRutinaService;
    @Autowired
    private MenuService menuService;
    @Autowired
    private ComidaMenuService comidaMenuService;
    @Autowired
    private DietaComidaService dietaComidaService;

    @GetMapping("/rutina")
    public String rutina(@RequestParam("id") Integer id, Model model) {
        ClienteDTO client = clienteService.getClienteByUserId(id);
        RutinaDTO rutina = rutinaService.getActiveRutinasByClienteId(client.getId());
        List<EntrenamientoDTO> entrenamientos = entrenamientoService.findByRutinaId(rutina.getId());
        List<GrupoMuscularDTO> grupomuscular = grupoMuscularService.findAll();

        // Para cada entrenamiento, guardamos en un mapa su id y su cumplimiento,
        // que calculamos con el método privado calcularCumplimiento a partir del cumplimiento
        // medio de cada entrenamiento
        HashMap<Integer, Float> cumplimiento= new HashMap<>();
        for(EntrenamientoDTO entrenamiento: entrenamientos){
            float c = calcularCumplimiento(entrenamiento.getId());
            cumplimiento.put(entrenamiento.getId(), c);
        }

        // Para cada entrenamiento, accedemos a su tabla entrenamiento_rutina correspondiente y accedemos al
        // atributo día. Lo guardamos también en un mapa
        HashMap<Integer, Integer> dia = new HashMap<>();
        for(EntrenamientoDTO entrenamiento: entrenamientos){
            int diaSemana = entrenamientoRutinaService.getdiaSemanaFromRutinaAndEntrenamientoId(rutina.getId(), entrenamiento.getId());
            dia.put(entrenamiento.getId(),diaSemana);
        }
        model.addAttribute("rutina", rutina);
        model.addAttribute("cliente", client);
        model.addAttribute("entrenamientos", entrenamientos);
        model.addAttribute("grupomuscular", grupomuscular);
        model.addAttribute("dia", dia);
        model.addAttribute("cumplimiento", cumplimiento);
        model.addAttribute("rutinaFiltro", new RutinaFiltro());
        model.addAttribute("desempenyoFiltro", new DesempenyoFiltro());
        return "cliente/rutina";
    }

    @GetMapping("/verRutina")
    public String verRutinaNoActiva(@RequestParam("id") Integer id, Model model) {
        List<EntrenamientoDTO> entrenamientos = entrenamientoService.findByRutinaId(id);
        HashMap<Integer, Float> cumplimiento= new HashMap<>();

        // Similar a la función anterior, en este caso calculamos el cumplimiento de
        // los ejercicios dentro de un entrenamiento concreto
        for(EntrenamientoDTO entrenamiento: entrenamientos){
            float c = calcularCumplimiento(entrenamiento.getId());
            cumplimiento.put(entrenamiento.getId(), c);
        }
        HashMap<Integer, Integer> dia = new HashMap<>();
        for(EntrenamientoDTO entrenamiento: entrenamientos){
            int diaSemana = entrenamientoRutinaService.getdiaSemanaFromRutinaAndEntrenamientoId(id, entrenamiento.getId());
            dia.put(entrenamiento.getId(),diaSemana);
        }
        model.addAttribute("entrenamientos", entrenamientos);
        model.addAttribute("dia", dia);
        model.addAttribute("cumplimiento", cumplimiento);

        return "cliente/rutinaNoActiva";
    }



    @GetMapping("/menu")
    public String menu(@RequestParam("id") Integer id, Model model){
        ClienteDTO client = clienteService.getClienteByUserId(id);
        DietaDTO dieta = client.getDieta();
        Set<DietaComidaDTO> dietaComidas = dietaComidaService.getComidaDietaByDietaId(dieta.getId());

        // Dividimos las comidas en listas según el momento del día, siendo:
        // M1: desayuno
        // M2: almuerzo
        // M3: cena
        List<ComidaDTO> comidasM1 = new ArrayList<>();
        List<ComidaDTO> comidasM2 = new ArrayList<>();
        List<ComidaDTO> comidasM3 = new ArrayList<>();
        for(DietaComidaDTO dietaComida: dietaComidas){
            ComidaDTO comida = comidaService.getReferenceById(dietaComida.getComida());
            if(dietaComida.getMomentoDia()==1){
                comidasM1.add(comida);
            } else if(dietaComida.getMomentoDia()==2){
                comidasM2.add(comida);
            } else if(dietaComida.getMomentoDia()==3){
                comidasM3.add(comida);
            }
        }
        model.addAttribute("dieta", dieta);
        model.addAttribute("comidaFiltro", new ComidaFiltro());
        model.addAttribute("client", client);
        model.addAttribute("desempenyoFiltro", new DesempenyoFiltro());
        model.addAttribute("comidasM1", comidasM1);
        model.addAttribute("comidasM2", comidasM2);
        model.addAttribute("comidasM3", comidasM3);
        return "cliente/menu";
    }

    @GetMapping("/dia")
    public String getDia(@RequestParam("id") Integer id, @RequestParam("clientId") Integer clientId, Model model){

        List<EjercicioDTO> ejercicios = ejercicioService.findEjerciciosByEntrenamientoId(id);
        HashMap<Integer, List<Float>> map = new HashMap<>();

        // Accedemos a las especificaciones de un ejercicio en un entrenamiento (peso, repeciciones, etc.)
        // y las guardamos en un mapa
        for(EjercicioDTO ejercicio : ejercicios) {
            List<Float> specs = ejercicioService.getEspecificacionesEjercicio(ejercicio.getId(), id);
            int realizado = 1;
            int eeID= ejercicioService.findId(ejercicio.getId(), id);
            EjercicioEntrenamientoDTO ee = ejercicioEntrenamientoService.getReferenceById(eeID);
            if(ee.getDesempeno()==null){
                realizado = 0;
            }
            List<Float> lista = new ArrayList<>();
            lista.add(specs.get(0));
            lista.add(specs.get(1));
            lista.add(specs.get(2));
            lista.add((float) realizado);
            map.put(ejercicio.getId(), lista);
        }
        model.addAttribute("ejercicios", ejercicios);
        model.addAttribute("map", map);
        model.addAttribute("clientId", clientId);
        model.addAttribute("entrenamientoId", id);
        return "/cliente/diaRutina";
    }

    @GetMapping("/desempeno")
    public String getDesempeno(@RequestParam("id") Integer id,
                               @RequestParam("clientId") Integer clientId,
                               @RequestParam("entrenamientoId") Integer entrenamientoId,
                               Model model){
        EjercicioDTO ejercicio = ejercicioService.getReferenceById(id);
        ClienteDTO cliente = clienteService.getReferenceById(clientId);
        DesempenyoYEjercicio desempeno = new DesempenyoYEjercicio();
        model.addAttribute("ejercicio", ejercicio);
        model.addAttribute("desempeno", desempeno);
        model.addAttribute("cliente", cliente);
        model.addAttribute("entrenamientoId", entrenamientoId);
        return "/cliente/desempeno";
    }

    @GetMapping("/verDesempeno")
    public String verDesempeno(@RequestParam("id") Integer id,
                               @RequestParam("clientId") Integer clientId,
                               @RequestParam("entrenamientoId") Integer entrenamientoId,
                               Model model){
        EjercicioDTO ejercicio = ejercicioService.getReferenceById(id);
        ClienteDTO cliente = clienteService.getReferenceById(clientId);
        DesempenoDTO d = desempenoService.getDesempenoByEntrenamientoAndEjId(id, entrenamientoId);

        DesempenyoYEjercicio desempeno = new DesempenyoYEjercicio();
        desempeno.setComentarios(d.getComentarios());
        desempeno.setValoracion(d.getValoracion());
        desempeno.setPesoRealizado(d.getPesoRealizado());

        model.addAttribute("ejercicio", ejercicio);
        model.addAttribute("desempeno", desempeno);
        model.addAttribute("cliente", cliente);
        model.addAttribute("entrenamientoId", entrenamientoId);
        return "/cliente/desempeno";
    }

    @PostMapping("/guardarDesempeno")
    public String guardarDesempeno(@ModelAttribute("desempeno") DesempenyoYEjercicio desempeno, Model model){
        desempenoService.guardarDesempeno(desempeno.getCliente(), desempeno.getPesoRealizado(), desempeno.getComentarios(), desempeno.getValoracion(), desempeno.getEjercicio(), desempeno.getEntrenamiento());
        ClienteDTO c = clienteService.getReferenceById(desempeno.getCliente());
        return "redirect:/rutina?id="+ c.getUsuario().getId();
    }

    @GetMapping("/comida")
    public String comida(@RequestParam("id") Integer id, @RequestParam("clientId") Integer clientId, Model model){
        ComidaDTO comida = comidaService.getReferenceById(id);
        ClienteDTO client = clienteService.getReferenceById(clientId);
        Set<ComidaMenuDTO> menus =  comida.getMenus();
        HashMap <Integer, Integer> desempenyo = new HashMap();
        HashMap <Integer, List<String>> desc = new HashMap();

        // Guardamos el desempeño del menu y sus especificaciones en un mapa
        // para así poder acceder a ello a través del id del menú
        for(ComidaMenuDTO menu : menus){
            int realizado = 1;
            if(menu.getDesempeno()==null){
                realizado = 0;
            }
            MenuDTO m = menuService.getReferenceById(menu.getMenu());
            desempenyo.put(m.getId(), realizado);
            List<String> list = new ArrayList<>();
            list.add(m.getNombre());
            list.add(m.getDescripcion());
            desc.put(menu.getId(), list);
        }

        model.addAttribute("desempenyo", desempenyo);
        model.addAttribute("comida", comida);
        model.addAttribute("menus", menus);
        model.addAttribute("client", client);
        model.addAttribute("desc", desc);
        return "/cliente/comida";
    }

    @GetMapping("/dietaDesempeno")
    public String dietaDesempenyo(@RequestParam("id") Integer id,
                                  @RequestParam("comidaId") Integer comidaId,
                                  @RequestParam("clientId") Integer clientId,
                                  Model model){
        ComidaDesempenyo desempeno = new ComidaDesempenyo();
        MenuDTO menu = menuService.getReferenceById(id);
        ClienteDTO cliente = clienteService.getReferenceById(clientId);

        model.addAttribute("menu", menu);
        model.addAttribute("desempeno", desempeno);
        model.addAttribute("cliente", cliente);
        model.addAttribute("comidaId", comidaId);

        return "/cliente/dietaDesempenyo";
    }
    @PostMapping("/guardarDesempenoDieta")
    public String guardarDesempenoDieta(@ModelAttribute("desempeno") ComidaDesempenyo desempeno, Model model){
        desempenoService.guardarDesempenoDieta(desempeno.getCliente(), 0F, desempeno.getComentarios(), desempeno.getValoracion(), desempeno.getMenu(), desempeno.getComida());
        ClienteDTO c = clienteService.getReferenceById(desempeno.getCliente());
        return "redirect:/menu?id="+ c.getUsuario().getId();
    }

    @GetMapping("/verDietaDesempeno")
    public String verDietaDesempeno(@RequestParam("id") Integer id,
                                    @RequestParam("comidaId") Integer comidaId,
                                    @RequestParam("clientId") Integer clientId,
                                    Model model){
        ComidaDesempenyo desempeno = new ComidaDesempenyo();
        MenuDTO menu = menuService.getReferenceById(id);
        ClienteDTO cliente = clienteService.getReferenceById(clientId);
        DesempenoDTO d = desempenoService.getDesempenoByMenuAndComidaId(id, comidaId);

        desempeno.setComentarios(d.getComentarios());
        desempeno.setValoracion(d.getValoracion());

        model.addAttribute("menu", menu);
        model.addAttribute("desempeno", desempeno);
        model.addAttribute("cliente", cliente);
        model.addAttribute("comidaId", comidaId);
        return "/cliente/dietaDesempenyo";
    }

    @PostMapping("/filtrarRutina")
    public String rutinaFiltrada(@ModelAttribute("rutinaFiltro") RutinaFiltro rutinaFiltro,
                                 Model model){
        ClienteDTO client = clienteService.getClienteByUserId(rutinaFiltro.getClienteId());
        List<RutinaDTO> rutina = rutinaService.getRutinasByNameAndClientId(rutinaFiltro.getClienteId(), rutinaFiltro.getNombre());

        model.addAttribute("rutina", rutina);
        model.addAttribute("cliente", client);
        model.addAttribute("rutinaFiltro", new RutinaFiltro());
        model.addAttribute("desempenyoFiltro", new DesempenyoFiltro());
        return "/cliente/rutinaFiltrada";
    }

    @PostMapping("/filtrarRutinaDesempenyo")
    public String filtrarRutinaDesempenyo(@ModelAttribute("desempenyoFiltro") DesempenyoFiltro filtro, Model model){
        List<RutinaDTO> rutina = rutinaService.getAllRutinasByClienteId(filtro.getIdCliente());
        List<RutinaDTO> rutinasFiltradas = new ArrayList<>();
        List<RutinaDTO> rutinaNoFiltradas = new ArrayList<>(rutina);
        HashMap<Integer, Float> cumplimiento = new HashMap<>();

        // Definimos los límites del filtro por los que buscar
        int upperBound = 0;
        int lowerBound = 0;
        if(filtro.getDesempenyo().equals("Alto")){
            upperBound = 100;
            lowerBound = 70;
        } else if(filtro.getDesempenyo().equals("Medio")){
            upperBound = 69;
            lowerBound = 30;
        } else if(filtro.getDesempenyo().equals("Bajo")){
            upperBound = 29;
            lowerBound = 0;
        }
        float desempenyoTotal = 0;

        // Lista temporal para almacenar las rutinas a eliminar
        List<RutinaDTO> rutinasAEliminar = new ArrayList<>();

        // Calculamos el desempeño y si cumple con los límites puestos, lo añadimos a rutinasFiltradas
        // y lo marcamos para quitarlo de la lista del resto de rutinas
        for(RutinaDTO r : rutina){
            List<EntrenamientoDTO> entrenamientos = entrenamientoService.findByRutinaId(r.getId());
            desempenyoTotal = 0;
            for(EntrenamientoDTO e : entrenamientos){
                desempenyoTotal += calcularCumplimiento(e.getId());
            }
            desempenyoTotal = desempenyoTotal/entrenamientos.size();
            cumplimiento.put(r.getId(), desempenyoTotal);
            if(desempenyoTotal <= upperBound && desempenyoTotal >= lowerBound){
                rutinasFiltradas.add(r);
                rutinasAEliminar.add(r);
            }
        }

        // Eliminar las rutinas marcadas de la lista del resto de rutinas
        rutinaNoFiltradas.removeAll(rutinasAEliminar);

        model.addAttribute("rutinasFiltradas", rutinasFiltradas);
        model.addAttribute("rutinas", rutinaNoFiltradas);
        model.addAttribute("cumplimiento", cumplimiento);
        return "/cliente/rutinaDesempenyoFiltrada";
    }


    @PostMapping("/filtrarDietaDesempenyo")
    public String filtrarDietaDesempenyo(@ModelAttribute("desempenyoFiltro") DesempenyoFiltro filtro, Model model){
        List<DietaDTO> dieta = dietaService.getDietaByClientId(filtro.getIdCliente());
        List<DietaDTO> dietasFiltradas = new ArrayList<>();
        List<DietaDTO> dietaNoFiltradas = new ArrayList<>(dieta);
        HashMap<Integer, Float> cumplimiento = new HashMap<>();

        int upperBound = 0;
        int lowerBound = 0;
        if(filtro.getDesempenyo().equals("Alto")){
            upperBound = 100;
            lowerBound = 70;
        } else if(filtro.getDesempenyo().equals("Medio")){
            upperBound = 69;
            lowerBound = 30;
        } else if(filtro.getDesempenyo().equals("Bajo")){
            upperBound = 29;
            lowerBound = 0;
        }
        float desempenyoTotal = 0;

        // Lista temporal para almacenar las dietas a eliminar
        List<DietaDTO> dietasAEliminar = new ArrayList<>();

        for(DietaDTO d : dieta){
            List<ComidaDTO> comidas = comidaService.findByDietaId(d.getId());
            desempenyoTotal = 0;
            for(ComidaDTO c : comidas){
                desempenyoTotal += calcularCumplimientoDieta(c.getId());
            }
            desempenyoTotal = desempenyoTotal/comidas.size();
            cumplimiento.put(d.getId(), desempenyoTotal);
            if(desempenyoTotal <= upperBound && desempenyoTotal >= lowerBound){
                dietasFiltradas.add(d);
                dietasAEliminar.add(d);
            }
        }

        // Eliminar las dietas marcadas de la lista del resto de dietas
        dietaNoFiltradas.removeAll(dietasAEliminar);

        model.addAttribute("dietasFiltradas", dietasFiltradas);
        model.addAttribute("dietas", dietaNoFiltradas);
        model.addAttribute("cumplimiento", cumplimiento);
        return "/cliente/dietaDesempenyoFiltrada";
    }


    @GetMapping("/eliminarDesempeno")
    public String eliminarDesempeno(@RequestParam("id") Integer id,
                                    @RequestParam("clientId") Integer clientId,
                                    @RequestParam("entrenamientoId") Integer entrenamientoId,
                                    Model model){
        ClienteDTO c = clienteService.getReferenceById(clientId);
        desempenoService.delete(clientId, id, entrenamientoId);
        return "redirect:/rutina?id="+ c.getUsuario().getId();
    }

    @GetMapping("/eliminarDesempenoMenu")
    public String eliminarDesempenoMenu(@RequestParam("id") Integer id,
                                    @RequestParam("clientId") Integer clientId,
                                    @RequestParam("comidaId") Integer comidaId,
                                    Model model){
        ClienteDTO c = clienteService.getReferenceById(clientId);
        desempenoService.deleteMenu(clientId, id, comidaId);
        return "redirect:/menu?id="+ c.getUsuario().getId();
    }

///////////////////////// METODOS PRIVADOS /////////////////////////////////

    private float calcularCumplimiento(Integer id) {
        float res = 0;
        Entrenamiento e = entrenamientoService.getEntityById(id);
        if(e.getEjercicios().isEmpty()){
            return 0F;
        } else {
            for (EjercicioEntrenamiento ee : e.getEjercicios()) {
                if (ee.getDesempeno() != null) {
                    res++;
                }
            }
            return (res / e.getEjercicios().size()) * 100;
        }
    }

    private float calcularCumplimientoDieta(Integer id) {
        float res = 0;
        Comida c = comidaService.getEntityById(id);
        if(c.getMenus().isEmpty()){
            return 0F;
        } else {
            for (ComidaMenu cm : c.getMenus()) {
                if (cm.getDesempeno() != null) {
                    res++;
                }
            }
            return (res/c.getMenus().size())*100;
        }
    }

///////////////////////////////////////////////////////////////////////////
}


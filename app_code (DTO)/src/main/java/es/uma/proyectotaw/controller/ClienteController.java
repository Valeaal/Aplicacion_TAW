package es.uma.proyectotaw.controller;

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

    @GetMapping("/rutina")
    public String rutina(@RequestParam("id") Integer id, Model model) {
        Cliente client = clienteService.getClienteByUserId2(id);
        Rutina rutina = rutinaService.getActiveRutinasByClienteId(client.getId());
        List<Entrenamiento> entrenamientos = entrenamientoService.findByRutinaId(rutina.getId());
        List<GrupoMuscular> grupomuscular = grupoMuscularService.findAll2();

        HashMap<Integer, Float> cumplimiento= new HashMap<>();
        for(Entrenamiento entrenamiento: entrenamientos){
            float c = calcularCumplimiento(entrenamiento.getId());
            cumplimiento.put(entrenamiento.getId(), c);
        }

        HashMap<Integer, Integer> dia = new HashMap<>();
        for(Entrenamiento entrenamiento: entrenamientos){
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
        List<Entrenamiento> entrenamientos = entrenamientoService.findByRutinaId(id);
        HashMap<Integer, Float> cumplimiento= new HashMap<>();
        for(Entrenamiento entrenamiento: entrenamientos){
            float c = calcularCumplimiento(entrenamiento.getId());
            cumplimiento.put(entrenamiento.getId(), c);
        }
        HashMap<Integer, Integer> dia = new HashMap<>();
        for(Entrenamiento entrenamiento: entrenamientos){
            int diaSemana = entrenamientoRutinaService.getdiaSemanaFromRutinaAndEntrenamientoId(id, entrenamiento.getId());
            dia.put(entrenamiento.getId(),diaSemana);
        }
        model.addAttribute("entrenamientos", entrenamientos);
        model.addAttribute("dia", dia);
        model.addAttribute("cumplimiento", cumplimiento);

        return "cliente/rutinaNoActiva";
    }

    private float calcularCumplimiento(Integer id) {
        float res = 0;
        Entrenamiento e = entrenamientoService.getReferenceById(id);
        for(EjercicioEntrenamiento ee: e.getEjercicios()){
            if(ee.getDesempeno()!=null){
                res++;
            }
        }
        return (res/e.getEjercicios().size())*100;
    }

    private float calcularCumplimientoDieta(Integer id) {
        float res = 0;
        Comida c = comidaService.getReferenceById(id);
        for(ComidaMenu cm : c.getMenus()){
            if(cm.getDesempeno()!=null){
                res++;
            }
        }
        return (res/c.getMenus().size())*100;
    }

    @GetMapping("/menu")
    public String menu(@RequestParam("id") Integer id, Model model){
        Cliente client = clienteService.getClienteByUserId2(id);
        Dieta dieta = client.getDieta();
        Set<DietaComida> dietaComidas = dietaService.getComidaDietaByDietaId(dieta.getId());
        List<Comida> comidas = dietaService.findComidasByDietaId(dieta.getId());
        model.addAttribute("dieta", dieta);
        model.addAttribute("dietaComidas", dietaComidas);
        model.addAttribute("comidas", comidas);
        model.addAttribute("comidaFiltro", new ComidaFiltro());
        model.addAttribute("client", client);
        model.addAttribute("desempenyoFiltro", new DesempenyoFiltro());
        return "cliente/menu";
    }

    @GetMapping("/dia")
    public String getDia(@RequestParam("id") Integer id, @RequestParam("clientId") Integer clientId, Model model){

        List<Ejercicio> ejercicios = ejercicioService.findEjerciciosByEntrenamientoId(id);
        model.addAttribute("ejercicios", ejercicios);
        HashMap<Integer, List<Float>> map = new HashMap<>();
        for(Ejercicio ejercicio : ejercicios) {
            List<Float> specs = ejercicioService.getEspecificacionesEjercicio(ejercicio.getId(), id);
            int realizado = 1;
            int eeID= ejercicioService.findId(ejercicio.getId(), id);
            EjercicioEntrenamiento ee = ejercicioEntrenamientoService.getReferenceById(eeID);
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
        Ejercicio ejercicio = ejercicioService.getReferenceById(id);
        Cliente cliente = clienteService.getReferenceById(clientId);
        //Desempeno desempeno = new Desempeno();
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
        Ejercicio ejercicio = ejercicioService.getReferenceById(id);
        Cliente cliente = clienteService.getReferenceById(clientId);
        Desempeno d = desempenoService.getDesempenoByEntrenamientoAndEjId(id, entrenamientoId);

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
        Cliente c = clienteService.getReferenceById(desempeno.getCliente());
        return "redirect:/rutina?id="+ c.getUsuario().getId();
    }

    @GetMapping("/comida")
    public String comida(@RequestParam("id") Integer id, @RequestParam("clientId") Integer clientId, Model model){
        Comida comida = comidaService.getReferenceById(id);
        Cliente client = clienteService.getReferenceById(clientId);
        Set<ComidaMenu> menus =  comida.getMenus();
        HashMap <Integer, Integer> desempenyo = new HashMap();
        for(ComidaMenu menu : menus){
            int realizado = 1;
            if(menu.getDesempeno()==null){
                realizado = 0;
            }
            desempenyo.put(menu.getMenu().getId(), realizado);
        }

        model.addAttribute("desempenyo", desempenyo);
        model.addAttribute("comida", comida);
        model.addAttribute("menus", menus);
        model.addAttribute("client", client);
        return "/cliente/comida";
    }

    @GetMapping("/dietaDesempeno")
    public String dietaDesempenyo(@RequestParam("id") Integer id,
                                  @RequestParam("comidaId") Integer comidaId,
                                  @RequestParam("clientId") Integer clientId,
                                  Model model){
        ComidaDesempenyo desempeno = new ComidaDesempenyo();
        Menu menu = menuService.getReferenceById(id);
        Cliente cliente = clienteService.getReferenceById(clientId);

        model.addAttribute("menu", menu);
        model.addAttribute("desempeno", desempeno);
        model.addAttribute("cliente", cliente);
        model.addAttribute("comidaId", comidaId);

        return "/cliente/dietaDesempenyo";
    }
    @PostMapping("/guardarDesempenoDieta")
    public String guardarDesempenoDieta(@ModelAttribute("desempeno") ComidaDesempenyo desempeno, Model model){
        desempenoService.guardarDesempenoDieta(desempeno.getCliente(), 0F, desempeno.getComentarios(), desempeno.getValoracion(), desempeno.getMenu(), desempeno.getComida());
        Cliente c = clienteService.getReferenceById(desempeno.getCliente());
        return "redirect:/menu?id="+ c.getUsuario().getId();
    }

    @GetMapping("/verDietaDesempeno")
    public String verDietaDesempeno(@RequestParam("id") Integer id,
                                    @RequestParam("comidaId") Integer comidaId,
                                    @RequestParam("clientId") Integer clientId,
                                    Model model){
        ComidaDesempenyo desempeno = new ComidaDesempenyo();
        Menu menu = menuService.getReferenceById(id);
        Cliente cliente = clienteService.getReferenceById(clientId);
        Desempeno d = desempenoService.getDesempenoByMenuAndComidaId(id, comidaId);

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
        Cliente client = clienteService.getClienteByUserId2(rutinaFiltro.getClienteId());
        List<Rutina> rutina = rutinaService.getRutinasByNameAndClientId(rutinaFiltro.getClienteId(), rutinaFiltro.getNombre());


        model.addAttribute("rutina", rutina);
        model.addAttribute("cliente", client);
        model.addAttribute("rutinaFiltro", new RutinaFiltro());
        model.addAttribute("desempenyoFiltro", new DesempenyoFiltro());
        return "/cliente/rutinaFiltrada";
    }

//    @PostMapping("/filtrarComida")
//    public String comidaFiltrada(@ModelAttribute("comidaFiltro") ComidaFiltro comidaFiltro, Model model){
//        Set<DietaComida> comidaFiltrada = comidaDietaRepository.getComidaDietaByMomentoDiaYNombre(comidaFiltro.getNombre(), comidaFiltro.getMomentoDia());
//        model.addAttribute("dietaComidas", comidaFiltrada);
//        model.addAttribute("comidaFiltro", new ComidaFiltro());
//        return "/cliente/menu";
//    }

    @PostMapping("/filtrarRutinaDesempenyo")
    public String filtrarRutinaDesempenyo(@ModelAttribute("desempenyoFiltro") DesempenyoFiltro filtro, Model model){
        Cliente client = clienteService.getClienteByUserId2(filtro.getIdCliente());
        List<Rutina> rutina = rutinaService.getAllRutinasByClienteId(filtro.getIdCliente());
        List<Rutina> rutinasFiltradas = new ArrayList<>();
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
        for(Rutina r : rutina){
            List<Entrenamiento> entrenamientos = entrenamientoService.findByRutinaId(r.getId());
            for(Entrenamiento e : entrenamientos){
                desempenyoTotal += calcularCumplimiento(e.getId());
            }
            desempenyoTotal = desempenyoTotal/entrenamientos.size();
            cumplimiento.put(r.getId(), desempenyoTotal);
            if(desempenyoTotal <= upperBound && desempenyoTotal >= lowerBound){
                rutinasFiltradas.add(r);
                rutina.remove(r);
                if(rutina.isEmpty()){
                    break;
                }
            }
        }
        model.addAttribute("rutinasFiltradas", rutinasFiltradas);
        model.addAttribute("rutinas", rutina);
        model.addAttribute("cumplimiento", cumplimiento);
        return "/cliente/rutinaDesempenyoFiltrada";
    }

    @PostMapping("/filtrarDietaDesempenyo")
    public String filtrarDietaDesempenyo(@ModelAttribute("desempenyoFiltro") DesempenyoFiltro filtro, Model model){
        Cliente client = clienteService.getClienteByUserId2(filtro.getIdCliente());
        List<Dieta> dieta = dietaService.getDietaByClientId(filtro.getIdCliente());
        List<Dieta> dietasFiltradas = new ArrayList<>();
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
        for(Dieta d : dieta){
            List<Comida> comidas = comidaService.findByDietaId(d.getId());
            for(Comida c : comidas){
                desempenyoTotal += calcularCumplimientoDieta(c.getId());
            }
            desempenyoTotal = desempenyoTotal/comidas.size();
            cumplimiento.put(d.getId(), desempenyoTotal);
            if(desempenyoTotal <= upperBound && desempenyoTotal >= lowerBound){
                dietasFiltradas.add(d);
                dieta.remove(d);
                if(dieta.isEmpty()){
                    break;
                }
            }
        }
        model.addAttribute("dietasFiltradas", dietasFiltradas);
        model.addAttribute("dietas", dieta);
        model.addAttribute("cumplimiento", cumplimiento);
        return "/cliente/dietaDesempenyoFiltrada";
    }

    @GetMapping("/eliminarDesempeno")
    public String eliminarDesempeno(@RequestParam("id") Integer id,
                                    @RequestParam("clientId") Integer clientId,
                                    @RequestParam("entrenamientoId") Integer entrenamientoId,
                                    Model model){
        Cliente c = clienteService.getReferenceById(clientId);
        desempenoService.delete(clientId, id, entrenamientoId);
        return "redirect:/rutina?id="+ c.getUsuario().getId();
    }

    @GetMapping("/eliminarDesempenoMenu")
    public String eliminarDesempenoMenu(@RequestParam("id") Integer id,
                                    @RequestParam("clientId") Integer clientId,
                                    @RequestParam("comidaId") Integer comidaId,
                                    Model model){
        Cliente c = clienteService.getReferenceById(clientId);
        desempenoService.deleteMenu(clientId, id, comidaId);
        return "redirect:/menu?id="+ c.getUsuario().getId();
    }


}

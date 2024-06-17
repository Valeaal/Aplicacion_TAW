package es.uma.proyectotaw.controller;

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
    protected EjercicioRepository ejercicioRepository;
    @Autowired
    protected UsuarioRepository usuarioRepository;
    @Autowired
    private TipoUsuarioRepository tipoUsuarioRepository;
    @Autowired
    private RutinaRepository rutinaRepository;
    @Autowired
    private ClienteRepository clienteRepository;
    @Autowired
    private EntrenamientoRepository entrenamientoRepository;
    @Autowired
    private GrupoMuscularRepository grupoMuscularRepository;
    @Autowired
    private DesempenoRepository desempenoRepository;
    @Autowired
    private EjercicioEntrenamientoRepository ejercicioEntrenamientoRepository;
    @Autowired
    private ComidaRepository comidaRepository;
    @Autowired
    private DietaRepository dietaRepository;
    @Autowired
    private ComidaDietaRepository comidaDietaRepository;
    @Autowired
    private EntrenamientoRutinaRepository entrenamientoRutinaRepository;
    @Autowired
    private MenuRepository menuRepository;
    @Autowired
    private ComidaMenuRepository comidaMenuRepository;

    @GetMapping("/rutina")
    public String rutina(@RequestParam("id") Integer id, Model model) {
        Cliente client = clienteRepository.getClienteByUserId(id);
        Rutina rutina = rutinaRepository.getActiveRutinasByClienteId(client.getId()).get(0);
        List<Entrenamiento> entrenamientos = entrenamientoRepository.findByRutinaId(rutina.getId());
        List<GrupoMuscular> grupomuscular = grupoMuscularRepository.findAll();

        HashMap<Integer, Float> cumplimiento= new HashMap<>();
        for(Entrenamiento entrenamiento: entrenamientos){
            float c = calcularCumplimiento(entrenamiento.getId());
            cumplimiento.put(entrenamiento.getId(), c);
        }

        HashMap<Integer, Integer> dia = new HashMap<>();
        for(Entrenamiento entrenamiento: entrenamientos){
            int diaSemana = entrenamientoRutinaRepository.getdiaSemanaFromRutinaAndEntrenamientoId(rutina.getId(), entrenamiento.getId());
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
        List<Entrenamiento> entrenamientos = entrenamientoRepository.findByRutinaId(id);
        HashMap<Integer, Float> cumplimiento= new HashMap<>();
        for(Entrenamiento entrenamiento: entrenamientos){
            float c = calcularCumplimiento(entrenamiento.getId());
            cumplimiento.put(entrenamiento.getId(), c);
        }
        HashMap<Integer, Integer> dia = new HashMap<>();
        for(Entrenamiento entrenamiento: entrenamientos){
            int diaSemana = entrenamientoRutinaRepository.getdiaSemanaFromRutinaAndEntrenamientoId(id, entrenamiento.getId());
            dia.put(entrenamiento.getId(),diaSemana);
        }
        model.addAttribute("entrenamientos", entrenamientos);
        model.addAttribute("dia", dia);
        model.addAttribute("cumplimiento", cumplimiento);

        return "cliente/rutinaNoActiva";
    }

    private float calcularCumplimiento(Integer id) {
        float res = 0;
        Entrenamiento e = entrenamientoRepository.getReferenceById(id);
        for(EjercicioEntrenamiento ee: e.getEjercicios()){
            if(ee.getDesempeno()!=null){
                res++;
            }
        }
        return (res/e.getEjercicios().size())*100;
    }

    private float calcularCumplimientoDieta(Integer id) {
        float res = 0;
        Comida c = comidaRepository.getReferenceById(id);
        for(ComidaMenu cm : c.getMenus()){
            if(cm.getDesempeno()!=null){
                res++;
            }
        }
        return (res/c.getMenus().size())*100;
    }

    @GetMapping("/menu")
    public String menu(@RequestParam("id") Integer id, Model model){
        Cliente client = clienteRepository.getClienteByUserId(id);
        Dieta dieta = client.getDieta();
        Set<DietaComida> dietaComidas = dietaRepository.getComidaDietaByDietaId(dieta.getId());
        List<Comida> comidas = dietaRepository.findComidasByDietaId(dieta.getId());
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
        List<Ejercicio> ejercicios = ejercicioRepository.findEjerciciosByEntrenamientoId(id);
        model.addAttribute("ejercicios", ejercicios);
        HashMap<Integer, List<Float>> map = new HashMap<>();
        for(Ejercicio ejercicio : ejercicios) {
            int series = ejercicioRepository.findEjercicioSeries(ejercicio.getId(), id);
            int rep = ejercicioRepository.findEjercicioRepeticiones(ejercicio.getId(), id);
            float peso = ejercicioRepository.findEjercicioPeso(ejercicio.getId(), id);
            int realizado = 1;
            int eeID= ejercicioRepository.findId(ejercicio.getId(), id);
            EjercicioEntrenamiento ee = ejercicioEntrenamientoRepository.getReferenceById(eeID);
            if(ee.getDesempeno()==null){
                realizado = 0;
            }
            List<Float> lista = new ArrayList<>();
            lista.add((float) series);
            lista.add((float) rep);
            lista.add(peso);
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
        Ejercicio ejercicio = ejercicioRepository.getReferenceById(id);
        Cliente cliente = clienteRepository.getReferenceById(clientId);
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
        Ejercicio ejercicio = ejercicioRepository.getReferenceById(id);
        Cliente cliente = clienteRepository.getReferenceById(clientId);
        Desempeno d = desempenoRepository.getDesempenoByEntremanientoAndEjId(id, entrenamientoId);

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
        Desempeno d = new Desempeno();
        Cliente c = clienteRepository.getReferenceById(desempeno.getCliente());
        d.setCliente(c);
        d.setPesoRealizado(desempeno.getPesoRealizado());
        d.setComentarios(desempeno.getComentarios());
        d.setValoracion(desempeno.getValoracion());

        EjercicioEntrenamiento ee = ejercicioEntrenamientoRepository.getEjercicioEntrenamientoFromEjAndEntrenamientoId(desempeno.getEjercicio(), desempeno.getEntrenamiento());
        ee.setOrden(1);
        ee.setDesempeno(d);

        desempenoRepository.save(d);
        ejercicioEntrenamientoRepository.save(ee);
        return "redirect:/rutina?id="+ c.getUsuario().getId();
    }

    @GetMapping("/comida")
    public String comida(@RequestParam("id") Integer id, @RequestParam("clientId") Integer clientId, Model model){
        Comida comida = comidaRepository.getReferenceById(id);
        Cliente client = clienteRepository.getReferenceById(clientId);
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
        Menu menu = menuRepository.getReferenceById(id);
        Cliente cliente = clienteRepository.getReferenceById(clientId);

        model.addAttribute("menu", menu);
        model.addAttribute("desempeno", desempeno);
        model.addAttribute("cliente", cliente);
        model.addAttribute("comidaId", comidaId);

        return "/cliente/dietaDesempenyo";
    }
    @PostMapping("/guardarDesempenoDieta")
    public String guardarDesempenoDieta(@ModelAttribute("desempeno") ComidaDesempenyo desempeno, Model model){
        Desempeno d = new Desempeno();
        Cliente c = clienteRepository.getReferenceById(desempeno.getCliente());
        d.setCliente(c);
        d.setPesoRealizado(0F);
        d.setComentarios(desempeno.getComentarios());
        d.setValoracion(desempeno.getValoracion());

        ComidaMenu cm = comidaMenuRepository.getcomidaMenuByMenuAndComidaId(desempeno.getMenu(), desempeno.getComida());
        cm.setDesempeno(d);

        desempenoRepository.save(d);
        comidaMenuRepository.save(cm);
        return "redirect:/menu?id="+ c.getUsuario().getId();
    }

    @GetMapping("/verDietaDesempeno")
    public String verDietaDesempeno(@RequestParam("id") Integer id,
                                    @RequestParam("comidaId") Integer comidaId,
                                    @RequestParam("clientId") Integer clientId,
                                    Model model){
        ComidaDesempenyo desempeno = new ComidaDesempenyo();
        Menu menu = menuRepository.getReferenceById(id);
        Cliente cliente = clienteRepository.getReferenceById(clientId);
        Desempeno d = desempenoRepository.getDesempenoByMenuAndComidaId(id, comidaId);

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
        Cliente client = clienteRepository.getClienteByUserId(rutinaFiltro.getClienteId());
        List<Rutina> rutina = rutinaRepository.getRutinasByNameAndClientId(rutinaFiltro.getClienteId(), rutinaFiltro.getNombre());


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
        Cliente client = clienteRepository.getClienteByUserId(filtro.getIdCliente());
        List<Rutina> rutina = rutinaRepository.getAllRutinasByClienteId(filtro.getIdCliente());
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
            List<Entrenamiento> entrenamientos = entrenamientoRepository.findByRutinaId(r.getId());
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
        Cliente client = clienteRepository.getClienteByUserId(filtro.getIdCliente());
        List<Dieta> dieta = dietaRepository.getDietaByClientId(filtro.getIdCliente());
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
            List<Comida> comidas = comidaRepository.findByDietaId(d.getId());
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
        Cliente c = clienteRepository.getReferenceById(clientId);
        EjercicioEntrenamiento ee = ejercicioEntrenamientoRepository.getEjercicioEntrenamientoFromEjAndEntrenamientoId(id, entrenamientoId);
        Desempeno d = ee.getDesempeno();
        ee.setDesempeno(null);
        ejercicioEntrenamientoRepository.saveAndFlush(ee);
        desempenoRepository.delete(d);
        return "redirect:/rutina?id="+ c.getUsuario().getId();
    }

    @GetMapping("/eliminarDesempenoMenu")
    public String eliminarDesempenoMenu(@RequestParam("id") Integer id,
                                    @RequestParam("clientId") Integer clientId,
                                    @RequestParam("comidaId") Integer comidaId,
                                    Model model){
        Cliente c = clienteRepository.getReferenceById(clientId);
        ComidaMenu cm = comidaMenuRepository.getcomidaMenuByMenuAndComidaId(id, comidaId);
        Desempeno d = cm.getDesempeno();
        cm.setDesempeno(null);
        comidaMenuRepository.saveAndFlush(cm);
        desempenoRepository.delete(d);
        return "redirect:/menu?id="+ c.getUsuario().getId();
    }


}

package es.uma.proyectotaw.controller;

import es.uma.proyectotaw.ui.ComidaFiltro;
import es.uma.proyectotaw.ui.DesempenyoFiltro;
import es.uma.proyectotaw.ui.DesempenyoYEjercicio;
import es.uma.proyectotaw.ui.RutinaFiltro;
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

// creado por: Alba de la Torre

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

    @GetMapping("/rutina")
    public String rutina(@RequestParam("id") Integer id, Model model) {
        Cliente client = clienteRepository.getClienteByUserId(id);
        Rutina rutina = rutinaRepository.getActiveRutinasByClienteId(client.getId()).get(0);
        int n = rutina.getId();
        List<Entrenamiento> entrenamientos = entrenamientoRepository.findByRutinaId(rutina.getId());
        List<GrupoMuscular> grupomuscular = grupoMuscularRepository.findAll();
        model.addAttribute("rutina", rutina);
        model.addAttribute("cliente", client);
        model.addAttribute("entrenamientos", entrenamientos);
        model.addAttribute("grupomuscular", grupomuscular);
        model.addAttribute("rutinaFiltro", new RutinaFiltro());
        model.addAttribute("desempenyoFiltro", new DesempenyoFiltro());
        return "cliente/rutina";
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
        ee.setDesempeno(d);

        desempenoRepository.save(d);
        ejercicioEntrenamientoRepository.save(ee);
        return "redirect:/rutina?id="+ c.getUsuario().getId();
    }

    @GetMapping("/comida")
    public String comida(@RequestParam("id") Integer id, Model model){
        Comida comida = comidaRepository.getReferenceById(id);
        model.addAttribute("comida", comida);
        return "/cliente/comida";
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

    @PostMapping("/filtrarComida")
    public String comidaFiltrada(@ModelAttribute("comidaFiltro") ComidaFiltro comidaFiltro, Model model){
        Set<DietaComida> comidaFiltrada = comidaDietaRepository.getComidaDietaByMomentoDiaYNombre(comidaFiltro.getNombre(), comidaFiltro.getMomentoDia());
        model.addAttribute("dietaComidas", comidaFiltrada);
        model.addAttribute("comidaFiltro", new ComidaFiltro());
        return "/cliente/menu";
    }

    @PostMapping("/filtrarRutinaDesempenyo")
    public String filtrarRutinaDesempenyo(@ModelAttribute("desempenyoFiltro") DesempenyoFiltro filtro, Model model){
        Cliente client = clienteRepository.getClienteByUserId(filtro.getIdCliente());
        List<Rutina> rutina = rutinaRepository.getAllRutinasByClienteId(filtro.getIdCliente());
        for(Rutina r : rutina){
            List<Entrenamiento> entrenamientos = entrenamientoRepository.findByRutinaId(r.getId());
            for(Entrenamiento e : entrenamientos){
            }
        }
        return "/cliente/rutinaDesempenyoFiltrada";
    }
}

package es.uma.proyectotaw.controller;

import es.uma.proyectotaw.ui.ComidaFiltro;
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
        List<Entrenamiento> entrenamientos = entrenamientoRepository.findByRutinaId(rutina.getId());
        List<GrupoMuscular> grupomuscular = grupoMuscularRepository.findAll();
        model.addAttribute("rutina", rutina);
        model.addAttribute("cliente", client);
        model.addAttribute("entrenamientos", entrenamientos);
        model.addAttribute("grupomuscular", grupomuscular);
        return "cliente/rutina";
    }

    @GetMapping("/menu")
    public String menu(@RequestParam("id") Integer id, Model model){
        Cliente client = clienteRepository.getClienteByUserId(id);
        Dieta dieta = client.getDieta();
        Set<DietaComida> dietaComidas = dieta.getDietas();
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
            int series = ejercicioRepository.findEjercicioSeries(ejercicio.getId());
            int rep = ejercicioRepository.findEjercicioRepeticiones(ejercicio.getId());
            float peso = ejercicioRepository.findEjercicioPeso(ejercicio.getId());
            List<Float> lista = new ArrayList<>();
            lista.add((float) series);
            lista.add((float) rep);
            lista.add(peso);
            map.put(ejercicio.getId(), lista);
        }
        model.addAttribute("map", map);
        model.addAttribute("clientId", clientId);
        return "/cliente/diaRutina";
    }

    @GetMapping("/desempeno")
    public String getDesempeno(@RequestParam("id") Integer id, @RequestParam("clientId") Integer clientId, Model model){
        Ejercicio ejercicio = ejercicioRepository.getReferenceById(id);
        model.addAttribute("ejercicio", ejercicio);
        model.addAttribute("clientId", clientId);
        return "/cliente/desempeno";
    }

    @PostMapping("/guardarDesempeno")
    public String guardarDesempeno(@RequestParam("valoracion") String valoracion,
                                   @RequestParam("peso") String peso,
                                   @RequestParam("comentario") String comentario,
                                   @RequestParam("clientId") Integer clientId,
                                   @RequestParam("ejercicioId") Integer ejercicioId,
                                   Model model){
        Desempeno desempeno = new Desempeno();
        desempeno.setCliente(clienteRepository.getClienteByUserId(clientId));
        desempeno.setComentarios(comentario);
        desempeno.setValoracion(Integer.parseInt(valoracion));
        desempeno.setPesoRealizado(Float.parseFloat(peso));
        EjercicioEntrenamiento ejEntrenamiento = ejercicioEntrenamientoRepository.getEjercicioEntrenamientoFromEjId(ejercicioId);
        ejEntrenamiento.setDesempeno(desempeno);
        ejercicioEntrenamientoRepository.save(ejEntrenamiento);
        desempenoRepository.save(desempeno);
        return "redirect:/cliente/diaRutina";
    }

    @GetMapping("/comida")
    public String comida(@RequestParam("id") Integer id, Model model){
        Comida comida = comidaRepository.getReferenceById(id);
        model.addAttribute("comida", comida);
        return "/cliente/comida";
    }

    @PostMapping("/filtrarRutina")
    public String rutinaFiltrada(@RequestParam("nombre") String nombre,
                                 @RequestParam("peso") Integer peso,
                                 @RequestParam("cliente") Integer clientId,
                                 @RequestParam("parteCuerpo") Integer parteId,
                                 Model model){

        List<Rutina> rutinas = rutinaRepository.getAllRutinasByClienteId(clientId);
        int pesoinf=0;
        int pesosup=0;
        if(peso==0){
            peso = null;
        } else if(peso==1){
            pesoinf=0;
            pesosup=20;
        }
        if(parteId==0){
            parteId = null;
        }
        List<Ejercicio> ejercicio = ejercicioRepository.getEjercicioByClienteIdFiltrado(clientId, nombre, peso, parteId);
        model.addAttribute("ejercicios", ejercicio);
        return "/cliente/rutinaFiltrada";
    }

    @PostMapping("/filtrarComida")
    public String comidaFiltrada(@ModelAttribute("comidaFiltro") ComidaFiltro comidaFiltro, Model model){
        Set<DietaComida> comidaFiltrada = comidaDietaRepository.getComidaDietaByMomentoDiaYNombre(comidaFiltro.getNombre(), comidaFiltro.getMomentoDia());
        model.addAttribute("dietaComidas", comidaFiltrada);
        model.addAttribute("comidaFiltro", new ComidaFiltro());
        return "/cliente/menu";
    }
}

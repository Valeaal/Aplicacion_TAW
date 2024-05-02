package es.uma.proyectotaw.controller;

import org.springframework.ui.Model;
import es.uma.proyectotaw.entity.*;
import es.uma.proyectotaw.dao.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

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
        return "redirect:/dia";
    }
}

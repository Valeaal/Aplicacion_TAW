package es.uma.proyectotaw.service;

import es.uma.proyectotaw.dao.*;

import es.uma.proyectotaw.dto.*;

import es.uma.proyectotaw.dto.RutinaDTO;
import es.uma.proyectotaw.dto.UsuarioDTO;

import es.uma.proyectotaw.entity.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

import java.util.ArrayList;
import java.util.HashSet;

import java.util.List;
import java.util.Set;

// autor: Alba de la Torre
// autor: Miguel Galdeano Rodríguez

@Service
public class RutinaService extends DTOService<RutinaDTO, Rutina> {
    @Autowired
    RutinaRepository rutinaRepository;
    @Autowired

    UsuarioRepository usuarioRepository;
    @Autowired
    Cliente_RutinaRepository clienteRutinaRepository;
    @Autowired
    EntrenamientoRutinaRepository entrenamientoRutinaRepository;
    @Autowired
    Tipo_RutinaRepository tipoRutinaRepository;

    private Tipo_RutinaRepository tipo_RutinaRepository;

    @Autowired
    private Entrenamiento_RutinaRepository entrenamiento_RutinaRepository;
    @Autowired
    private Cliente_RutinaRepository cliente_RutinaRepository;


    public RutinaDTO getActiveRutinasByClienteId(Integer idCliente) {
        Rutina rutina = rutinaRepository.getActiveRutinasByClienteId(idCliente).get(0);
        return rutina.toDTO();
    }

    public List<RutinaDTO> getRutinasByNameAndClientId(Integer clientId, String name) {
        List<Rutina> lista = rutinaRepository.getRutinasByNameAndClientId(clientId, name);
        return this.entidadesADTO(lista);
    }

    public List<RutinaDTO> getAllRutinasByClienteId(Integer clientId) {
        List<Rutina> rutinas = rutinaRepository.getAllRutinasByClienteId(clientId);
        return this.entidadesADTO(rutinas);
    }

    public List<RutinaDTO> getCrossfitRutinas() { // pablo
        List<Rutina> rutinas = rutinaRepository.getCrossfitRutinas();
        return this.entidadesADTO(rutinas);
    }

    public List<RutinaDTO> filtrarPorNombre(String nombre) { // pablo
        List<Rutina> rutinas = rutinaRepository.filtrarPorNombre(nombre);
        return this.entidadesADTO(rutinas);
    }

    public List<RutinaDTO> filtrarPornumEntrenamientos(Integer n) { // pablo
        List<Rutina> rutinas = rutinaRepository.filtrarPornumEntrenamientos(n);
        return this.entidadesADTO(rutinas);
    }

    public RutinaDTO findById(Integer idRutina) {
        Rutina rutina = rutinaRepository.findById(idRutina).orElse(null);
        return rutina.toDTO();
    }


    public List<RutinaDTO> findByTipo(Integer tipoRutina) {
        List<Rutina> rutinas = rutinaRepository.findByTipo(tipoRutina);
        return this.entidadesADTO(rutinas);
    }

    public List<RutinaDTO> filtrarPorNombreynEntrenamientos(String nombre, Integer n){ // pablo
        List<Rutina> rutinas = rutinaRepository.filtrarPorNombreynEntrenamientos(nombre,n);
        return this.entidadesADTO(rutinas);
    }

    public List<RutinaDTO> findByNombre(String nombre,Integer tipo, LocalDate fecha){ // pablo
        List<Rutina> rutinas = rutinaRepository.findByNombre(nombre,tipo,fecha);
        return this.entidadesADTO(rutinas);
    }

    public List<RutinaDTO> findByNombreAndEntrenos(String nombre,Integer numEntrenos,Integer tipo, LocalDate fecha){ // pablo
        List<Rutina> rutinas = rutinaRepository.findByNombreAndEntrenos(nombre,numEntrenos,tipo,fecha);
        return this.entidadesADTO(rutinas);
    }

    public List<RutinaDTO> findByNombreAndCreador(String nombre, Integer usuario, Integer tipo, LocalDate fecha){ // pablo
        List<Rutina> rutinas = rutinaRepository.findByNombreAndCreador(nombre,usuario,tipo,fecha);
        return this.entidadesADTO(rutinas);
    }

    public List<RutinaDTO> findByNombreEntrenosAndCreador(String nombre,Integer numEntrenos, Integer usuario, Integer tipo, LocalDate fecha){ // pablo
        List<Rutina> rutinas = rutinaRepository.findByNombreEntrenosAndCreador(nombre,numEntrenos,usuario,tipo,fecha);
        return this.entidadesADTO(rutinas);
    }

    @SuppressWarnings("unchecked")
    public void guardar(RutinaDTO rutinaDTO){
        Rutina r = new Rutina();
        Usuario entrenador = this.usuarioRepository.buscarPorID(rutinaDTO.getEntrenador().getId());
        r.setEntrenador(entrenador);
        r.setDescripcion(rutinaDTO.getDescripcion());
        r.setNombre(rutinaDTO.getNombre());

        Set<ClienteRutinaDTO> clienteRutinaDTOS = rutinaDTO.getClientes();
        Set<ClienteRutina> clienteRutinas = new HashSet<>();

        for(ClienteRutinaDTO cr : clienteRutinaDTOS){
            clienteRutinas.add(this.clienteRutinaRepository.findById(cr.getId()).orElse(null));
        }

        Set<EntrenamientoRutinaDTO> entrenamientoRutinaDTOS = rutinaDTO.getEntrenamientos();
        Set<EntrenamientoRutina> entrenamientoRutinas = new HashSet<>();

        for(EntrenamientoRutinaDTO er : entrenamientoRutinaDTOS){
            entrenamientoRutinas.add(this.entrenamientoRutinaRepository.findById(er.getId()).orElse(null));
        }
        r.setClientes(clienteRutinas);
        r.setEntrenamientos(entrenamientoRutinas);
        TipoRutina tipoRutina = this.tipoRutinaRepository.findById(rutinaDTO.getTipoRutina().getId()).orElse(null);
        r.setTipoRutina(tipoRutina);
        r.setFechaCreacion(rutinaDTO.getFechaCreacion());
        this.rutinaRepository.save(r);
    }

    public void delete(Integer rutinaID) {
        Rutina r = this.rutinaRepository.findById(rutinaID).orElse(new Rutina());
        this.rutinaRepository.delete(r);
    }

    public RutinaDTO getRutinaByNombre(String nombre) {
        Rutina rutina = rutinaRepository.getRutinaByNombre(nombre);
        return rutina.toDTO();
    }


    public void guardarRutina(RutinaDTO rutina) { //pablo
        Rutina nuevaRutina = new Rutina();

        TipoRutina tr = tipo_RutinaRepository.findById(rutina.getTipoRutina().getId()).orElse(null);
        nuevaRutina.setTipoRutina(tr);
        nuevaRutina.setNombre(rutina.getNombre());
        nuevaRutina.setDescripcion(rutina.getDescripcion());
        nuevaRutina.setFechaCreacion(LocalDate.now());
        Usuario ent = usuarioRepository.findById(rutina.getEntrenador().getId()).orElse(null);
        nuevaRutina.setEntrenador(ent);
        nuevaRutina.setId(rutina.getId());
        rutinaRepository.save(nuevaRutina);
    }

    public void borrarRutina(RutinaDTO rutina) { //pablo
        Rutina rutinaBorrar = new Rutina();
        rutinaBorrar.setId(rutina.getId());
        for (EntrenamientoRutina erEliminados : this.entrenamiento_RutinaRepository.findAll()) {
            if (erEliminados.getRutina().getId() == rutinaBorrar.getId()) {
                this.entrenamiento_RutinaRepository.delete(erEliminados);
            }
        }
        for (ClienteRutina cr : this.cliente_RutinaRepository.findAll()) {
            if (cr.getRutina().getId() == rutinaBorrar.getId()) {
                this.cliente_RutinaRepository.delete(cr);
            }
        }
        this.rutinaRepository.delete(rutinaBorrar);

    }
}

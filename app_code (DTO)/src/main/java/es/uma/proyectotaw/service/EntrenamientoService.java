//autor: Alba de la Torre           40%
//autor: Miguel Galdeano Rodríguez  30%
//autor: Álvaro Valencia Villalón   30%
//Modificad los porcentajes, lo he puesto orientativo
package es.uma.proyectotaw.service;

import es.uma.proyectotaw.dao.*;
import es.uma.proyectotaw.dto.*;
import es.uma.proyectotaw.entity.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


@Service
public class EntrenamientoService extends DTOService<EntrenamientoDTO, Entrenamiento>{

    @Autowired
    private EntrenamientoRepository entrenamientoRepository;
    @Autowired
    private EntrenamientoRutinaRepository entrenamientoRutinaRepository;
    @Autowired
    private EjercicioEntrenamientoRepository ejercicioEntrenamientoRepository;
    @Autowired
    private EjercicioRepository ejercicioRepository;
    @Autowired
    private RutinaRepository rutinaRepository;

    public List<EntrenamientoDTO> findAll(){
        List<Entrenamiento> entrenamientos = this.entrenamientoRepository.findAll();
        return this.entidadesADTO(entrenamientos);
    }

    public List<EntrenamientoDTO> findByRutinaId(Integer rutinaId) {
        List<Entrenamiento> er = entrenamientoRepository.findByRutinaId(rutinaId);
        return this.entidadesADTO(er);
    }

    public EntrenamientoDTO findbyID(Integer id) {
        return entrenamientoRepository.findById(id).orElse(null).toDTO();
    }

    public EntrenamientoDTO getReferenceById(Integer id) {
        return entrenamientoRepository.getReferenceById(id).toDTO();
    }

    public Entrenamiento getEntityById(Integer id) {
        return entrenamientoRepository.getReferenceById(id);
    }

    public void guardar(EntrenamientoDTO entrenamientoDTO) {
        Entrenamiento entrenamiento = this.entrenamientoRepository.findById(entrenamientoDTO.getId()).orElse(new Entrenamiento());
        entrenamiento.setNombre(entrenamientoDTO.getNombre());
        entrenamiento.setDescripcion(entrenamientoDTO.getDescripcion());

        Set<Integer> rutinas = new HashSet<Integer>();
        for(Integer rutina : entrenamientoDTO.getRutinas()){
           Rutina r = this.rutinaRepository.findById(rutina).orElse(null);
           if(r != null){
               rutinas.add(r.getId());
           }
        }

        Set<Integer> ejercicios = new HashSet<Integer>();
        for(Integer ejercicio : entrenamientoDTO.getEjercicios()){
           Ejercicio e = this.ejercicioRepository.findById(ejercicio).orElse(null);
            if(e != null){
                rutinas.add(e.getId());
            }
        }

        this.entrenamientoRepository.save(entrenamiento);
    }

    public void delete(Integer entrenamientoId){
        Entrenamiento e = this.entrenamientoRepository.findById(entrenamientoId).orElse(null);
        EjercicioEntrenamiento ee = this.ejercicioEntrenamientoRepository.findByEntrenamientoID(entrenamientoId);
        EntrenamientoRutina er = this.entrenamientoRutinaRepository.findByEntrenamientoID(entrenamientoId);
        this.ejercicioEntrenamientoRepository.delete(ee);
        this.entrenamientoRutinaRepository.delete(er);
        this.entrenamientoRepository.delete(e);
    }

    public List<EntrenamientoDTO> DTOfindAll(){
        List<Entrenamiento> entrenamientos = this.entrenamientoRepository.findAll();
        return this.entidadesADTO(entrenamientos);
    }

    public EntrenamientoDTO buscarPorString(String nombre) {
        return entrenamientoRepository.buscarPorString(nombre).toDTO();
    }
}

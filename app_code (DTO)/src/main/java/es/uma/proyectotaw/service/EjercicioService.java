//Autor: Álvaro Valencia Villalón
package es.uma.proyectotaw.service;

import es.uma.proyectotaw.dao.EjercicioRepository;
import es.uma.proyectotaw.dao.GrupoMuscularRepository;
import es.uma.proyectotaw.dao.TipoEjercicioRepository;
import es.uma.proyectotaw.dto.EjercicioDTO;
import es.uma.proyectotaw.entity.Ejercicio;
import es.uma.proyectotaw.entity.GrupoMuscular;
import es.uma.proyectotaw.entity.TipoEjercicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class EjercicioService extends DTOService<EjercicioDTO, Ejercicio>{

    @Autowired
    EjercicioRepository ejercicioRepository;
    @Autowired
    TipoEjercicioRepository tipoEjercicioRepository;
    @Autowired
    GrupoMuscularRepository grupoMuscularRepository;

    public List<EjercicioDTO> findAll() {
        return this.entidadesADTO(ejercicioRepository.findAll());
    }

    public Ejercicio getReferenceById(Integer id) { return ejercicioRepository.getReferenceById(id);}

    public List<EjercicioDTO> filtrarEjercicios(String inputNombre, Integer inputTipo, Integer inputGrupo){
        return this.entidadesADTO(ejercicioRepository.filtrarEjercicios(inputNombre, inputTipo, inputGrupo));
    }

    public void save(EjercicioDTO ejercicioDTO) {
        Ejercicio ejercicio = new Ejercicio();
        ejercicio.setNombre(ejercicioDTO.getNombre());

        TipoEjercicio tipoEjercicio = tipoEjercicioRepository.findById(ejercicioDTO.getTipo().getId()).orElse(null);
        ejercicio.setTipo(tipoEjercicio);

        if (ejercicioDTO.getGrupoMuscular() != null) {        //No es obligatorio según la bdd
            GrupoMuscular grupoMuscular = grupoMuscularRepository.findById(ejercicioDTO.getGrupoMuscular().getId()).orElse(null);
            ejercicio.setGrupoMuscular(grupoMuscular);
        }

        if (ejercicioDTO.getDescripcion() != null){         //No es obligatorio según la bdd
            ejercicio.setDescripcion(ejercicioDTO.getDescripcion());
        }

        if (ejercicioDTO.getUrlVideo() != null) {            //No es obligatorio según la bdd
            ejercicio.setDescripcion(ejercicioDTO.getDescripcion());
        }

        ejercicioRepository.save(ejercicio);
    }

    public List<Ejercicio> findEjerciciosByEntrenamientoId(Integer id){
        return this.ejercicioRepository.findEjerciciosByEntrenamientoId(id);
    }

    public List<Float> getEspecificacionesEjercicio(Integer ejId, Integer entrenamientoId){
        List<Float> res = new ArrayList<>();
        int series = ejercicioRepository.findEjercicioSeries(ejId, entrenamientoId);
        int rep = ejercicioRepository.findEjercicioRepeticiones(ejId, entrenamientoId);
        float peso = ejercicioRepository.findEjercicioPeso(ejId, entrenamientoId);
        res.add((float) series);
        res.add((float) rep);
        res.add(peso);
        return res;
    }

    public Integer findId(Integer ejId, Integer entrenamientoId){
        return ejercicioRepository.findId(ejId, entrenamientoId);
    }
}

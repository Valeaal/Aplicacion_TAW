//Autor: Álvaro Valencia Villalón 70%
//Autor: Alba de la Torre 20%
//Autor: Miguel Galdeano Rodríguez 10%
package es.uma.proyectotaw.service;

import es.uma.proyectotaw.dao.EjercicioEntrenamientoRepository;
import es.uma.proyectotaw.dao.EjercicioRepository;
import es.uma.proyectotaw.dao.GrupoMuscularRepository;
import es.uma.proyectotaw.dao.TipoEjercicioRepository;
import es.uma.proyectotaw.dto.EjercicioDTO;
import es.uma.proyectotaw.entity.Ejercicio;
import es.uma.proyectotaw.entity.EjercicioEntrenamiento;
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
    @Autowired
    EjercicioEntrenamientoRepository entrenamientoRepository;
    @Autowired
    private EjercicioEntrenamientoRepository ejercicioEntrenamientoRepository;

    public List<EjercicioDTO> findAll() {
        return this.entidadesADTO(ejercicioRepository.findAll());
    }

    public EjercicioDTO getReferenceById(Integer id) { return ejercicioRepository.getReferenceById(id).toDTO();}

    public List<EjercicioDTO> filtrarEjercicios(String inputNombre, Integer inputTipo, Integer inputGrupo){
        return this.entidadesADTO(ejercicioRepository.filtrarEjercicios(inputNombre, inputTipo, inputGrupo));
    }

    public void save(EjercicioDTO ejercicioDTO) {
        Ejercicio ejercicio;
        if(ejercicioDTO.getId() == null){                   //Permitimos crear nuevo ejercicio y actualizar en el mismo método
            ejercicio = new Ejercicio();
        } else{
            ejercicio = ejercicioRepository.findById(ejercicioDTO.getId()).orElse(null);
        }

        ejercicio.setNombre(ejercicioDTO.getNombre());

        TipoEjercicio tipoEjercicio = tipoEjercicioRepository.findById(ejercicioDTO.getTipo().getId()).orElse(null);
        ejercicio.setTipo(tipoEjercicio);

        if (ejercicioDTO.getGrupoMuscular().getId() == null){       //Debido a las características del form de Spring, al actualizar un grupo muscular actualizamos grupoMuscular.id, por lo que luego aquí hacemos que efectivamente el grupoMuscular sea null
            ejercicio.setGrupoMuscular(null);
        } else{
            if (ejercicioDTO.getGrupoMuscular() != null) {          //No es obligatorio según la bdd
                GrupoMuscular grupoMuscular = grupoMuscularRepository.findById(ejercicioDTO.getGrupoMuscular().getId()).orElse(null);
                ejercicio.setGrupoMuscular(grupoMuscular);
            }
        }

        if (ejercicioDTO.getDescripcion() != null){             //No es obligatorio según la bdd
            ejercicio.setDescripcion(ejercicioDTO.getDescripcion());
        }

        if (ejercicioDTO.getUrlVideo() != null) {               //No es obligatorio según la bdd
            ejercicio.setDescripcion(ejercicioDTO.getDescripcion());
        }

        ejercicioRepository.save(ejercicio);
    }

    public List<EjercicioDTO> findEjerciciosByEntrenamientoId(Integer id){
        List<Ejercicio> lista = this.ejercicioRepository.findEjerciciosByEntrenamientoId(id);
        return this.entidadesADTO(lista);
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

    public void deleteById(Integer ejercicioId) {
        List<EjercicioEntrenamiento> ee = ejercicioEntrenamientoRepository.getEjercicioEntrenamientoPorEjercicioId(ejercicioId);
        for (EjercicioEntrenamiento e: ee) {                                    //Eliminado en cascada
            ejercicioEntrenamientoRepository.delete(e);
        }
        ejercicioRepository.deleteById(ejercicioId);
    }

    public EjercicioDTO findById(Integer ejercicioId) {
        return ejercicioRepository.findById(ejercicioId).get().toDTO();
    }

    public EjercicioDTO buscarPorString(String nombre) {
        return ejercicioRepository.buscarPorString(nombre).toDTO();
    }
}

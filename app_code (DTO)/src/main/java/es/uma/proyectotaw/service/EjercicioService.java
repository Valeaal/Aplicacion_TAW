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

    public List<EjercicioDTO> filtrarEjercicios(String inputNombre, Integer inputTipo, Integer inputGrupo){
        return this.entidadesADTO(ejercicioRepository.filtrarEjercicios(inputNombre, inputTipo, inputGrupo));
    }

    public void save(EjercicioDTO ejercicioDTO) {
        Ejercicio ejercicio;
        if(ejercicioDTO.getId() == null){                   //Permitimos crar nuevo ejercicio y actualizar en el mismo método
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

    public void deleteById(Integer ejercicioId) {
        ejercicioRepository.deleteById(ejercicioId);
    }

    public EjercicioDTO findById(Integer ejercicioId) {
        return ejercicioRepository.findById(ejercicioId).get().toDTO();
    }
}

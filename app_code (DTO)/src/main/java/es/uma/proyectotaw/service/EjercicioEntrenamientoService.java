//Autor: Álvaro Valencia Vilallón 50%
//Autor: Alba de la Torre 30%
//Autor: Miguel Galdeano Rodríguez 20%
package es.uma.proyectotaw.service;

import es.uma.proyectotaw.dao.DesempenoRepository;
import es.uma.proyectotaw.dao.EjercicioEntrenamientoRepository;
import es.uma.proyectotaw.dao.EjercicioRepository;
import es.uma.proyectotaw.dao.EntrenamientoRepository;
import es.uma.proyectotaw.dto.EjercicioEntrenamientoDTO;
import es.uma.proyectotaw.entity.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EjercicioEntrenamientoService extends DTOService<EjercicioEntrenamientoDTO, EjercicioEntrenamiento> {

    @Autowired
    private EjercicioEntrenamientoRepository ejercicioEntrenamientoRepository;
    @Autowired
    private DesempenoRepository desempenoRepository;
    @Autowired
    private EjercicioRepository ejercicioRepository;
    @Autowired
    private EntrenamientoRepository entrenamientoRepository;

    public EjercicioEntrenamientoDTO getReferenceById(Integer id) {
        return ejercicioEntrenamientoRepository.getReferenceById(id).toDTO();
    }

    public EjercicioEntrenamiento getEjercicioEntrenamientoFromEjAndEntrenamientoId(Integer ejId, Integer entrenamientoId) {
        return ejercicioEntrenamientoRepository.getEjercicioEntrenamientoFromEjAndEntrenamientoId(ejId, entrenamientoId);
    }

    public List<EjercicioEntrenamientoDTO> findAll(){
        return this.entidadesADTO(ejercicioEntrenamientoRepository.findAll());
    }

    public List<EjercicioEntrenamientoDTO> filtrarEjerciciosEntrenamiento(Integer inputEjercicio, Integer inputEntrenamiento, Integer inputSeries, Integer inputRepeticiones, Float inputPeso, Integer inputTiempo, Float inputDistancia, Integer inputOrden){
        return this.entidadesADTO(ejercicioEntrenamientoRepository.filtrarEjerciciosEntrenamiento(inputEjercicio, inputEntrenamiento, inputSeries, inputRepeticiones, inputPeso, inputTiempo, inputDistancia, inputOrden));
    }

    public EjercicioEntrenamientoDTO findByDesempeno(Integer desempeno) {
        EjercicioEntrenamiento EE =  this.ejercicioEntrenamientoRepository.findByDesempeno(desempeno);
        return EE == null ? null : EE.toDTO();
    }

    public void save(EjercicioEntrenamientoDTO dto) {
        EjercicioEntrenamiento ejercicioEntrenamientoEntity;
        if(dto.getId() == null){                                                                                            //Permitimos crear una entidad o actualizarla con el mismo método
            ejercicioEntrenamientoEntity = new EjercicioEntrenamiento();                                                    //Crea un nuevo objeto y el motor de persistencia genera el ID
        } else{
            ejercicioEntrenamientoEntity = ejercicioEntrenamientoRepository.findById(dto.getId()).orElse(null);       //Es fundamental mantener el ID para no estropear las relaciones, esto lo consigue (recuperamos el objeto con su ID)
        }

        if (dto.getDesempeno() != null){                                              //Puede ser nulo
            Desempeno desempeno = desempenoRepository.findById(dto.getDesempeno()).orElse(null);
            ejercicioEntrenamientoEntity.setDesempeno(desempeno);
        } else {
            ejercicioEntrenamientoEntity.setDesempeno(null);
        }

        if (dto.getDistancia() != null){                                              //Puede ser nulo
            ejercicioEntrenamientoEntity.setDistancia(dto.getDistancia());
        } else {
            ejercicioEntrenamientoEntity.setDistancia(null);
        }

        Ejercicio ejercicio = ejercicioRepository.findById(dto.getEjercicio().getId()).orElse(null);
        ejercicioEntrenamientoEntity.setEjercicio(ejercicio);

        Entrenamiento entrenamiento = entrenamientoRepository.findById(dto.getEntrenamiento().getId()).orElse(null);
        ejercicioEntrenamientoEntity.setEntrenamiento(entrenamiento);

        ejercicioEntrenamientoEntity.setOrden(dto.getOrden());

        if (dto.getPeso() != null){                                                     //Puede ser nulo
            ejercicioEntrenamientoEntity.setPeso(dto.getPeso());
        } else {
            ejercicioEntrenamientoEntity.setPeso(null);
        }

        if (dto.getRepeticiones() != null){                                              //Puede ser nulo
            ejercicioEntrenamientoEntity.setRepeticiones(dto.getRepeticiones());
        } else {
            ejercicioEntrenamientoEntity.setRepeticiones(null);
        }

        if (dto.getSeries() != null){                                                   //Puede ser nulo
            ejercicioEntrenamientoEntity.setSeries(dto.getSeries());
        } else {
            ejercicioEntrenamientoEntity.setSeries(null);
        }

        if (dto.getTiempo() != null){                                                   //Puede ser nulo
            ejercicioEntrenamientoEntity.setTiempo(dto.getTiempo());
        } else {
            ejercicioEntrenamientoEntity.setTiempo(null);
        }

        ejercicioEntrenamientoRepository.save(ejercicioEntrenamientoEntity);
    }

    public void deleteById(Integer id) {
        ejercicioEntrenamientoRepository.deleteById(id);
    }

    public EjercicioEntrenamientoDTO getById(Integer id) {
        return ejercicioEntrenamientoRepository.getById(id).toDTO();
    }

}

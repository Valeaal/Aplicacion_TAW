package es.uma.proyectotaw.service;

import es.uma.proyectotaw.dao.DietaRepository;
import es.uma.proyectotaw.dao.UsuarioRepository;
import es.uma.proyectotaw.dto.*;
import es.uma.proyectotaw.entity.*;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
// autor: Alba de la Torre y Javier Torrecilla
@Service
public class DietaService extends DTOService<DietaDTO, Dieta> {
    @Autowired
    private DietaRepository dietaRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;


    public Dieta getEntityById(Integer id){
        return dietaRepository.getReferenceById(id);
    }

    public List<DietaDTO> getDietaByClientId(Integer id){
        return this.entidadesADTO(dietaRepository.getDietaByClientId(id));
    }

    public List<DietaDTO> getAll(){
        return this.entidadesADTO(dietaRepository.findAll());
    }

    public DietaDTO getDietaById(Integer id){
        return dietaRepository.buscarPorID(id).toDTO();
    }

    public void deleteDieta(Integer dieta){
        Dieta d = dietaRepository.getReferenceById(dieta);
        dietaRepository.delete(d);
    }

    public List<DietaDTO> dietasFiltradas(String nombre, Integer calorias){
        return this.entidadesADTO(dietaRepository.getDietasFiltradas(nombre, calorias));
    }


    public void guardarDieta(DietaDTO dietaDTO){
        Dieta dietaEntity = new Dieta();

        dietaEntity.setNombre(dietaDTO.getNombre());
        dietaEntity.setDietista(usuarioRepository.buscarPorID(dietaDTO.getDietista().getId()));
        dietaEntity.setFecha(dietaDTO.getFecha());
        dietaEntity.setCalorias(dietaDTO.getCalorias());

        if(dietaDTO.getDietas()==null){
            dietaEntity.setDietas(null);
        }else{
            Set<DietaComida> addDC = new HashSet<>();
            Object [] ComidasDeDieta = dietaDTO.getDietas().toArray();
            for(Object o : ComidasDeDieta){
                addDC.add((DietaComida) o);
            }
            dietaEntity.setDietas(addDC);
        }

        if(dietaDTO.getDescripcion().equals(null)){
            dietaEntity.setDescripcion(null);
        }else{
            dietaEntity.setDescripcion(dietaDTO.getDescripcion());
        }

    }

    public void guardarDietaCreada(DietaDTO dietaDTO) {
        Dieta dieta = dietaRepository.findById(dietaDTO.getId()).orElse(new Dieta());
        dieta.setDescripcion(dietaDTO.getDescripcion());
        dieta.setNombre(dietaDTO.getNombre());
        dieta.setCalorias(dietaDTO.getCalorias());
        dieta.setFecha(dietaDTO.getFecha());
        Usuario usuario = this.usuarioRepository.findById(dietaDTO.getDietista().getId()).orElseThrow();
        dieta.setDietista(usuario);

        this.dietaRepository.save(dieta);
    }

}

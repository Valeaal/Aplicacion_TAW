package es.uma.proyectotaw.service;

import es.uma.proyectotaw.dao.ClienteRepository;
import es.uma.proyectotaw.dao.ComidaMenuRepository;
import es.uma.proyectotaw.dao.DesempenoRepository;
import es.uma.proyectotaw.dao.EjercicioEntrenamientoRepository;
import es.uma.proyectotaw.dto.ClienteDTO;
import es.uma.proyectotaw.dto.DesempenoDTO;
import es.uma.proyectotaw.dto.EjercicioEntrenamientoDTO;
import es.uma.proyectotaw.dto.RutinaDTO;
import es.uma.proyectotaw.entity.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
//autor: Alba de la Torre
//autor: Miguel Galdeano Rodríguez

@Service
public class DesempenoService extends DTOService<DesempenoDTO, Desempeno>{

    @Autowired
    private DesempenoRepository desempenoRepository;
    @Autowired
    private ClienteRepository clienteRepository;
    @Autowired
    private EjercicioEntrenamientoRepository ejercicioEntrenamientoRepository;
    @Autowired
    private ComidaMenuRepository comidaMenuRepository;

    public DesempenoDTO getDesempenoByEntrenamientoAndEjId(Integer ejId, Integer entrenamientoId) {
        return desempenoRepository.getDesempenoByEntremanientoAndEjId(ejId, entrenamientoId).toDTO();
    }

    public void guardarDesempeno(Integer clientId, float peso, String comentarios, int valoracion, Integer ejId, Integer entrenamientoId){
        Desempeno d = new Desempeno();
        Cliente c = clienteRepository.getReferenceById(clientId);
        d.setCliente(c);
        d.setPesoRealizado(peso);
        d.setComentarios(comentarios);
        d.setValoracion(valoracion);

        EjercicioEntrenamiento ee = ejercicioEntrenamientoRepository.getEjercicioEntrenamientoFromEjAndEntrenamientoId(ejId, entrenamientoId);
        ee.setOrden(1);
        ee.setDesempeno(d);

        desempenoRepository.save(d);
        ejercicioEntrenamientoRepository.save(ee);
    }

    public void guardarDesempenoDieta(Integer clientId, float peso, String comentarios, int valoracion, Integer menuId, Integer comidaId){
        Desempeno d = new Desempeno();
        Cliente c = clienteRepository.getReferenceById(clientId);
        d.setCliente(c);
        d.setPesoRealizado(peso);
        d.setComentarios(comentarios);
        d.setValoracion(valoracion);

        ComidaMenu cm = comidaMenuRepository.getcomidaMenuByMenuAndComidaId(menuId, comidaId);
        cm.setDesempeno(d);

        desempenoRepository.save(d);
        comidaMenuRepository.save(cm);
    }

    public DesempenoDTO getDesempenoByMenuAndComidaId(Integer menuId, Integer comidaId){
        return desempenoRepository.getDesempenoByMenuAndComidaId(menuId, comidaId).toDTO();
    }

    public List<DesempenoDTO> getByClienteId(Integer clienteID){
        List<Desempeno> desempenos = this.desempenoRepository.desempenoDelCliente(clienteID);
        List<DesempenoDTO> desempenoDTOs = new ArrayList<>();
        for(Desempeno d : desempenos){
            desempenoDTOs.add(d.toDTO());
        }
        return desempenoDTOs;
    }

    public void delete(Integer clientId, Integer ejId, Integer entrenamientoId){
        Cliente c = clienteRepository.getReferenceById(clientId);
        EjercicioEntrenamiento ee = ejercicioEntrenamientoRepository.getEjercicioEntrenamientoFromEjAndEntrenamientoId(ejId, entrenamientoId);
        Desempeno d = ee.getDesempeno();
        ee.setDesempeno(null);
        ejercicioEntrenamientoRepository.saveAndFlush(ee);
        desempenoRepository.delete(d);
    }

    public void deleteMenu(Integer clientId, Integer menuId, Integer comidaId){
        Cliente c = clienteRepository.getReferenceById(clientId);
        ComidaMenu cm = comidaMenuRepository.getcomidaMenuByMenuAndComidaId(menuId, comidaId);
        Desempeno d = cm.getDesempeno();
        cm.setDesempeno(null);
        comidaMenuRepository.saveAndFlush(cm);
        desempenoRepository.delete(d);
    }

}

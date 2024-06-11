//Autor: Álvaro Valencia Vilallón
package es.uma.proyectotaw.service;

import es.uma.proyectotaw.dao.EjercicioRepository;
import es.uma.proyectotaw.dao.TipoUsuarioRepository;
import es.uma.proyectotaw.dao.UsuarioRepository;
import es.uma.proyectotaw.dto.EjercicioDTO;
import es.uma.proyectotaw.dto.TipoUsuarioDTO;
import es.uma.proyectotaw.dto.UsuarioDTO;
import es.uma.proyectotaw.entity.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AppService {

    @Autowired
    protected EjercicioRepository ejercicioRepository;
    @Autowired
    protected UsuarioRepository usuarioRepository;
    @Autowired
    private TipoUsuarioRepository tipoUsuarioRepository;

    public UsuarioDTO buscarPorEmail(String inputEmail) {
        Usuario usr = usuarioRepository.buscarPorEmail(inputEmail);
        if (usr == null) {
            return null;
        }
        return usr.toDTO();
    }

    public TipoUsuarioDTO buscarPorID (Integer id){
        TipoUsuario tipo = tipoUsuarioRepository.buscarPorID(id);
        TipoUsuarioDTO tipoDTO = tipo.toDTO();
        return tipoDTO;
    }

    public List<EjercicioDTO> listaEjerciciosCompleta(){
        List<Ejercicio> ejercicios = ejercicioRepository.findAll();

        EjercicioService dtoService = new EjercicioService(); // Instancia de DTOService que nos proporciona la posibilidad convertir el conjunto a dto
        List<EjercicioDTO> ejerciciosDTO = dtoService.entidadesADTO(ejercicios);

        return ejerciciosDTO;
    }


}

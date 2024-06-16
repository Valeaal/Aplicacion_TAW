//Autor: Álvaro Valencia Villalón
package es.uma.proyectotaw.service;

import es.uma.proyectotaw.dao.ClienteRepository;
import es.uma.proyectotaw.dao.DietaRepository;
import es.uma.proyectotaw.dao.UsuarioRepository;
import es.uma.proyectotaw.dto.ClienteDTO;
import es.uma.proyectotaw.entity.Cliente;
import es.uma.proyectotaw.entity.Dieta;
import es.uma.proyectotaw.entity.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Service;

@Service
public class ClienteService extends DTOService<ClienteDTO, Cliente>{

    @Autowired
    private ClienteRepository clienteRepository;
    @Autowired
    private UsuarioRepository usuarioRepository;
    @Autowired
    private DietaRepository dietaRepository;

    public ClienteDTO getClienteByUserId(Integer id){
        Cliente clienteEntity = clienteRepository.getClienteByUserId(id);
        return clienteEntity.toDTO();
    }

    public void guardarCliente (ClienteDTO clienteDTO){
        Cliente clienteEntity = new Cliente();

        clienteEntity.setId(clienteDTO.getId());
        clienteEntity.setAltura(clienteDTO.getAltura());
        clienteEntity.setEdad(clienteDTO.getEdad());
        clienteEntity.setPeso(clienteDTO.getPeso());

        if(clienteDTO.getDietista() == null){
            clienteEntity.setDietista(null);
        } else{
            Usuario dietista = usuarioRepository.buscarPorID(clienteDTO.getDietista().getId());
            clienteEntity.setDietista(dietista);
        }

        if(clienteDTO.getEntrenador() == null){
            clienteEntity.setEntrenador(null);
        } else{
            Usuario entrenador = usuarioRepository.buscarPorID(clienteDTO.getEntrenador().getId());
            clienteEntity.setEntrenador(entrenador);
        }

        if(clienteDTO.getDieta() == null){
            clienteEntity.setDieta(null);
        } else{
            Dieta dieta = dietaRepository.findById(clienteDTO.getDieta().getId()).orElse(null);
            clienteEntity.setDieta(dieta);
        }

        //Al contrario que los campos anteriores que tienen ifElse, este no puede ser nulo.
        Usuario usuario = usuarioRepository.buscarPorID(clienteDTO.getUsuario().getId());
        clienteEntity.setUsuario(usuario);

        clienteRepository.save(clienteEntity);
    }

}
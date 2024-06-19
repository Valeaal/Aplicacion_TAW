//Autor: Álvaro Valencia Villalón
package es.uma.proyectotaw.service;

import es.uma.proyectotaw.dao.ClienteRepository;
import es.uma.proyectotaw.dao.DietaRepository;
import es.uma.proyectotaw.dao.UsuarioRepository;
import es.uma.proyectotaw.dto.ClienteDTO;
import es.uma.proyectotaw.dto.UsuarioDTO;
import es.uma.proyectotaw.entity.Cliente;
import es.uma.proyectotaw.entity.Dieta;
import es.uma.proyectotaw.entity.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClienteService extends DTOService<ClienteDTO, Cliente> {

    @Autowired
    private ClienteRepository clienteRepository;
    @Autowired
    private UsuarioRepository usuarioRepository;
    @Autowired
    private DietaRepository dietaRepository;

    public ClienteDTO getClienteByUserId(Integer id) {
        Cliente clienteEntity = clienteRepository.getClienteByUserId(id);
        return clienteEntity.toDTO();
    }

    public ClienteDTO getClienteById(Integer id){
        return this.clienteRepository.getClienteById(id).toDTO();
    }

    public ClienteDTO getReferenceById(Integer id){
        return clienteRepository.getReferenceById(id).toDTO();
    }

    public void guardarCliente(ClienteDTO clienteDTO) {
        Cliente clienteEntity = new Cliente();

        clienteEntity.setId(clienteDTO.getId());
        clienteEntity.setAltura(clienteDTO.getAltura());
        clienteEntity.setEdad(clienteDTO.getEdad());
        clienteEntity.setPeso(clienteDTO.getPeso());

        if (clienteDTO.getDietista() == null) {
            clienteEntity.setDietista(null);
        } else {
            Usuario dietista = usuarioRepository.buscarPorID(clienteDTO.getDietista().getId());
            clienteEntity.setDietista(dietista);
        }

        if (clienteDTO.getEntrenador() == null) {
            clienteEntity.setEntrenador(null);
        } else {
            Usuario entrenador = usuarioRepository.buscarPorID(clienteDTO.getEntrenador().getId());
            clienteEntity.setEntrenador(entrenador);
        }

        if (clienteDTO.getDieta() == null) {
            clienteEntity.setDieta(null);
        } else {
            Dieta dieta = dietaRepository.findById(clienteDTO.getDieta().getId()).orElse(null);
            clienteEntity.setDieta(dieta);
        }

        //Al contrario que los campos anteriores que tienen ifElse, este no puede ser nulo.
        Usuario usuario = usuarioRepository.buscarPorID(clienteDTO.getUsuario().getId());
        clienteEntity.setUsuario(usuario);

        clienteRepository.save(clienteEntity);
    }

    public List<ClienteDTO> getClientesDelEntrenador(Integer crossfitTrainerId) { //pablo
        List<Cliente> clientesDTO = clienteRepository.getClientesDelEntrenador(crossfitTrainerId);
        return this.entidadesADTO(clientesDTO);
    }
    
    public List<ClienteDTO> findByEntrenador(UsuarioDTO usuarioDTO){
        Usuario usuario = this.usuarioRepository.findById(usuarioDTO.getId()).orElse(null);
        return entidadesADTO(this.clienteRepository.findByEntrenador(usuario));
    }

    public List<ClienteDTO> getClientesDelEntrenadorYFiltro(Integer crossfitTrainerId, String nombre) { //pablo
        List<Cliente> clientesDTO = clienteRepository.getClientesDelEntrenadorYFiltro(crossfitTrainerId,nombre);
        return this.entidadesADTO(clientesDTO);
    }

    public ClienteDTO getClienteById(Integer id) { //pablo
        Cliente clienteEntity = clienteRepository.getClienteById(id);
        return clienteEntity.toDTO();
    }

}

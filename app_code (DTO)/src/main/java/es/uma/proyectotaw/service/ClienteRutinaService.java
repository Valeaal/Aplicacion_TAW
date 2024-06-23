package es.uma.proyectotaw.service;

import ch.qos.logback.core.net.server.Client;
import es.uma.proyectotaw.dao.ClienteRepository;
import es.uma.proyectotaw.dao.Cliente_RutinaRepository;
import es.uma.proyectotaw.dao.RutinaRepository;
import es.uma.proyectotaw.dto.ClienteDTO;
import es.uma.proyectotaw.dto.ClienteRutinaDTO;
import es.uma.proyectotaw.dto.RutinaDTO;
import es.uma.proyectotaw.dto.UsuarioDTO;
import es.uma.proyectotaw.entity.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
// autor: Pablo Alonso Burgos
//autor: Miguel Galdeano Rodríguez
@Service
public class ClienteRutinaService extends DTOService<ClienteRutinaDTO, ClienteRutina> {

    @Autowired
    Cliente_RutinaRepository clienteRutinaRepository;
    @Autowired
    private ClienteRepository clienteRepository;
    @Autowired
    private RutinaRepository rutinaRepository;

    public ClienteRutinaDTO findById(Integer id) {
        return this.clienteRutinaRepository.findById(id).orElse(null).toDTO();
    }

    public ClienteRutinaDTO findByActiveRoutines(Integer clienteID) {
        List<ClienteRutina> cr = this.clienteRutinaRepository.findActiveRoutines(clienteID);
        return cr.isEmpty() ? null : cr.get(0).toDTO();
    }

    public List<ClienteRutinaDTO> findByRoutines (Integer rutinaID){
        return this.entidadesADTO(this.clienteRutinaRepository.rutinasPorRutina(rutinaID));
    }

    public List<ClienteRutinaDTO> findActiveRoutines(Integer clientId){
        List<ClienteRutina> lista = clienteRutinaRepository.findActiveRoutines(clientId);
        return this.entidadesADTO(lista);
    }

    public List<ClienteRutinaDTO> historialRutinasCliente(Integer clientId){
        List<ClienteRutina> lista = clienteRutinaRepository.historialRutinasCliente(clientId);
        return this.entidadesADTO(lista);
    }

    public void guardarClienteRutina (ClienteRutinaDTO clienteRutina){
        ClienteRutina asignacionRutinaACliente = new ClienteRutina();
        Cliente cliente = clienteRepository.getClienteById(clienteRutina.getCliente());
        Rutina rutina = rutinaRepository.findById(clienteRutina.getRutina()).orElse(null);
        asignacionRutinaACliente.setCliente(cliente);
        asignacionRutinaACliente.setRutina(rutina);
        asignacionRutinaACliente.setVigente(true);
        for (ClienteRutina cr : this.clienteRutinaRepository.findActiveRoutines(cliente.getId())) { // para que la nueva sea la vigente
            cr.setVigente(false);
        }
        this.clienteRutinaRepository.save(asignacionRutinaACliente);
    }

    public void guardar(ClienteRutinaDTO clienteRutinaDTO){
        ClienteRutina clienteRutina = new ClienteRutina();
        clienteRutina.setId(clienteRutinaDTO.getRutina());
        clienteRutina.setCliente(this.clienteRepository.findById(clienteRutinaDTO.getCliente()).orElse(null));
        clienteRutina.setRutina(this.rutinaRepository.findById(clienteRutinaDTO.getRutina()).orElse(null));
        clienteRutina.setVigente(clienteRutinaDTO.getVigente());
        for (ClienteRutina cr : this.clienteRutinaRepository.findActiveRoutines(clienteRutinaDTO.getCliente())) {
            cr.setVigente(false);
        }
        clienteRutinaRepository.save(clienteRutina);
    }

    public void eliminar(ClienteRutinaDTO clienteRutinaDTO){
        ClienteRutina clienteRutina = this.clienteRutinaRepository.findById(clienteRutinaDTO.getId()).orElse(null);
        clienteRutinaRepository.delete(clienteRutina);
    }
}


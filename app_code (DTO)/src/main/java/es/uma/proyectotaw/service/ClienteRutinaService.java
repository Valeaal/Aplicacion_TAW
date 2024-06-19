package es.uma.proyectotaw.service;

import es.uma.proyectotaw.dao.ClienteRepository;
import es.uma.proyectotaw.dao.Cliente_RutinaRepository;
import es.uma.proyectotaw.dao.RutinaRepository;
import es.uma.proyectotaw.dto.ClienteRutinaDTO;
import es.uma.proyectotaw.entity.ClienteRutina;
import es.uma.proyectotaw.entity.Cliente;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

//autor: Miguel Galdeano Rodríguez
@Service
public class ClienteRutinaService extends DTOService<ClienteRutinaDTO, ClienteRutina> {
    @Autowired
    Cliente_RutinaRepository clienteRutinaRepository;
    @Autowired
    ClienteRepository clienteRepository;
    @Autowired
    RutinaRepository rutinaRepository;

    public ClienteRutinaDTO findById(Integer id) {
        return this.clienteRutinaRepository.findById(id).orElse(null).toDTO();
    }

    public ClienteRutinaDTO findByActiveRoutines(Integer clienteID) {
        return this.clienteRutinaRepository.findActiveRoutines(clienteID).get(0).toDTO();
    }

    public void guardar(ClienteRutinaDTO clienteRutinaDTO){
        ClienteRutina clienteRutina = new ClienteRutina();
        clienteRutina.setCliente(this.clienteRepository.findById(clienteRutinaDTO.getCliente()).orElse(null));
        clienteRutina.setRutina(this.rutinaRepository.findById(clienteRutinaDTO.getRutina()).orElse(null));
        clienteRutina.setVigente(clienteRutinaDTO.getVigente());
        clienteRutinaRepository.save(clienteRutina);
    }

}

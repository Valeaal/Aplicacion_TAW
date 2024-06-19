package es.uma.proyectotaw.service;

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

@Service
public class ClienteRutinaService extends DTOService<ClienteRutinaDTO, ClienteRutina> {

    @Autowired
    Cliente_RutinaRepository cliente_RutinaRepository;
    @Autowired
    private ClienteRepository clienteRepository;
    @Autowired
    private RutinaRepository rutinaRepository;


    public List<ClienteRutinaDTO> findActiveRoutines(Integer clientId){
        List<ClienteRutina> lista = cliente_RutinaRepository.findActiveRoutines(clientId);
        return this.entidadesADTO(lista);
    }

    public List<ClienteRutinaDTO> historialRutinasCliente(Integer clientId){
        List<ClienteRutina> lista = cliente_RutinaRepository.historialRutinasCliente(clientId);
        return this.entidadesADTO(lista);
    }

    public void guardarClienteRutina (ClienteRutinaDTO clienteRutina){
        ClienteRutina asignacionRutinaACliente = new ClienteRutina();
        Cliente cliente = clienteRepository.getClienteById(clienteRutina.getCliente());
        Rutina rutina = rutinaRepository.findById(clienteRutina.getRutina()).orElse(null);
        asignacionRutinaACliente.setCliente(cliente);
        asignacionRutinaACliente.setRutina(rutina);
        asignacionRutinaACliente.setVigente(true);
        for (ClienteRutina cr : this.cliente_RutinaRepository.findActiveRoutines(cliente.getId())) { // para que la nueva sea la vigente
            cr.setVigente(false);
        }
        this.cliente_RutinaRepository.save(asignacionRutinaACliente);
    }
}
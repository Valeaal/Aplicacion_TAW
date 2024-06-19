package es.uma.proyectotaw.entity;

import es.uma.proyectotaw.dto.ClienteRutinaDTO;
import es.uma.proyectotaw.dto.DTO;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "cliente_rutina")
public class ClienteRutina implements DTO<ClienteRutinaDTO> {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "cliente_id", nullable = false)
    private Cliente cliente;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "rutina_id", nullable = false)
    private Rutina rutina;

    @Column(name = "vigente", nullable = false)
    private Boolean vigente = false;

    public ClienteRutinaDTO toDTO() {
        ClienteRutinaDTO clienteRutinaDTO = new ClienteRutinaDTO();
        clienteRutinaDTO.setId(this.id);
        clienteRutinaDTO.setCliente(this.cliente.getId());
        clienteRutinaDTO.setRutina(this.rutina.getId());
        clienteRutinaDTO.setVigente(this.vigente);
        return clienteRutinaDTO;
    }
}
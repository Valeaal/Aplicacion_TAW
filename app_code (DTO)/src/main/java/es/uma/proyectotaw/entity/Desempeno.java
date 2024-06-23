package es.uma.proyectotaw.entity;

import es.uma.proyectotaw.dto.DTO;
import es.uma.proyectotaw.dto.DesempenoDTO;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "desempeno")
public class Desempeno implements DTO<DesempenoDTO> {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "cliente_id", nullable = false)
    private Cliente cliente;

    @Column(name = "valoracion", nullable = false)
    private Integer valoracion;

    @Column(name = "peso_realizado", nullable = false)
    private Float pesoRealizado;

    @Lob
    @Column(name = "comentarios")
    private String comentarios;

    public DesempenoDTO toDTO(){
        DesempenoDTO desempenoDTO = new DesempenoDTO();
        desempenoDTO.setId(id);
        desempenoDTO.setCliente(this.cliente.toDTO());
        desempenoDTO.setValoracion(this.valoracion);
        desempenoDTO.setPesoRealizado(this.pesoRealizado);
        desempenoDTO.setComentarios(this.comentarios);
        return desempenoDTO;
    }

}
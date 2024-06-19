package es.uma.proyectotaw.dto;

import es.uma.proyectotaw.entity.Cliente;
import es.uma.proyectotaw.entity.Rutina;
import jakarta.persistence.Column;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;
// autor: Alba de la Torre
@Data
public class ClienteRutinaDTO {
    private Integer id;
    private Integer cliente;
    private Integer rutina;
    private Boolean vigente;
}

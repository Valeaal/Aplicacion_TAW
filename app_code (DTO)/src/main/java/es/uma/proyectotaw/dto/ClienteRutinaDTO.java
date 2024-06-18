package es.uma.proyectotaw.dto;

import es.uma.proyectotaw.entity.Cliente;
import es.uma.proyectotaw.entity.Rutina;
import jakarta.persistence.Column;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;

@Data
public class ClienteRutinaDTO {
    private Integer id;
    private Boolean vigente;
}

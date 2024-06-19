package es.uma.proyectotaw.dto;

import es.uma.proyectotaw.entity.Cliente;
import es.uma.proyectotaw.service.DTOService;
import jakarta.persistence.*;
import lombok.Data;
// autor: Alba de la Torre

@Data
public class DesempenoDTO {
    private Integer id;
    private ClienteDTO cliente;
    private Integer valoracion;
    private Float pesoRealizado;
    private String comentarios;
}

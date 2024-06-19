package es.uma.proyectotaw.dto;

import jakarta.persistence.Column;
import jakarta.persistence.Lob;
import lombok.Data;
// autor: Alba de la Torre

@Data
public class TipoRutinaDTO {
    private Integer id;
    private String tipo;
}

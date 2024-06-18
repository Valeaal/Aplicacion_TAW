package es.uma.proyectotaw.dto;

import es.uma.proyectotaw.entity.ClienteRutina;
import es.uma.proyectotaw.entity.EntrenamientoRutina;
import es.uma.proyectotaw.entity.TipoRutina;
import es.uma.proyectotaw.entity.Usuario;
import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

public class RutinaDTO {
    private Integer id;
    private String nombre;
    private String descripcion;
    private LocalDate fechaCreacion;
    private UsuarioDTO entrenador;
    private TipoRutinaDTO tipoRutina;
    private Set<ClienteRutinaDTO> clientes = new HashSet<>();
    private Set<EntrenamientoRutinaDTO> entrenamientos = new HashSet<>();
}

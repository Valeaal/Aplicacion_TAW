package es.uma.proyectotaw.ui;

import java.time.LocalDate;

import es.uma.proyectotaw.dto.UsuarioDTO;
import es.uma.proyectotaw.entity.Usuario;

public class FiltroBodyBuilder {
    private String numEntrenamientos;
    private String nombre;
    private LocalDate fecha;
    private UsuarioDTO entrenadorCreador;

    public UsuarioDTO getEntrenadorCreador() {
        return entrenadorCreador;
    }

    public void setEntrenadorCreador(UsuarioDTO entrenadorCreador) {
        this.entrenadorCreador = entrenadorCreador;
    }

    public String getNumEntrenamientos() {
        return numEntrenamientos;
    }

    public void setNumEntrenamientos(String numEntrenamientos) {
        this.numEntrenamientos = numEntrenamientos;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }
}

package es.uma.proyectotaw.ui;

import java.time.LocalDate;

public class FiltroBodyBuilder {
    private String numEntrenamientos;
    private String nombre;
    private LocalDate fechaInicio;
    private LocalDate fechaFin;
    private boolean activo = true;

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

    public LocalDate getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(LocalDate fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public LocalDate getFechaFin() {
        return fechaFin;
    }

    public void setFechaFin(LocalDate fechaFin) {
        this.fechaFin = fechaFin;
    }

    public boolean getActivo(){
        return activo;
    }

    public void setActivo(Boolean activo){
        activo = this.activo;
    }

}

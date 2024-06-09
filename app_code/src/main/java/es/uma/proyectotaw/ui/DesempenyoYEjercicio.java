package es.uma.proyectotaw.ui;

import es.uma.proyectotaw.entity.Cliente;

public class DesempenyoYEjercicio {
    private Integer cliente;

    private Integer valoracion;

    private Float pesoRealizado;

    private String comentarios;

    private Integer ejercicio;
    private Integer entrenamiento;

    public Integer getEntrenamiento() {
        return entrenamiento;
    }

    public void setEntrenamiento(Integer entrenamiento) {
        this.entrenamiento = entrenamiento;
    }

    public DesempenyoYEjercicio() {
    }

    public Integer getCliente() {
        return cliente;
    }

    public void setCliente(Integer cliente) {
        this.cliente = cliente;
    }

    public Integer getValoracion() {
        return valoracion;
    }

    public void setValoracion(Integer valoracion) {
        this.valoracion = valoracion;
    }

    public Float getPesoRealizado() {
        return pesoRealizado;
    }

    public void setPesoRealizado(Float pesoRealizado) {
        this.pesoRealizado = pesoRealizado;
    }

    public String getComentarios() {
        return comentarios;
    }

    public void setComentarios(String comentarios) {
        this.comentarios = comentarios;
    }

    public Integer getEjercicio() {
        return ejercicio;
    }

    public void setEjercicio(Integer ejercicio) {
        this.ejercicio = ejercicio;
    }
}

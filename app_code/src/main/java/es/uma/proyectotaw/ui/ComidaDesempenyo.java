package es.uma.proyectotaw.ui;

// autor: Alba de la Torre
public class ComidaDesempenyo {
    private Integer cliente;

    private Integer valoracion;

    private String comentarios;

    private Integer comida;
    private Integer menu;

    public Integer getComida() {
        return comida;
    }

    public void setComida(Integer comida) {
        this.comida = comida;
    }

    public Integer getMenu() {
        return menu;
    }

    public void setMenu(Integer menu) {
        this.menu = menu;
    }

    public ComidaDesempenyo() {
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

    public String getComentarios() {
        return comentarios;
    }

    public void setComentarios(String comentarios) {
        this.comentarios = comentarios;
    }
}

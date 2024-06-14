package es.uma.proyectotaw.ui;

public class NuevaDieta {
        private String nombre;
        private String descripcion;
        private Integer calorias;
        private Integer comidasDiarias;
        private Integer Id;

        // Getters y setters

        public String getNombre() { return nombre; }
        public void setNombre(String nombre) { this.nombre = nombre; }

        public String getDescripcion() { return descripcion; }
        public void setDescripcion(String descripcion) { this.descripcion = descripcion; }

        public Integer getCalorias() { return calorias; }
        public void setCalorias(Integer calorias) { this.calorias = calorias; }

        public Integer getComidasDiarias() { return comidasDiarias; }
        public void setComidasDiarias(Integer comidasDiarias) { this.comidasDiarias = comidasDiarias; }

        public Integer getId() {return Id;}
        public void setId(Integer id) {Id = id;}
}

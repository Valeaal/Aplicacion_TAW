package es.uma.proyectotaw.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "desempeno")
public class Desempeno {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "cliente_id", nullable = false)
    private Cliente cliente;

    @Column(name = "valoracion", nullable = false)
    private Integer valoracion;

    @Column(name = "peso_realizado", nullable = false)
    private Float pesoRealizado;

    @Lob
    @Column(name = "comentarios")
    private String comentarios;

}
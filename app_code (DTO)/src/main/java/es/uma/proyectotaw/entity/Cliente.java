//Autor: Álvaro Valencia Villalón: 30%
package es.uma.proyectotaw.entity;

import es.uma.proyectotaw.dto.ClienteDTO;
import es.uma.proyectotaw.dto.DTO;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "cliente")
public class Cliente implements DTO<ClienteDTO> {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "usuario_id", nullable = false)
    private Usuario usuario;

    //@ManyToOne(fetch = FetchType.LAZY)
    //@JoinColumn(name = "rutina_id")
    //private Dieta rutina;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "entrenador_id")
    private Usuario entrenador;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "dieta_id")
    private Dieta dieta;

    @Column(name = "peso", nullable = false)
    private Float peso;

    @Column(name = "altura", nullable = false)
    private Integer altura;

    @Column(name = "edad", nullable = false)
    private Integer edad;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "dietista_id")
    private Usuario dietista;

    public ClienteDTO toDTO(){
        ClienteDTO clienteDTO = new ClienteDTO();
        clienteDTO.setId(this.id);
        clienteDTO.setAltura(this.altura);
        clienteDTO.setPeso(this.peso);
        clienteDTO.setEdad(this.edad);
        if (this.entrenador != null) {  //Podría ser null
            clienteDTO.setEntrenador(this.entrenador.toDTO());
        }
        if (this.dieta != null) {       //Podría ser null
            clienteDTO.setDieta(this.dieta.toDTO());
        }
        if (this.dietista != null) {    //Podría ser null
            clienteDTO.setDietista(this.getDietista().toDTO());
        }
        clienteDTO.setUsuario(this.getUsuario().toDTO());
        return clienteDTO;
    }

}
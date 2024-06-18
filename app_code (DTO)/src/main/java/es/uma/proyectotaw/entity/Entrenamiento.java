package es.uma.proyectotaw.entity;

import es.uma.proyectotaw.dto.DTO;
import es.uma.proyectotaw.dto.EntrenamientoDTO;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "entrenamiento")
public class Entrenamiento implements DTO<EntrenamientoDTO> {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "nombre", nullable = false)
    private String nombre;

    @Lob
    @Column(name = "descripcion")
    private String descripcion;

    @OneToMany(mappedBy = "entrenamiento")
    private Set<EntrenamientoRutina> rutinas = new HashSet<>();

    @OneToMany(mappedBy = "entrenamiento")
    private Set<EjercicioEntrenamiento> ejercicios = new HashSet<>();

    public EntrenamientoDTO toDTO(){
        EntrenamientoDTO dto = new EntrenamientoDTO();
        dto.setId(id);
        dto.setNombre(nombre);
        dto.setDescripcion(descripcion);
        Set<Integer> lista = new HashSet<>();
        for (EntrenamientoRutina rutina : rutinas) {
            lista.add(rutina.getId());
        }
        dto.setRutinas(lista);
        lista.clear();;
        for(EjercicioEntrenamiento ejercicio : ejercicios){
            lista.add(ejercicio.getId());
        }
        dto.setEjercicios(lista);
        return dto;
    }

}
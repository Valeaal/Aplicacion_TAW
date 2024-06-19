//Autor: Álvaro Valencia Villalón
package es.uma.proyectotaw.service;

import es.uma.proyectotaw.dto.DTO;
import es.uma.proyectotaw.dto.DietaComidaDTO;
import es.uma.proyectotaw.entity.DietaComida;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

//Cualquier servicio que extienda esta clase abstracta, podrá convertir los elementos "entities" a elementos
//"dto" de una lista o conjunto. Esta clase abstracta necesita de una DTOClass es decir, una clase que
//implementa la interfaz DTO, la cual tiene que tener un método que convierta de "entity" a "dto"

public abstract class DTOService<DTOClass, EntityClass> {

    protected List<DTOClass> entidadesADTO(List<EntityClass> entidades) {
        List<DTOClass> lista = new ArrayList<>();
        for (EntityClass entidad : entidades) {
            DTO<DTOClass> clase = (DTO<DTOClass>) entidad;
            lista.add(clase.toDTO());
        }
        return lista;
    }

    public Set<DTOClass> entidadesADTO(Set<EntityClass> entidades) {
        Set<DTOClass> conjunto = new HashSet<>();
        for (EntityClass entidad : entidades) {
            DTO<DTOClass> clase = (DTO<DTOClass>) entidad;
            conjunto.add(clase.toDTO());
        }
        return conjunto;
    }
}

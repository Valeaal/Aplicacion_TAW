//Autor: Álvaro Valencia Vilallón
package es.uma.proyectotaw.dto;

//Esta interfaz permite anunciar que una clase "entity" implementa el método para devolver su
//correspondiente objeto "dto". Por ejemplo en la clase abstracta DTOService se indica que
//un argumento del método que implementa sea una clase que implemente a su vez esta interfaz.

public interface DTO<DTOClass> {
    public DTOClass toDTO ();
}

//Autor: Álvaro Valencia Villalón
//Quien sea que participe aquí, que se añada como autor y el porcentaje

package es.uma.proyectotaw.dto;

import lombok.Data;

@Data
public class ComidaMenuDTO {

    public Integer id;
    public Integer comida;
    public Integer menu;
    public Integer desempeno;

    //Comida y desempeno pueden pasarse a integer -> A veces si para crear por ejemplo un menu necesita ComidaMenu y este un Menu, es recursivo
    //y da fallo de desbordamiento (Stack Overflow). Por eso menu es Integer
}

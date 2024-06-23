//Autor: Álvaro Valencia Villalón 66% Javier Torrecilla Reyes 33%
package es.uma.proyectotaw.dao;

import es.uma.proyectotaw.entity.ComidaMenu;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ComidaMenuRepository extends JpaRepository<ComidaMenu, Integer> {

    @Query("SELECT cm FROM ComidaMenu cm WHERE cm.comida.id=:comidaId AND cm.menu.id=:menuId")
    ComidaMenu getcomidaMenuByMenuAndComidaId(@Param("menuId") Integer menuId, @Param("comidaId") Integer comidaId);

    @Query("SELECT cm FROM ComidaMenu cm WHERE cm.menu.id = :menuId")
    List<ComidaMenu> getComidaMenuPorMenuId(@Param("menuId") Integer menuId);

    @Query("SELECT cm FROM ComidaMenu cm WHERE cm.desempeno.id = :id")
    ComidaMenu getComidaMenuPorDesempeno(@Param("id") Integer id);

}

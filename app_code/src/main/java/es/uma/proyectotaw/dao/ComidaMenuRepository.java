package es.uma.proyectotaw.dao;

import es.uma.proyectotaw.entity.ComidaMenu;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ComidaMenuRepository extends JpaRepository<ComidaMenu, Integer> {

    @Query("SELECT cm FROM ComidaMenu cm WHERE cm.comida.id=:comidaId AND cm.menu.id=:menuId")
    ComidaMenu getcomidaMenuByMenuAndComidaId(@Param("menuId") Integer menuId, @Param("comidaId") Integer comidaId);
}

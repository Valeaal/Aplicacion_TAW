package es.uma.proyectotaw.dao;

import es.uma.proyectotaw.entity.Ejercicio;
import es.uma.proyectotaw.entity.GrupoMuscular;
import es.uma.proyectotaw.entity.Menu;
import es.uma.proyectotaw.entity.TipoEjercicio;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface MenuRepository extends JpaRepository<Menu, Integer> {

    @Query("select m from Menu m where (m.nombre like concat('%', :inputNombre, '%'))" +
            "and (m.alergenos like concat('%', :inputAlergenos, '%'))")
    public List<Menu> filtrarMenus (@Param("inputNombre") String inputNombre,
                                    @Param("inputAlergenos") String inputAlergenos);

}

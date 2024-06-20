//Autor: Álvaro Valencia 50%
package es.uma.proyectotaw.dao;

import es.uma.proyectotaw.entity.DietaComida;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Set;

public interface ComidaDietaRepository extends JpaRepository<DietaComida, Integer> {
    @Query("SELECT dc FROM DietaComida dc JOIN Comida c ON c.id=dc.comida.id WHERE (:nombre IS NULL OR c.nombre like concat ('%', :nombre, '%')) AND (:momentoDia IS NULL OR dc.momentoDia=:momentoDia)")
    Set<DietaComida> getComidaDietaByMomentoDiaYNombre(@Param("nombre") String nombre, @Param("momentoDia") Integer momentoDia);

    @Query("SELECT dc FROM DietaComida dc WHERE dc.dieta.id = :dietaId")
    List<DietaComida> getDietaComidasPorDietaId(@Param("dietaId") Integer dietaId);
}

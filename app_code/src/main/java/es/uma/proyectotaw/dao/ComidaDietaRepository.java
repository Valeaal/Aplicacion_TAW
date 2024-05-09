package es.uma.proyectotaw.dao;

import es.uma.proyectotaw.entity.Comida;
import es.uma.proyectotaw.entity.DietaComida;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Set;

public interface ComidaDietaRepository extends JpaRepository<DietaComida, Integer> {
    @Query("SELECT dc FROM DietaComida dc JOIN Comida c ON c.id=dc.comida.id WHERE c.nombre like concat ('%', :nombre, '%') AND dc.momentoDia=:momentoDia")
    Set<DietaComida> getComidaDietaByMomentoDiaYNombre(@Param("nombre") String nombre, @Param("momentoDia") Integer momentoDia);
}

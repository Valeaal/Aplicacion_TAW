package es.uma.proyectotaw.dao;

import es.uma.proyectotaw.entity.Comida;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ComidaRepository extends JpaRepository<Comida, Integer> {

    @Query("SELECT c FROM Comida c JOIN DietaComida dc ON c.id=dc.comida.id WHERE c.nombre like concat ('%', :nombre, '%') AND dc.momentoDia=:momentoDia")
    List<Comida> getComidasByMomentoDiaYNombre(@Param("nombre") String nombre, @Param("momentoDia") Integer momentoDia);
}

package es.uma.proyectotaw.dao;

import es.uma.proyectotaw.entity.Comida;
import es.uma.proyectotaw.entity.Dieta;
import es.uma.proyectotaw.entity.Rutina;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface DietaRepository extends JpaRepository<Dieta, Integer> {
    @Query("SELECT dc.comida FROM DietaComida dc WHERE dc.dieta.id = :dietaId")
    List<Comida> findComidasByDietaId(@Param("dietaId") Integer dietaId);
}

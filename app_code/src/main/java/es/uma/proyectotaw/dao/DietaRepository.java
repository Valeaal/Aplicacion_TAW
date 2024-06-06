package es.uma.proyectotaw.dao;

import es.uma.proyectotaw.entity.Comida;
import es.uma.proyectotaw.entity.Dieta;
import es.uma.proyectotaw.entity.DietaComida;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Set;

public interface DietaRepository extends JpaRepository<Dieta, Integer> {
    @Query("SELECT dc.comida FROM DietaComida dc WHERE dc.dieta.id = :dietaId")
    List<Comida> findComidasByDietaId(@Param("dietaId") Integer dietaId);

    @Query("SELECT cd FROM DietaComida cd WHERE cd.dieta.id=:dietaId")
    Set<DietaComida> getComidaDietaByDietaId(@Param("dietaId") Integer dietaId);
}

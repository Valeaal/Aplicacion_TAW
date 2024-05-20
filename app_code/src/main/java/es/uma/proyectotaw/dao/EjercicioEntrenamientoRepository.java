package es.uma.proyectotaw.dao;

import es.uma.proyectotaw.entity.EjercicioEntrenamiento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface EjercicioEntrenamientoRepository extends JpaRepository<EjercicioEntrenamiento, Integer> {
    @Query("select e from EjercicioEntrenamiento e where e.ejercicio.id= :ejercicioId")
    public EjercicioEntrenamiento getEjercicioEntrenamientoFromEjId(@Param("ejercicioId") Integer ejercicioId);
}

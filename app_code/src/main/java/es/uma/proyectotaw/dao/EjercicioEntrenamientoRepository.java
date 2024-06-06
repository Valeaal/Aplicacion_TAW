package es.uma.proyectotaw.dao;

import es.uma.proyectotaw.entity.EjercicioEntrenamiento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface EjercicioEntrenamientoRepository extends JpaRepository<EjercicioEntrenamiento, Integer> {
    @Query("SELECT e FROM EjercicioEntrenamiento e WHERE e.ejercicio.id=:ejercicioId AND e.entrenamiento.id=:entrenamientoId")
    EjercicioEntrenamiento getEjercicioEntrenamientoFromEjAndEntrenamientoId(Integer ejercicioId, Integer entrenamientoId);
}

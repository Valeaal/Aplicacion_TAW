package es.uma.proyectotaw.dao;

import es.uma.proyectotaw.entity.Ejercicio;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface EjercicioRepository extends JpaRepository <Ejercicio, Integer> {
    @Query("SELECT e.ejercicio FROM EjercicioEntrenamiento e WHERE e.entrenamiento.id = :entrenamientoId")
    List<Ejercicio> findEjerciciosByEntrenamientoId(@Param("entrenamientoId") Integer entrenamientoId);

    @Query("SELECT ee.peso FROM EjercicioEntrenamiento ee WHERE ee.ejercicio.id = :ejercicioId")
    Float findEjercicioPeso(@Param("ejercicioId") Integer ejercicioId);

    @Query("SELECT ee.series FROM EjercicioEntrenamiento ee WHERE ee.ejercicio.id = :ejercicioId")
    Integer findEjercicioSeries(@Param("ejercicioId") Integer ejercicioId);

    @Query("SELECT ee.repeticiones FROM EjercicioEntrenamiento ee WHERE ee.ejercicio.id = :ejercicioId")
    Integer findEjercicioRepeticiones(@Param("ejercicioId") Integer ejercicioId);

    @Query("SELECT ee.desempeno FROM EjercicioEntrenamiento ee WHERE ee.ejercicio.id = :ejercicioId")
    Integer findEjercicioDesempeno(@Param("ejercicioId") Integer ejercicioId);

}

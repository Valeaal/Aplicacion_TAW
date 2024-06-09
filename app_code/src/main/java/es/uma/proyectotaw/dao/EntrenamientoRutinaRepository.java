package es.uma.proyectotaw.dao;

import es.uma.proyectotaw.entity.EjercicioEntrenamiento;
import es.uma.proyectotaw.entity.EntrenamientoRutina;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface EntrenamientoRutinaRepository extends JpaRepository<EntrenamientoRutina, Integer> {

    @Query("SELECT e.diaSemana FROM EntrenamientoRutina e WHERE e.rutina.id=:rutinaId AND e.entrenamiento.id=:entrenamientoId")
    Integer getdiaSemanaFromRutinaAndEntrenamientoId(@Param("rutinaId") Integer rutinaId, @Param("entrenamientoId") Integer entrenamientoId);
}

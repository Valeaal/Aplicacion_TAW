package es.uma.proyectotaw.dao;

import es.uma.proyectotaw.entity.EjercicioEntrenamiento;
import es.uma.proyectotaw.entity.EntrenamientoRutina;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface EntrenamientoRutinaRepository extends JpaRepository<EntrenamientoRutina, Integer> {

    @Query("SELECT e.diaSemana FROM EntrenamientoRutina e WHERE e.rutina.id=:rutinaId AND e.entrenamiento.id=:entrenamientoId")
    Integer getdiaSemanaFromRutinaAndEntrenamientoId(@Param("rutinaId") Integer rutinaId, @Param("entrenamientoId") Integer entrenamientoId);

    @Query("select e from EntrenamientoRutina e where e.entrenamiento.id= :entrenamiento")
    public EntrenamientoRutina findByEntrenamientoID(@Param("entrenamiento") Integer entrenamientoid);

    @Query("SELECT e.id FROM EntrenamientoRutina e WHERE e.rutina.id =:id")
    public List<Integer> findByRutinaId(@Param("id") Integer id);
}

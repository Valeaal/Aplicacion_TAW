package es.uma.proyectotaw.dao;

import es.uma.proyectotaw.entity.Ejercicio;
import es.uma.proyectotaw.entity.Entrenamiento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface EntrenamientoRepository extends JpaRepository<Entrenamiento, Integer> {

    @Query ("SELECT er.entrenamiento FROM EntrenamientoRutina er WHERE er.rutina.id=:rutinaId")
    List<Entrenamiento> findByRutinaId(@Param("rutinaId") Integer rutinaId);

    @Query("select e from Entrenamiento e where e.nombre = :entrenamientoString")
    public Entrenamiento buscarPorString (@Param("entrenamientoString") String entrenamientoString);

}

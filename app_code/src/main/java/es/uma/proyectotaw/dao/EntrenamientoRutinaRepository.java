package es.uma.proyectotaw.dao;

import es.uma.proyectotaw.entity.Entrenamiento;
import es.uma.proyectotaw.entity.EntrenamientoRutina;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface EntrenamientoRutinaRepository extends JpaRepository<EntrenamientoRutina, Integer> {
    @Query("select er.entrenamiento from EntrenamientoRutina er where er.rutina.id = :id")
    public List<Entrenamiento> findByRutina(@Param("id") Integer id);
}

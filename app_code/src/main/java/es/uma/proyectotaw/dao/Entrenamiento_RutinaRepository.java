package es.uma.proyectotaw.dao;

import es.uma.proyectotaw.entity.EntrenamientoRutina;
import es.uma.proyectotaw.entity.TipoUsuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface Entrenamiento_RutinaRepository extends JpaRepository<EntrenamientoRutina, Integer> {
    @Query("select er from EntrenamientoRutina er where er.rutina.id = :idRutina")
    public List<EntrenamientoRutina> buscarEntrenamientosdeRutina(@Param("idRutina") int idRutina);
}

package es.uma.proyectotaw.dao;

import es.uma.proyectotaw.entity.Ejercicio;
import es.uma.proyectotaw.entity.Rutina;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface RutinaRepository extends JpaRepository<Rutina, Integer> {
    @Query("select r from Rutina r where r.tipoRutina.id = :tipo")
    public List<Rutina> findByTipo(@Param("tipo") Integer tipo);

    @Query("select r from Rutina r where r.nombre like concat('%',:nombre,'%') and  r.tipoRutina.id = :tipo")
    public List<Rutina> findByNombre(@Param("nombre") String nombre,@Param("tipo") Integer tipo);

    @Query("select r from Rutina r where r.nombre like concat('%',:nombre,'%') and size(r.entrenamientoRutinas) = :num and  r.tipoRutina.id = :tipo")
    public List<Rutina> findByNombreAndNumEntrenamientos(@Param("nombre") String nombre,@Param("num")Integer num,@Param("tipo") Integer tipo);
}

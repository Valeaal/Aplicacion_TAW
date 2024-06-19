package es.uma.proyectotaw.dao;

import es.uma.proyectotaw.entity.Rutina;
import es.uma.proyectotaw.entity.Usuario;
import org.springframework.cglib.core.Local;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.time.LocalDate;

public interface RutinaRepository extends JpaRepository<Rutina, Integer> {
    @Query("select r from Rutina r where r.tipoRutina.id = 2")
    List<Rutina> getCrossfitRutinas();

    @Query("select r from Rutina r where r.tipoRutina =:tipo order by r.id")
    public List<Rutina> findAllOrdered(@Param("tipo")Integer tipo);

    @Query("select r from Rutina r  where r.tipoRutina.id = 2 and r.nombre like concat('%', :nombre, '%')")
    List<Rutina> filtrarPorNombre(@Param("nombre") String nombre);

    @Query("SELECT r FROM Rutina r WHERE r.tipoRutina.id = 2 AND (SELECT COUNT(e) FROM r.entrenamientos e) = :n")
    List<Rutina> filtrarPornumEntrenamientos(@Param("n") Integer n);

    @Query("select r from Rutina r where r.tipoRutina.id = 2 and r.nombre like concat('%', :nombre, '%') and" +
            " (SELECT COUNT(e) FROM r.entrenamientos e) = :n")
    List<Rutina> filtrarPorNombreynEntrenamientos(@Param("nombre") String nombre,
                                                  @Param("n") Integer n);

    @Query("SELECT r FROM Rutina r JOIN r.clientes cr WHERE cr.cliente.id = :clienteId AND cr.vigente = true")
    List<Rutina> getActiveRutinasByClienteId(@Param("clienteId") Integer clienteId);

    @Query("SELECT r FROM Rutina r JOIN r.clientes cr WHERE cr.cliente.id = :clienteId")
    List<Rutina> getAllRutinasByClienteId(@Param("clienteId") Integer clienteId);

    @Query("SELECT r FROM Rutina r JOIN r.clientes cr WHERE cr.cliente.id =:clienteId AND r.nombre like concat('%', :nombre, '%')")
    List<Rutina> getRutinasByNameAndClientId(@Param("clienteId") Integer clienteId, @Param("nombre") String nombre);

    @Query("select r from Rutina r where r.tipoRutina.id = :tipo")
    public List<Rutina> findByTipo(@Param("tipo") Integer tipo);

    @Query("select r from Rutina r where r.nombre like concat('%',:nombre,'%') and  r.tipoRutina.id = :tipo and r.fechaCreacion>=:fecha")
    public List<Rutina> findByNombre(@Param("nombre") String nombre,@Param("tipo") Integer tipo, @Param("fecha")LocalDate fecha);

    @Query("select r from Rutina r where r.nombre like concat('%',:nombre,'%') and size(r.entrenamientos) = :num and r.tipoRutina.id = :tipo and r.fechaCreacion>=:fecha")
    public List<Rutina> findByNombreAndEntrenos(@Param("nombre") String nombre,@Param("num")Integer num,@Param("tipo") Integer tipo, @Param("fecha")LocalDate fecha);

    @Query("select r from Rutina r where r.nombre like concat('%',:nombre,'%') and size(r.entrenamientos) = :num and r.tipoRutina.id = :tipo and r.entrenador.id =:creador and r.fechaCreacion>=:fecha")
    public List<Rutina> findByNombreEntrenosAndCreador(@Param("nombre") String nombre,@Param("num")Integer num,@Param("creador")Integer usuario,@Param("tipo") Integer tipo, @Param("fecha")LocalDate fecha);

    @Query("select r from Rutina r where r.nombre like concat('%',:nombre,'%') and r.tipoRutina.id = :tipo and r.entrenador.id =:creador and r.fechaCreacion>=:fecha")
    public List<Rutina> findByNombreAndCreador(@Param("nombre") String nombre, @Param("creador")Integer usuario, @Param("tipo") Integer tipo, @Param("fecha")LocalDate fecha);



    /*@Query("SELECT R.id, R.nombre, AVG(Performance.porcentaje) AS AveragePerformance" +
            "FROM Rutina R" +
            "JOIN ENTRENAMIENTO E ON R.id = E.rutina_id" +
            "JOIN (" +
            "    SELECT EE.entrenamiento_id," +
            "           100.0 * SUM(CASE WHEN EE.completado THEN 1 ELSE 0 END) / COUNT(*) AS porcentaje" +
            "    FROM EJERCICIO_ENTRENAMIENTO EE" +
            "    GROUP BY EE.entrenamiento_id" +
            ") AS Performance ON E.id = Performance.entrenamiento_id" +
            "GROUP BY R.id, R.nombre" +
            "HAVING AVG(Performance.porcentaje) = :porcentaje")
    List<Rutina> getRutinasByDesempenyo(@Param("porcentaje") Integer porcentaje);*/

}

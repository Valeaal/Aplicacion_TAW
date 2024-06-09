package es.uma.proyectotaw.dao;

import es.uma.proyectotaw.entity.Rutina;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface RutinaRepository extends JpaRepository<Rutina, Integer> {
    @Query("select r from Rutina r where r.tipoRutina.id = 2")
    List<Rutina> getCrossfitRutinas();

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

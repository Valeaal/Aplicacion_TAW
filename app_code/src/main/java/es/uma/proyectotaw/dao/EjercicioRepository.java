package es.uma.proyectotaw.dao;

import es.uma.proyectotaw.entity.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface EjercicioRepository extends JpaRepository <Ejercicio, Integer> {
    @Query("SELECT e.ejercicio FROM EjercicioEntrenamiento e WHERE e.entrenamiento.id = :entrenamientoId")
    List<Ejercicio> findEjerciciosByEntrenamientoId(@Param("entrenamientoId") Integer entrenamientoId);

    @Query("SELECT ee.peso FROM EjercicioEntrenamiento ee WHERE ee.ejercicio.id = :ejercicioId AND ee.entrenamiento.id =:entrenamientoId")
    Float findEjercicioPeso(@Param("ejercicioId") Integer ejercicioId, @Param("entrenamientoId") Integer entrenamientoId);

    @Query("SELECT ee.series FROM EjercicioEntrenamiento ee WHERE ee.ejercicio.id = :ejercicioId AND ee.entrenamiento.id =:entrenamientoId")
    Integer findEjercicioSeries(@Param("ejercicioId") Integer ejercicioId, @Param("entrenamientoId") Integer entrenamientoId);

    @Query("SELECT ee.repeticiones FROM EjercicioEntrenamiento ee WHERE ee.ejercicio.id = :ejercicioId AND ee.entrenamiento.id =:entrenamientoId")
    Integer findEjercicioRepeticiones(@Param("ejercicioId") Integer ejercicioId, @Param("entrenamientoId") Integer entrenamientoId);

    @Query("SELECT ee.id FROM EjercicioEntrenamiento ee WHERE ee.ejercicio.id = :ejercicioId AND ee.entrenamiento.id =:entrenamientoId")
    Integer findId(@Param("ejercicioId") Integer ejercicioId, @Param("entrenamientoId") Integer entrenamientoId);

    @Query("SELECT ee.desempeno FROM EjercicioEntrenamiento ee WHERE ee.ejercicio.id = :ejercicioId")
    Integer findEjercicioDesempeno(@Param("ejercicioId") Integer ejercicioId);

    @Query("SELECT e FROM Ejercicio e " +
            "JOIN e.entrenamientos ee " +
            "JOIN ee.entrenamiento et " +
            "JOIN et.rutinas etr " +
            "JOIN etr.rutina r " +
            "JOIN r.clientes cr " +
            "WHERE cr.cliente.id = :clienteId " +
            "AND (:nombre IS NULL OR e.nombre LIKE CONCAT('%', :nombre, '%')) " +
            "AND (:parteId IS NULL OR e.grupoMuscular.id = :parteId) " +
            "AND (:peso IS NULL OR ee.peso = :peso)")
    List<Ejercicio> getEjercicioByClienteIdFiltrado(@Param("clienteId") Integer clienteId, @Param("nombre") String nombre,
                                               @Param("peso") Integer peso, @Param("parteId") Integer parteId);

    //Gracias al uso del "or is null" hacemos que los parámetros pasados nulos no afecten a la búsqueda, y así no hay que crear
    //una consulta de búsqueda por cada combinación posible de parámetros
    @Query("select e from Ejercicio e where (e.nombre like concat('%', :inputNombre, '%'))" +
            "and ((:inputTipo is null) or e.tipo = :inputTipo)" +
            "and ((:inputGrupo is null) or e.grupoMuscular = :inputGrupo)")
    public List<Ejercicio> filtrarEjercicios (@Param("inputNombre") String inputNombre,
                                              @Param("inputTipo") TipoEjercicio inputTipo,
                                              @Param("inputGrupo") GrupoMuscular inputGrupo);

    @Query("select e from Ejercicio e where e.nombre = :ejercicioString")
    public Ejercicio buscarPorString (@Param("ejercicioString") String ejercicioString);

    @Query("select e from Ejercicio e where e.nombre like concat('%',:nombre,'%')")
    public List<Ejercicio> findByNombre(@Param("nombre") String nombre);

    /*List<Ejercicio> getEjercicioByClientIdDesempenyo(@Param("clienteId") Integer clienteId, @Param("desempenyoSup") Integer desempenyoLimiteSup,
                                                      @Param("desempenyoInf") Integer desempenyoLimiteInf);*/
}

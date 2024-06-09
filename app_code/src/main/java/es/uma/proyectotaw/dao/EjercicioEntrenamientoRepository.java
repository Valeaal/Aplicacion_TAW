package es.uma.proyectotaw.dao;

import es.uma.proyectotaw.entity.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface EjercicioEntrenamientoRepository extends JpaRepository<EjercicioEntrenamiento, Integer> {
    @Query("select e from EjercicioEntrenamiento e where e.ejercicio.id= :ejercicioId")
    public EjercicioEntrenamiento getEjercicioEntrenamientoFromEjId(@Param("ejercicioId") Integer ejercicioId);

    //Gracias al uso del "or is null" hacemos que los parámetros pasados nulos no afecten a la búsqueda, y así no hay que crear
    //una consulta de búsqueda por cada combinación posible de parámetros
    @Query("select e from EjercicioEntrenamiento e where ((:inputEjercicio is null) or e.ejercicio = :inputEjercicio)" +
            "and ((:inputEntrenamiento is null) or e.entrenamiento = :inputEntrenamiento)" +
            "and ((:inputSeries is null) or e.series = :inputSeries)" +
            "and ((:inputRepeticiones is null) or e.repeticiones = :inputRepeticiones)" +
            "and ((:inputPeso is null) or e.peso = :inputPeso)" +
            "and ((:inputTiempo is null) or e.tiempo = :inputTiempo)" +
            "and ((:inputDistancia is null) or e.distancia = :inputDistancia)" +
            "and ((:inputOrden is null) or e.orden = :inputOrden)")
    public List<EjercicioEntrenamiento> filtrarEjerciciosEntrenamiento (@Param("inputEjercicio") Ejercicio inputEjercicio,
                                          @Param("inputEntrenamiento") Entrenamiento inputEntrenamiento,
                                          @Param("inputSeries") Integer inputSeries,
                                          @Param("inputRepeticiones") Integer inputRepeticiones,
                                          @Param("inputPeso") Integer inputPeso,
                                          @Param("inputTiempo") Integer inputTiempo,
                                          @Param("inputDistancia") Integer inputDistancia,
                                          @Param("inputOrden") Integer inputOrden);
}

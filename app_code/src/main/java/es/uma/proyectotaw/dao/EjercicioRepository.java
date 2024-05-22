package es.uma.proyectotaw.dao;

import es.uma.proyectotaw.entity.Ejercicio;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface EjercicioRepository extends JpaRepository <Ejercicio, Integer> {
    @Query("select e from Ejercicio e where e.nombre like concat('%',:nombre,'%')")
    public List<Ejercicio> findByNombre(@Param("nombre") String nombre);
}

//Autor: Álvaro Valencia Villalón
package es.uma.proyectotaw.dao;

import es.uma.proyectotaw.entity.GrupoMuscular;
import es.uma.proyectotaw.entity.TipoEjercicio;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface TipoEjercicioRepository extends JpaRepository<TipoEjercicio, Integer> {

    @Query("select t.id from TipoEjercicio t where t.tipo = :inputString")
    public Integer buscarPorString (@Param("inputString") String inputString);

}

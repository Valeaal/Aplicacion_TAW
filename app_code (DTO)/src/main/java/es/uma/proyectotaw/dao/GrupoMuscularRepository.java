package es.uma.proyectotaw.dao;

import es.uma.proyectotaw.entity.GrupoMuscular;
import es.uma.proyectotaw.entity.TipoUsuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface GrupoMuscularRepository extends JpaRepository<GrupoMuscular, Integer> {

    @Query("select g from GrupoMuscular g where g.grupo = :inputString")
    public GrupoMuscular buscarPorString (@Param("inputString") String inputString);
}

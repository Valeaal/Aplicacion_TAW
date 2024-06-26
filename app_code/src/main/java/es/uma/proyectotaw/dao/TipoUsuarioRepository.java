package es.uma.proyectotaw.dao;

import es.uma.proyectotaw.entity.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface TipoUsuarioRepository extends JpaRepository<TipoUsuario, Integer> {

    @Query("select t from TipoUsuario t where t.id = :tipoID")
    public TipoUsuario buscarPorID (@Param("tipoID") int tipoID);

    @Query("select t from TipoUsuario t where t.tipo = :tipoString")
    public TipoUsuario buscarPorString (@Param("tipoString") String tipoString);

}

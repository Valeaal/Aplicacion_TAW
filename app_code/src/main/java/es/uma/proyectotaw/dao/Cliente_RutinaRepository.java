package es.uma.proyectotaw.dao;

import es.uma.proyectotaw.entity.ClienteRutina;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface Cliente_RutinaRepository extends JpaRepository<ClienteRutina, Integer> {
    @Query("select cr from ClienteRutina cr where cr.cliente.id = :idCliente and cr.vigente = true")
    public List<ClienteRutina> findActiveRoutines(@Param("idCliente") Integer idCliente);

    @Query("select cr from ClienteRutina cr where cr.cliente.id = :idCliente")
    public List<ClienteRutina> historialRutinasCliente(@Param("idCliente") Integer idCliente);
}

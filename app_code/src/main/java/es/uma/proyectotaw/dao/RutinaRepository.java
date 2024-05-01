package es.uma.proyectotaw.dao;

import es.uma.proyectotaw.entity.Rutina;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface RutinaRepository extends JpaRepository<Rutina, Integer> {
    @Query("SELECT r FROM Rutina r JOIN r.clientes cr WHERE cr.cliente.id = :clienteId AND cr.vigente = true")
    List<Rutina> getActiveRutinasByClienteId(@Param("clienteId") Integer clienteId);
}

package es.uma.proyectotaw.dao;

import es.uma.proyectotaw.entity.ClienteRutina;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface Cliente_RutinaRepository extends JpaRepository<ClienteRutina, Integer> {
    @Query("select cr from ClienteRutina cr where cr.vigente = true")
    public List<ClienteRutina> findActiveRoutines();
}

package es.uma.proyectotaw.dao;

import es.uma.proyectotaw.entity.ClienteRutina;
import es.uma.proyectotaw.entity.Rutina;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface Cliente_RutinaRepository extends JpaRepository<ClienteRutina, Integer> {

}

package es.uma.proyectotaw.dao;

import es.uma.proyectotaw.entity.ClienteRutinaId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClienteRutinaRepository extends JpaRepository<ClienteRutinaId, Integer> {
}

package es.uma.proyectotaw.dao;

import es.uma.proyectotaw.entity.Comida;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ComidaRepository extends JpaRepository<Comida, Integer> {
}

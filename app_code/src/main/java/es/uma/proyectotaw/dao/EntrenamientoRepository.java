package es.uma.proyectotaw.dao;

import es.uma.proyectotaw.entity.Entrenamiento;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EntrenamientoRepository extends JpaRepository<Entrenamiento, Integer> {
}

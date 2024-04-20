package es.uma.proyectotaw.dao;

import es.uma.proyectotaw.entity.EjercicioEntrenamientoId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EjercicioEntrenamientoRepository extends JpaRepository<EjercicioEntrenamientoId, Integer> {
}

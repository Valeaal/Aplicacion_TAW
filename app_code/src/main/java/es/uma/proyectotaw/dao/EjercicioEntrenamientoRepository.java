package es.uma.proyectotaw.dao;

import es.uma.proyectotaw.entity.EjercicioEntrenamiento;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EjercicioEntrenamientoRepository extends JpaRepository<EjercicioEntrenamiento, Integer> {
}

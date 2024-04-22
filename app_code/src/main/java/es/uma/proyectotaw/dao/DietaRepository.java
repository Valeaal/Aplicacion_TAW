package es.uma.proyectotaw.dao;

import es.uma.proyectotaw.entity.Dieta;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DietaRepository extends JpaRepository<Dieta, Integer> {
}

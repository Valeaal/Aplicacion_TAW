package es.uma.proyectotaw.dao;

import es.uma.proyectotaw.entity.DietaComida;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DietaComidaIdRepository extends JpaRepository<DietaComida, Integer> {
}

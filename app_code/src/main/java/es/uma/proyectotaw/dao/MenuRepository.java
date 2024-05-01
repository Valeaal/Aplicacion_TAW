package es.uma.proyectotaw.dao;

import es.uma.proyectotaw.entity.Menu;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MenuRepository extends JpaRepository<Menu, Integer> {
}

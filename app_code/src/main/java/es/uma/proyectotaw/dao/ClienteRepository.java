package es.uma.proyectotaw.dao;

import es.uma.proyectotaw.entity.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClienteRepository extends JpaRepository<Cliente, Integer> {

}

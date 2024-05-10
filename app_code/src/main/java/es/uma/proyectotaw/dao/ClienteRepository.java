package es.uma.proyectotaw.dao;

import es.uma.proyectotaw.entity.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ClienteRepository extends JpaRepository<Cliente, Integer> {

    @Query("select c from Cliente c where c.dieta != null")
    public List<Cliente> clientesConDietas();
}

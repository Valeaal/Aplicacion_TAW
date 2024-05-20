package es.uma.proyectotaw.dao;

import es.uma.proyectotaw.entity.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
<<<<<<< HEAD

import java.util.List;


public interface ClienteRepository extends JpaRepository<Cliente, Integer> {
    @Query("select u from Cliente u where u.usuario.id = :userId")
    public Cliente getClienteByUserId(Integer userId);
}

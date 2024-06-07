package es.uma.proyectotaw.dao;

import es.uma.proyectotaw.entity.Cliente;
import es.uma.proyectotaw.entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;


public interface ClienteRepository extends JpaRepository<Cliente, Integer> {
    @Query("select u from Cliente u where u.usuario.id = :userId")
    public Cliente getClienteByUserId(Integer userId);

    @Query("select u from Cliente  u where u.dieta IS NULL")
    public List<Cliente> getClientesSinDieta();

    @Query("select u from Cliente  u where u.dieta IS NOT NULL")
    public List<Cliente> getClientesConDieta();

    @Query("select u from Cliente u where u.id = :userId")
    public Cliente getClienteById(Integer userId);


    @Query("SELECT u FROM Cliente u " +
            "WHERE (:edad IS NULL OR u.edad = :edad) " +
            "AND (:nombre IS NULL OR LOWER(u.usuario.nombre) LIKE LOWER(CONCAT('%', :nombre, '%'))) " +
            "AND (:dieta IS NULL OR LOWER(u.dieta.nombre) LIKE LOWER(CONCAT('%', :dieta, '%')))")
    List<Cliente> getClienteFiltrado(Integer edad, String nombre, String dieta);

}



//Autor: Álvaro Valencia Villalón 20%
package es.uma.proyectotaw.dao;

import es.uma.proyectotaw.entity.Desempeno;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface DesempenoRepository extends JpaRepository<Desempeno, Integer> {
    @Query("select d from Desempeno d where d.cliente.id = :idCliente")
    List<Desempeno> desempenoDelCliente(@Param("idCliente") Integer idCliente);


    @Query("SELECT d.desempeno FROM EjercicioEntrenamiento d WHERE d.entrenamiento.id=:entrenamientoId AND d.ejercicio.id=:ejId")
    Desempeno getDesempenoByEntremanientoAndEjId(@Param("ejId") Integer ejId, @Param("entrenamientoId") Integer entrenamientoId);

    @Query("SELECT d.desempeno FROM ComidaMenu d WHERE d.menu.id=:menuId AND d.comida.id=:comidaId")
    Desempeno getDesempenoByMenuAndComidaId(@Param("menuId") Integer menuId, @Param("comidaId") Integer comidaId);

    @Query("SELECT d from Desempeno d where d.cliente.id = :clienteId")
    List<Desempeno> getDesempenoPorCliente (@Param("clienteId") Integer clienteId);

}

package es.uma.proyectotaw.dao;

import es.uma.proyectotaw.entity.Comida;
import es.uma.proyectotaw.entity.Dieta;
import es.uma.proyectotaw.entity.DietaComida;
import es.uma.proyectotaw.entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Set;

public interface DietaRepository extends JpaRepository<Dieta, Integer> {
    @Query("SELECT dc.comida FROM DietaComida dc WHERE dc.dieta.id = :dietaId")
    List<Comida> findComidasByDietaId(@Param("dietaId") Integer dietaId);

    @Query("SELECT cd FROM DietaComida cd WHERE cd.dieta.id=:dietaId")
    Set<DietaComida> getComidaDietaByDietaId(@Param("dietaId") Integer dietaId);

    @Query("select u from Dieta u where u.id = :inputID ")
    public Dieta buscarPorID (@Param("inputID") Integer inputID);

    @Query("SELECT d FROM Dieta d " +
            "WHERE (:nombre IS NULL OR LOWER(d.nombre) LIKE LOWER(CONCAT('%', :nombre, '%'))) " +
            "AND (:calorias IS NULL OR " +
            "(d.calorias < 500 AND :calorias = 1) OR " +
            "(d.calorias >= 500 AND d.calorias < 1000 AND :calorias = 2) OR " +
            "(d.calorias >= 1000 AND d.calorias < 1500 AND :calorias = 3) OR " +
            "(d.calorias >= 1500 AND d.calorias < 2000 AND :calorias = 4) OR " +
            "(d.calorias >= 2000 AND d.calorias < 2500 AND :calorias = 5) OR " +
            "(d.calorias >= 2500 AND d.calorias < 3000 AND :calorias = 6) OR " +
            "(d.calorias >= 3000 AND :calorias = 7))")
    List<Dieta> getDietasFiltradas(String nombre, Integer calorias);

    @Query("SELECT d.dieta FROM Cliente d WHERE d.id=:id")
    List<Dieta> getDietaByClientId(Integer id);



}

package es.uma.proyectotaw.dao;

import es.uma.proyectotaw.entity.DietaComida;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface Dieta_ComidaRepository extends JpaRepository<DietaComida,Integer> {


}

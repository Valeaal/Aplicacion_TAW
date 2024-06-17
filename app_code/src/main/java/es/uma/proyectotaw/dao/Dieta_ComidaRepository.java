package es.uma.proyectotaw.dao;

import es.uma.proyectotaw.entity.Dieta;
import es.uma.proyectotaw.entity.DietaComida;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface Dieta_ComidaRepository extends JpaRepository<DietaComida,Integer> {
    @Query("select dc from DietaComida dc where dc.dieta = :dieta and dc.dia = :dia and dc.momentoDia = :momento")
    public DietaComida buscarComidaEnDieta(@Param("dieta")Dieta dieta,
                                           @Param("dia")Integer dia,
                                           @Param("momento") Integer moomento);
}

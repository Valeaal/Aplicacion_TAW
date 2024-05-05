package es.uma.proyectotaw.dao;

import es.uma.proyectotaw.entity.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface UsuarioRepository extends JpaRepository<Usuario, Integer> {

    @Query("select u from Usuario u")
    public List<Usuario> sacarUsuarios();

    @Query("select u from Usuario u where u.email = :inputEmail ")
    public Usuario buscarPorEmail (@Param("inputEmail") String inputEmail);

    @Query("select u from Usuario u where u.tipoUsuario = :inputTipo")
    public List<Usuario> buscarPorTipo(@Param("inputTipo") TipoUsuario inputTipo);

    @Query("select YEAR(current_date) - YEAR(u.fechaNacimiento) as edad from Usuario u group by edad order by 1 desc")
    public List<Integer> sacarEdades();

    @Query("select YEAR(u.perteneceDesde) as ingreso from Usuario u group by ingreso order by 1 asc")
    public List<Integer> sacarIngresos();

    //Gracias al uso del "or is null" hacemos que los parámetros pasados nulos no afecten a la búsqueda, y así no hay que crear
    //una consulta de búsqueda por cada combinación posible de parámetros
    @Query("select u from Usuario u where (u.nombre like concat('%', :inputNombre, '%'))" +
            "and (u.apellidos like concat('%', :inputApellidos, '%'))" +
            "and ((YEAR(current_date) - YEAR(u.fechaNacimiento) = :inputEdad) or (:inputEdad  is null)) " +
            "and ((:inputIngreso is null) or :inputIngreso = YEAR(u.perteneceDesde))" +
            "and ((:inputRol is null) or u.tipoUsuario = :inputRol)")
    public List<Usuario> filtrarUsuarios (@Param("inputNombre") String inputNombre,
                                          @Param("inputApellidos") String inputApellidos,
                                          @Param("inputEdad") Integer inputEdad,
                                          @Param("inputIngreso") Integer inputIngreso,
                                          @Param("inputRol") TipoUsuario inputRol);

}

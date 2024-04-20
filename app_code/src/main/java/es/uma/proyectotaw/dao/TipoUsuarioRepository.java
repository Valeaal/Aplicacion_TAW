package es.uma.proyectotaw.dao;

import es.uma.proyectotaw.entity.TipoUsuario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TipoUsuarioRepository extends JpaRepository<TipoUsuario, Integer> {
}

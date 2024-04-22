package es.uma.proyectotaw.dao;

import es.uma.proyectotaw.entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioRepository extends JpaRepository<Usuario, Integer> {
}

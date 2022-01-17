package silveira.caio.domain.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import silveira.caio.domain.entity.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

	
	Optional<Usuario> findByLogin(String login);
}

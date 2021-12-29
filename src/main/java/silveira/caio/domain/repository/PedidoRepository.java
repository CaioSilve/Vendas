package silveira.caio.domain.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import silveira.caio.domain.entity.Pedido;

public interface PedidoRepository extends JpaRepository<Pedido, Long> {

	@Query("select p from Pedido p left join fetch p.itens where p.id = :id")
	Optional<Pedido> findByIdFetchItens(@Param("id") Long id);
}

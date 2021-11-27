package silveira.caio.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import silveira.caio.domain.entity.Pedido;

public interface PedidoRepository extends JpaRepository<Pedido, Long> {

}

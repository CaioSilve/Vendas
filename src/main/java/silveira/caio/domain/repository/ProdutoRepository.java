package silveira.caio.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import silveira.caio.domain.entity.Produto;

public interface ProdutoRepository extends JpaRepository<Produto, Long> {

}

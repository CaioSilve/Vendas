package silveira.caio.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import silveira.caio.domain.entity.Cliente;

public interface ClienteRepository extends JpaRepository<Cliente, Long>{

}

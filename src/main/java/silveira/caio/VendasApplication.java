package silveira.caio;

import java.time.OffsetDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import silveira.caio.domain.entity.Cliente;
import silveira.caio.domain.entity.ItemPedido;
import silveira.caio.domain.entity.Pedido;
import silveira.caio.domain.entity.Produto;
import silveira.caio.domain.repository.ClienteRepository;
import silveira.caio.domain.repository.PedidoRepository;
import silveira.caio.domain.repository.ProdutoRepository;

@SpringBootApplication
public class VendasApplication implements CommandLineRunner {

	@Autowired
	private ClienteRepository repoClie;
	
	@Autowired
	private ProdutoRepository repoProd;
	
	@Autowired
	private PedidoRepository repoPedi;
	
	public static void main(String[] args) {
		SpringApplication.run(VendasApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		Cliente clie = new Cliente("Geovana");
		
		repoClie.save(new Cliente("Marcos"));
		repoClie.save(clie);
		
		
		Produto prod = new Produto(null, "Mouse Gamer", 250.00);
		
		repoProd.save(prod);
		
		Pedido pedi = new Pedido(clie, OffsetDateTime.now());
		
		repoPedi.save(pedi);
		
		ItemPedido item = new ItemPedido(null, pedi, prod, 2);
		
		pedi.getItens().add(item);
		
		pedi.setTotal();
		
		repoPedi.save(pedi);
		
		
		
	}

}

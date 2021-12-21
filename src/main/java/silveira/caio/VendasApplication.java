package silveira.caio;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import silveira.caio.domain.entity.Cliente;
import silveira.caio.domain.entity.Produto;
import silveira.caio.domain.repository.ClienteRepository;
import silveira.caio.domain.repository.ProdutoRepository;

@SpringBootApplication
public class VendasApplication implements CommandLineRunner {
	
	
	@Autowired
	private ClienteRepository repoClie;
	
	@Autowired
	private ProdutoRepository repoProd;

	public static void main(String[] args) {
		SpringApplication.run(VendasApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		
		Cliente clie = new Cliente("Jorge", "14523658965");
		
		repoClie.save(clie);
		
		Produto prod = new Produto(null, "Mouse Gamer", 250.0);
		Produto prod1 = new Produto(null, "Teclado", 50.0);
		
		repoProd.saveAll(Arrays.asList(prod, prod1));
		
		
		
		
		
		
	}

}

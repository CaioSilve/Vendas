package silveira.caio.api.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import silveira.caio.domain.entity.Produto;
import silveira.caio.domain.repository.ProdutoRepository;

@RestController
@RequestMapping("/produtos")
public class ProdutoController {
	
	@Autowired
	ProdutoRepository repo;
	
	
	@GetMapping
	public List<Produto> getProdutos(@RequestBody Produto filtro){
		ExampleMatcher matcher = ExampleMatcher.matching().withIgnoreCase();
		Example<Produto> example = Example.of(filtro, matcher);
		
		return repo.findAll(example);
	}

	
	@GetMapping("/{id}")
	public Produto getProduto(@PathVariable Long id) {
		return repo.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Produto não encontrado"));
	}
	
	
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public Produto saveProduto(@Valid @RequestBody Produto prod) {
		return repo.save(prod);
	}
	
	@PutMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void updateProduto(@Valid @RequestBody Produto prod, @PathVariable Long id){
		repo.findById(id).map(ex -> {
			prod.setId(ex.getId());
			repo.save(prod);
			return prod;
		}).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Produto não encontrado"));
	}
	
	
	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void deleteProduto(@PathVariable Long id){
		repo.findById(id).map(prod -> {
			repo.delete(prod);
			return prod;
		}).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Produto não encontrado"));
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}

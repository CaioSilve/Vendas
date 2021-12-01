package silveira.caio.api.controller;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.ExampleMatcher.StringMatcher;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import silveira.caio.domain.entity.Cliente;
import silveira.caio.domain.repository.ClienteRepository;

@RestController
@RequestMapping("/clientes")
public class ClienteController {

	@Autowired
	private ClienteRepository repo;
	
	@GetMapping
	public ResponseEntity<List<Cliente>> listarClientes(@RequestBody Cliente filtro){
		ExampleMatcher matcher = ExampleMatcher.matching().withIgnoreCase().withStringMatcher(StringMatcher.CONTAINING);
		Example<Cliente> example = Example.of(filtro, matcher);
		
		List<Cliente> resposta = repo.findAll(example);
		
		return ResponseEntity.ok(resposta);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Cliente> getCliente(@PathVariable("id") Long clienteId){
		Optional<Cliente> opt = repo.findById(clienteId);
		
		return opt.isEmpty() ? ResponseEntity.notFound().build() : ResponseEntity.ok(opt.get());
	}
	
	
	@PostMapping
	public ResponseEntity<Cliente> saveCliente(@Valid @RequestBody Cliente cliente){
		return new ResponseEntity<Cliente>(repo.save(cliente), HttpStatus.CREATED);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Object> updateCliente(@Valid @RequestBody Cliente cliente, @PathVariable Long id){		
		return repo.findById(id).map(ex ->{
			cliente.setId(ex.getId());
			repo.save(cliente);
			return ResponseEntity.noContent().build();
		}).orElseGet(() -> ResponseEntity.notFound().build());
	}
	
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Cliente> deleteCliente(@PathVariable Long id){
		Optional<Cliente> opt = repo.findById(id);
		
		if (opt.isEmpty()) return ResponseEntity.notFound().build();

		repo.deleteById(id);
		
		return ResponseEntity.noContent().build();
	}
	
	
	
}

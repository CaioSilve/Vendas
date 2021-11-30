package silveira.caio.api.controller;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
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
	public ResponseEntity<List<Cliente>> listarClientes(){
		List<Cliente> resposta = repo.findAll();
		
		if(resposta.isEmpty()) {
			return ResponseEntity.notFound().build();
		}
		
		
		return ResponseEntity.ok(resposta);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Cliente> getCliente(@PathVariable("id") Long clienteId){
		Optional<Cliente> opt = repo.findById(clienteId);
		
		return opt.isEmpty() ? ResponseEntity.notFound().build() : ResponseEntity.ok(opt.get());
	}
	
	@PostMapping
	public ResponseEntity<Cliente> saveCliente(@Valid @RequestBody Cliente cliente){
		return ResponseEntity.ok(repo.save(cliente));
	}
	
	
	
}

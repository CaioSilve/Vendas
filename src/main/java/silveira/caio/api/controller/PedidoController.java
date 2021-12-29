package silveira.caio.api.controller;

import static org.springframework.http.HttpStatus.CREATED;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import silveira.caio.api.mapper.PedidoMapper;
import silveira.caio.api.model.PedidoResponse;
import silveira.caio.api.model.input.PedidoInput;
import silveira.caio.api.model.input.UpdatePedidoInput;
import silveira.caio.domain.entity.Pedido;
import silveira.caio.domain.entity.enums.StatusPedido;
import silveira.caio.domain.service.PedidoService;

@RestController
@RequestMapping("/pedidos")
public class PedidoController {

	
	@Autowired
	PedidoService serv;
	
	@Autowired
	private PedidoMapper mapper;
	
	
	@GetMapping
	public List<PedidoResponse> getPedidos(Pedido filtro){
		return mapper.toCollectionResponse(serv.findAll(filtro));
	}
	
	@GetMapping("/{id}")
	public PedidoResponse getPedido(@PathVariable("id") Long id) {
		return mapper.toResponse(serv.findById(id));
	}
	
	@PostMapping
	@ResponseStatus(CREATED)
	public Long savePedido( @Valid @RequestBody PedidoInput pedido) {
		return serv.salvar(pedido).getId();
	}
	
	@PatchMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void updatePedido(@PathVariable Long id, @RequestBody @Valid UpdatePedidoInput status) {
		serv.atualizaStatus(id, StatusPedido.valueOf(status.getStatus()));
	}
}

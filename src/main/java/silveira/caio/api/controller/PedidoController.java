package silveira.caio.api.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import silveira.caio.api.model.input.PedidoInput;
import silveira.caio.domain.entity.Pedido;
import silveira.caio.domain.service.PedidoService;

import static org.springframework.http.HttpStatus.*;

@RestController
@RequestMapping("/pedidos")
public class PedidoController {

	
	@Autowired
	PedidoService serv;
	
	
	@GetMapping
	public List<Pedido> getPedidos(Pedido filtro){
		return serv.findAll(filtro);
	}
	
	@PostMapping
	@ResponseStatus(CREATED)
	public Long savePedido( @Valid @RequestBody PedidoInput pedido) {
		Pedido pedi = serv.salvar(pedido);
		return pedi.getId();
	}
}

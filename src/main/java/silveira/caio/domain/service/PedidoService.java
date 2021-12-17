package silveira.caio.domain.service;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.Set;

import org.hibernate.service.spi.ServiceException;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.ExampleMatcher.StringMatcher;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import silveira.caio.api.mapper.ItemPedidoMapper;
import silveira.caio.api.mapper.PedidoMapper;
import silveira.caio.api.model.input.ItemPedidoInput;
import silveira.caio.api.model.input.PedidoInput;
import silveira.caio.domain.entity.ItemPedido;
import silveira.caio.domain.entity.Pedido;
import silveira.caio.domain.repository.ClienteRepository;
import silveira.caio.domain.repository.PedidoRepository;

@Service
@RequiredArgsConstructor
public class PedidoService {

	private final PedidoRepository repo;
	
	private final ClienteRepository repoClie;
	
	private final PedidoMapper mapper;
	
	private final ItemPedidoMapper mapperItens;
	
	public Pedido salvar(PedidoInput pedido) {
		Pedido pedi = mapper.toEntity(pedido);
		pedi.setCliente(repoClie.findById(pedido.getIdCliente()).orElseThrow(
				() -> new ServiceException("Cliente não encontrado")));
		pedi.setDataPedido(OffsetDateTime.now());
		pedi.setTotal();
		pedi.setItens(salvarItens(pedido.getItens()));
		
		return pedi;
	}
	
	private Set<ItemPedido> salvarItens(List<ItemPedidoInput> itens) {
		if(itens.isEmpty()) throw new ServiceException("Favor adicionar itens ao pedido");
		return mapperItens.toCollectionEntity(itens);
	}

	public List<Pedido> findAll(Pedido filtro) {
		ExampleMatcher matcher = ExampleMatcher.matching().withIgnoreCase().withStringMatcher(StringMatcher.CONTAINING);
		Example<Pedido> exam = Example.of(filtro, matcher);
		
		return repo.findAll(exam); 
	}
	
	
	
	
	
	
	
}

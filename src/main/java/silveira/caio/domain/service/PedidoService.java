package silveira.caio.domain.service;

import java.util.List;

import org.hibernate.service.spi.ServiceException;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.ExampleMatcher.StringMatcher;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;
import silveira.caio.api.mapper.ItemPedidoMapper;
import silveira.caio.api.mapper.PedidoMapper;
import silveira.caio.api.model.input.ItemPedidoInput;
import silveira.caio.api.model.input.PedidoInput;
import silveira.caio.domain.entity.ItemPedido;
import silveira.caio.domain.entity.Pedido;
import silveira.caio.domain.repository.ItemPedidoRepository;
import silveira.caio.domain.repository.PedidoRepository;

@Service
@RequiredArgsConstructor
public class PedidoService {

	private final PedidoRepository repo;
	
	private final ItemPedidoRepository repoItemPedido;
	
	private final PedidoMapper mapper;
	
	private final ItemPedidoMapper mapperItens;
	
	
	
	public List<Pedido> findAll(Pedido filtro) {
		ExampleMatcher matcher = ExampleMatcher.matching().withIgnoreCase().withStringMatcher(StringMatcher.CONTAINING);
		Example<Pedido> exam = Example.of(filtro, matcher);
		
		return repo.findAll(exam); 
	}
	
	
	@Transactional
	public Pedido salvar(PedidoInput pedido) {
		Pedido pedi = mapper.toEntity(pedido);
		List<ItemPedido> itens = salvarItens(pedi, pedido.getItens());
		
		pedi.setTotal(itens.stream().mapToDouble((v) -> v.getProduto().getPreco() * v.getQtde()).sum());
		
		repo.save(pedi);
		repoItemPedido.saveAll(itens);
		
		
		pedi.setItens(itens);
		
		return pedi;
	}
	
	
	private List<ItemPedido> salvarItens(Pedido pedido, List<ItemPedidoInput> itens) {
		if(itens.isEmpty()) throw new ServiceException("Favor adicionar itens ao pedido");
		
		return mapperItens.toCollectionEntity(pedido, itens);
	}

	
	
	
	
	
	
	
	
	
}

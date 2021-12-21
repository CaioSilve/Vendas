package silveira.caio.api.mapper;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import silveira.caio.api.model.input.ItemPedidoInput;
import silveira.caio.domain.entity.ItemPedido;
import silveira.caio.domain.entity.Pedido;
import silveira.caio.domain.exception.ServiceException;
import silveira.caio.domain.repository.ProdutoRepository;

@Component
public class ItemPedidoMapper {
	
	@Autowired
	private ModelMapper mapper;
	
	@Autowired
	private ProdutoRepository repoProd;
	
	public ItemPedido toEntity(Pedido pedido, ItemPedidoInput item) {
		ItemPedido itemPedido = mapper.map(item, ItemPedido.class);
		itemPedido.setId(null);
		itemPedido.setProduto(repoProd.findById(item.getIdProduto())
				.orElseThrow(() -> new ServiceException("Produto com o cógigo inválido: " + item.getIdProduto())));
		itemPedido.setPedido(pedido);
		return itemPedido;
	}
	
	public List<ItemPedido> toCollectionEntity(Pedido pedido,List<ItemPedidoInput> itens) {
		return itens.stream().map(item -> this.toEntity(pedido, item)).distinct().collect(Collectors.toList());
	}

}

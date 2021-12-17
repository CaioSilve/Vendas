package silveira.caio.api.mapper;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;

import silveira.caio.api.model.input.ItemPedidoInput;
import silveira.caio.domain.entity.ItemPedido;

public class ItemPedidoMapper {
	
	@Autowired
	ModelMapper mapper;
	
	public ItemPedido toEntity(ItemPedidoInput item) {
		return mapper.map(item, ItemPedido.class);
	}
	
	public Set<ItemPedido> toCollectionEntity(List<ItemPedidoInput> itens) {
		return itens.stream().map(this::toEntity).distinct().collect(Collectors.toSet());
	}

}
